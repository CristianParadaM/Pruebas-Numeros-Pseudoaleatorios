package model;

import java.io.File;

public class Prueba_Varianza extends Prueba_Numero_Pseudoaleatorio{
	

	public Prueba_Varianza(File file) {
		super(file);
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
		return PruebaChi2.TABLE_025[list.size()-1];
	}
	
	public double getChiCuadrado0975() {
		return PruebaChi2.TABLE_0975[list.size()-1];
	}

	public double getLi() {
		return getChiCuadrado025()/(12*(list.size()-1));
	}

	@Override
	public double getLs() {
		return getChiCuadrado0975()/(12*(list.size()-1));
	}
	
}
