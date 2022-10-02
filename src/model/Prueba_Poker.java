package model;

import java.io.File;
import java.util.Iterator;

public class Prueba_Poker extends Prueba_Numero_Pseudoaleatorio{
	private double[] odds;
	
	public Prueba_Poker(File file) {
		super(file);
		odds = new double[] {0.3024, 0.5040, 0.1080, 0.0720, 0.0090, 0.0045, 0.0001};
	}
	
	@Override
	public boolean isApproved() {
		return calculateError() < PruebaChi2.TABLE_005[6];
	}
	
	public double calculateError() {
		double aux = 0;
		double[] diference = calculateDiference();
		for (int i = 0; i < diference.length; i++) {
			aux += diference[i];
		}
		return aux;
	}
	
	public double[] calculateDiference() {
		double[] aux = new double[odds.length];
		double[] expectedFrecuency = calculateExpectedFrecuency();
		int[] obtainedFrecuency = calculateObtainedFrecuency();
		for (int i = 0; i < obtainedFrecuency.length; i++) {
			aux[i] = Math.pow(expectedFrecuency[i]-obtainedFrecuency[i],2)/expectedFrecuency[i];
		}
		return aux;
	}
	
	public double[] calculateExpectedFrecuency() {
		double[] aux = new double[odds.length];
		for (int i = 0; i < aux.length; i++) {
			aux[i] = list.size()*odds[i];
		}
		return aux;
	}
	
	public int[] calculateObtainedFrecuency() {
		int[] aux = new int[odds.length];
		for (int i = 0; i < list.size(); i++) {
			int[] repetitions = countRepetitions(list.get(i));
			if (repetitions[0] == 5) {
				aux[6] = aux[6]+1;
			}else if(repetitions[0] == 4 || repetitions[1] == 4) {
				aux[5] = aux[5]+1;
			}else if((repetitions[0] == 3 || repetitions[1] == 3 || repetitions[2] == 3) && countPar(repetitions) == 2) {
				aux[4] = aux[4]+1;
			}else if(repetitions[0] == 3 || repetitions[1] == 3 || repetitions[2] == 3) {
				aux[3] = aux[3]+1;
			}else if(countPar(repetitions) == 4) {
				aux[2] = aux[2]+1;
			}else if(countPar(repetitions) == 2) {
				aux[1] = aux[1]+1;
			}else {
				aux[0] = aux[0]+1;
			}
		}
		return aux;
	}

	private int countPar(int[] repetitions) {
		int count = 0;
		for (int i = 0; i < repetitions.length; i++) {
			if(repetitions[i] == 2) {
				count++;
			}
		}
		return count;
	}

	private int[] countRepetitions(Double double1) {
		String aux = double1.toString().substring(2,double1.toString().length());
		int[] repetitions = new int[5];
		for (int i = 0; i < aux.length(); i++) {
			repetitions[i] = amountCharacter(aux.charAt(i), aux);
		}
		return repetitions;
	}

	private int amountCharacter(char charAt, String string) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == charAt) {
				count++;
			}
		}
		return count;
	}

	@Override
	public double getLi() {
		return 0;
	}

	@Override
	public double getLs() {
		return 0;
	}
	
}
