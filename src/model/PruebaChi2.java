package model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PruebaChi2 extends Prueba_Numero_Pseudoaleatorio {
	public static final int NUMBER_OF_COLUMNS = 2;
	public static final int NUMBER_OF_INTERVALS = 8;

	private int count;

	public PruebaChi2(File file) {
		super(file);
	}
	
	public PruebaChi2(ArrayList<Double> numbers) {
		super(numbers);
	}

	@Override
	public boolean isApproved() {
		return getError() < getMaximumPossibleError();
	}

	public ArrayList<Double[]> generateIntervals() {
		ArrayList<Double[]> intervals = new ArrayList<>();
		double inicio = getLi();
		for (int i = 0; i < NUMBER_OF_INTERVALS - 1; i++) {
			Double[] aux = new Double[] { inicio,
					truncarNumber(inicio + ((getLs() - getLi()) / (double) NUMBER_OF_INTERVALS)) };
			intervals.add(aux);
			inicio = aux[1];
		}
		intervals.add(new Double[] { inicio, getLs() });
		return intervals;
	}

	public LinkedHashMap<String, Integer> calculateFrequencies() {
		ArrayList<Double[]> intervals = generateIntervals();
		LinkedHashMap<String, Integer> frecuencies = new LinkedHashMap<>();
		for (Double[] interval : intervals) {
			frecuencies.put(interval[0] + "-" + interval[1], 0);
		}
		for (Double doubles : list) {
			double number = truncarNumber(doubles);
			for (Double[] interval : intervals) {
				if (number >= interval[0] && number <= interval[1]) {
					frecuencies.put(interval[0] + "-" + interval[1],
							frecuencies.get(interval[0] + "-" + interval[1]) + 1);
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
			aux = aux >= double1 ? double1 : aux;
		}
		return truncarNumber(aux);
	}

	@Override
	public double getLs() {
		double aux = 0;
		for (Double double1 : list) {
			aux = aux <= double1 ? double1 : aux;
		}
		return truncarNumber(aux);
	}

	public ArrayList<Double> getColumnChi2() {
		LinkedHashMap<String, Integer> frecuencies = calculateFrequencies();
		ArrayList<Double> columnChi2 = new ArrayList<Double>();
		frecuencies.forEach((k, v) -> {
			columnChi2.add(truncarNumber(Math.pow(v - getFrecEsp(), 2) / getFrecEsp()));
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
		return table_005.get(getDegreesOfFreedom() - 1);
	}

	public int getDegreesOfFreedom() {
		return (NUMBER_OF_COLUMNS - 1) * (NUMBER_OF_INTERVALS - 1);
	}

	public double getFrecEsp() {
		return truncarNumber((double) list.size() / NUMBER_OF_INTERVALS);
	}

	public ArrayList<Object[]> getTable() {
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		LinkedHashMap<String, Integer> frecuencies = calculateFrequencies();
		count = 0;
		frecuencies.forEach((k, v) -> {
			String[] strings = k.split("-");
			double frecEsp = (double) getSize() / NUMBER_OF_INTERVALS;
			aux.add(new Object[] { count++, strings[0], strings[1], v, frecEsp,
					truncarNumber(Math.pow(v - frecEsp, 2) / frecEsp) });
		});
		return aux;
	}

	public ArrayList<Object[]> getTableLiberty() {
		ArrayList<Object[]> aux = new ArrayList<Object[]>();
		aux.add(new Object[] { getError(), (NUMBER_OF_COLUMNS - 1) * (NUMBER_OF_INTERVALS - 1),
				getMaximumPossibleError() });
		return aux;
	}

}
