package model;

import java.io.File;
import java.util.ArrayList;

public class Prueba_Varianza extends Prueba_Numero_Pseudoaleatorio{
	

	public Prueba_Varianza(File file) {
		super(file);
	}
	
	public Prueba_Varianza(ArrayList<Double> numbers) {
		super(numbers);
	}
	
	public boolean isApproved() {
		double variation = getVariation();
		return variation >= getLs() && variation <= getLi();
	}
	
	public double getVariation() {
		double sum = 0;
		double average = getAverage();
		for (Double double1 : list) {
			sum += Math.pow(double1-average, 2);
		}
		return truncarNumber(sum/(getSize()-1));
	}
	
	public double getChiCuadrado025() {
		return table_0025.get(list.size()-1);
	}
	
	public double getChiCuadrado0975() {
		return table_0975.get(list.size()-1);
	}

	public double getLi() {
		return truncarNumber(getChiCuadrado025()/(12*(list.size()-1)));
	}

	@Override
	public double getLs() {
		return truncarNumber(getChiCuadrado0975()/(12*(list.size()-1)));
	}
	
}
