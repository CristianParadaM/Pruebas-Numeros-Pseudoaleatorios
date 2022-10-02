package model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PruebaKS extends Prueba_Numero_Pseudoaleatorio {

	private static final int NUMBER_OF_INTERVALS = 10;
	private int count;
	private int i;
	private int count2;
	private static final double[] TABLE_KS_005 = new double[] { 0.97500, 0.84189, 0.70760, 0.62394, 0.56328, 0.51926,
			0.48342, 0.45427, 0.43001, 0.40925, 0.39122, 0.37543, 0.36143, 0.34890, 0.33750, 0.32733, 0.31796, 0.30936,
			0.30143, 0.29408, 0.28724, 0.28087, 0.27491, 0.26931, 0.26404, 0.25908, 0.25438, 0.24993, 0.24571, 0.24170,
			0.23788, 0.23424, 0.23076, 0.22743, 0.22425, 0.22119, 0.21826, 0.21544, 0.21273, 0.21012, 0.20760, 0.20517,
			0.20283, 0.20056, 0.19837, 0.19625, 0.19420, 0.19221, 0.19028, 0.18841 };

	public PruebaKS(File file) {
		super(file);
		count = 0;
		count2 = 0;
		i = 0;
	}

	@Override
	public boolean isApproved() {
		double dMax = Ks_Test();
		double dMaxP = 0;
		if (getSize() > 50) {
			dMaxP = 1.36 / Math.sqrt(getSize());
		} else {
			dMaxP = TABLE_KS_005[getSize() - 1];
		}
		return dMax < dMaxP;
	}

	private double Ks_Test() {
		LinkedHashMap<String, Integer> freq = calculateFrequencies();
		ArrayList<Integer> freqAc = new ArrayList<Integer>();
		ArrayList<Double> probObt = new ArrayList<Double>();
		ArrayList<Integer> frecEspAc = new ArrayList<Integer>();
		ArrayList<Double> probEsp = new ArrayList<Double>();
		ArrayList<Double> dife = new ArrayList<Double>();
		freq.forEach((k, v) -> {
			frecEspAc.add(count2 += 5);
			freqAc.add(count += v);
			probObt.add(Double.parseDouble(
					String.format("%,.5f", ((double) freqAc.get(i) / (double) getSize())).replace(",", ".")));
			probEsp.add(Double.parseDouble(
					String.format("%,.5f", ((double) frecEspAc.get(i) / (double) getSize())).replace(",", ".")));
			dife.add(Double
					.parseDouble(String.format("%,.5f", Math.abs(probEsp.get(i) - probObt.get(i))).replace(",", ".")));
			i++;
		});
		return getMax(dife);
	}
	
	public ArrayList<Object[]> getTable() {
		ArrayList<Object[]> table = new ArrayList<Object[]>();
		LinkedHashMap<String, Integer> frecuencies = calculateFrequencies();
		count = 0;
		frecuencies.forEach((k, v) -> {
			String[] intervals = k.split("-");
			int frec_a = count == 1 ? v : v + (int) table.get(count - 2)[4];
			double frec_esp_a = count == 1 ? list.size() / NUMBER_OF_INTERVALS
					: (list.size() / NUMBER_OF_INTERVALS) + (double) table.get(count - 2)[6];
			Object[] aux = new Object[] { count++, Double.parseDouble(intervals[0]), Double.parseDouble(intervals[1]),
					v, frec_a, frec_a / list.size(), frec_esp_a, frec_esp_a / list.size(),
					(frec_a / list.size()) - (frec_esp_a / list.size()) };
			table.add(aux);
		});
		return table;
	}

	private double getMax(ArrayList<Double> list) {
		double max = list.get(0);
		for (Double number : list) {
			if (number > max) {
				max = number;
			}
		}
		return max;
	}

	public ArrayList<Double[]> generateIntervals() {
		ArrayList<Double[]> intervals = new ArrayList<>();
		double inicio = getLi();
		for (int i = 0; i < NUMBER_OF_INTERVALS; i++) {
			Double[] aux = new Double[] { inicio,
					Double.parseDouble(
							String.format("%,.1f", inicio + ((getLs() - getLi()) / (double) NUMBER_OF_INTERVALS))
									.replace(",", ".")) };
			intervals.add(aux);
			inicio = aux[1];
		}
		return intervals;
	}

	public LinkedHashMap<String, Integer> calculateFrequencies() {
		ArrayList<Double[]> intervals = generateIntervals();
		LinkedHashMap<String, Integer> frecuencies = new LinkedHashMap<>();
		for (Double[] interval : intervals) {
			frecuencies.put(interval[0] + " - " + interval[1], 0);
		}
		for (Double doubles : list) {
			double number = doubles;
			for (Double[] interval : intervals) {
				if (number > interval[0] && number < interval[1]) {
					frecuencies.put(interval[0] + " - " + interval[1],
							frecuencies.get(interval[0] + " - " + interval[1]) + 1);
					break;
				}
			}
		}
		return frecuencies;
	}

	@Override
	public double getLi() {
		return 0;
	}

	@Override
	public double getLs() {
		return 1;
	}

}
