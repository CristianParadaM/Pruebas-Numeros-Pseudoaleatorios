package model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PruebaChi2 extends Prueba_Numero_Pseudoaleatorio{
	public static final int NUMBER_OF_COLUMNS = 2;
	public static final int NUMBER_OF_INTERVALS = 8;

	public static final double[] TABLE_025 = new double[] { 5.024, 7.378, 9.348, 11.143, 12.833, 14.449, 16.013, 17.535,
			19.023, 20.483, 21.920, 23.337, 24.736, 26.119, 27.488, 28.485, 30.191, 31.526, 32.852, 34.170, 35.479,
			36.781, 38.076, 39.364, 40.646, 41.923, 43.195, 44.461, 45.722, 46.979, 48.232, 49.480, 50.725, 51.966,
			53.203, 54.437, 55.668, 56.896, 58.120, 59.342, 60.561, 61.777, 62.990, 64.201, 65.410, 66.617, 67.821,
			69.023, 70.222, 71.420 };

	public static final double[] TABLE_0975 = new double[] { 0.00098, 0.051, 0.216, 0.484, 0.831, 1.237, 1.690, 2.180,
			2.700, 3.247, 3.816, 4.404, 5.009, 5.629, 6.262, 6.908, 7.564, 8.231, 8.907, 9.591, 10.283, 10.982, 11.689,
			12.401, 13.120, 13.844, 14.573, 15.308, 16.047, 16.791, 17.539, 18.291, 19.047, 19.806, 20.569, 21.336,
			22.106, 22.878, 23.654, 24.433, 25.215, 25.999, 26.785, 27.575, 28.366, 29.160, 29.956, 30.755, 31.555,
			32.357 };

	public static final double[] TABLE_005 = new double[] { 3.841, 5.991, 7.815, 9.488, 11.070, 12.592, 14.067, 15.507,
			16.919, 18.307, 19.675, 21.026, 22.362, 23.685, 24.996, 26.296, 27.587, 28.869, 30.144, 31.410, 32.671,
			33.924, 35.172, 36.415, 37.652, 38.885, 40.113, 41.337, 42.557, 43.773, 44.985, 46.194, 47.400, 48.602,
			49.802, 50.998, 52.192, 53.384, 54.572, 55.758, 56.942, 58.124, 59.304, 60.481, 61.656, 62.830, 64.001,
			65.171, 66.339, 67.505	};
	
	public PruebaChi2(File file) {
		super(file);
	}

	@Override
	public boolean isApproved() {
		return getError() < getMaximumPossibleError();
	}
	
	public ArrayList<Double[]> generateIntervals(){
		ArrayList<Double[]> intervals = new ArrayList<>();
		double inicio = getLi();
		for (int i = 0; i < NUMBER_OF_INTERVALS-1; i++) {
			Double[] aux = new Double[] {inicio, truncarNumber(inicio+((getLs()-getLi())/(double)NUMBER_OF_INTERVALS))};
			intervals.add(aux);
			inicio = aux[1];
		}
		intervals.add(new Double[] {inicio, getLs()});
		return intervals;
	}
	
	public LinkedHashMap<String, Integer> calculateFrequencies(){
		ArrayList<Double[]> intervals = generateIntervals();
		LinkedHashMap<String, Integer> frecuencies = new LinkedHashMap<>();
		for (Double[] interval : intervals) {
			frecuencies.put(interval[0]+" - "+interval[1], 0);
		}
		for (Double doubles : list) {
			double number = truncarNumber(doubles);
			for (Double[] interval : intervals) {
				if (number >= interval[0] && number <= interval[1]) {
					frecuencies.put(interval[0]+" - "+interval[1], frecuencies.get(interval[0]+" - "+interval[1])+1);
					break;
				}
			}
		}
		return frecuencies;
	}

	@Override
	public double getLi() {
		double aux = getLs();
		for (Double double1 : list) {
			aux = aux >= double1?double1:aux;
		}
		return truncarNumber(aux);
	}

	@Override
	public double getLs() {
		double aux = 0;
		for (Double double1 : list) {
			aux = aux <= double1?double1:aux;
		}
		return truncarNumber(aux);
	}
	
	public ArrayList<Double> getColumnChi2(){
		LinkedHashMap<String, Integer> frecuencies = calculateFrequencies();
		ArrayList<Double> columnChi2 = new ArrayList<Double>();
		frecuencies.forEach((k,v) -> {
			columnChi2.add(truncarNumber(Math.pow(v-getFrecEsp(), 2)/getFrecEsp()));
		});
		return columnChi2;
	}
	
	public double getError() {
		ArrayList<Double> columnChi2 = getColumnChi2();
		double sum = 0;
		for (Double double1 : columnChi2) {
			sum += double1;
		}
		return truncarNumber(sum);
	}
	
	public double getMaximumPossibleError() {
		return TABLE_005[getDegreesOfFreedom()-1];
	}
	
	public int getDegreesOfFreedom() {
		return (NUMBER_OF_COLUMNS-1)*(NUMBER_OF_INTERVALS-1);
	}
	
	public double getFrecEsp() {
		return truncarNumber((double)list.size()/NUMBER_OF_INTERVALS);
	}
	
}
