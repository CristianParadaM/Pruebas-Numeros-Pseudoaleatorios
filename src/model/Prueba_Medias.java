package model;

import java.io.File;

public class Prueba_Medias extends Prueba_Numero_Pseudoaleatorio{
	public static final double Z = 1.96;

	public Prueba_Medias(File file) {
		super(file);
	}
	
	public boolean isApproved() {
		double promedio = getAverage();
		return promedio >= getLi() && promedio <= getLs();
	}
	
	public double getLi() {
		return truncarNumber((0.5)-Z*(1/Math.sqrt(12*getSize())));
	}
	
	public double getLs() {
		return truncarNumber((0.5)+Z*(1/Math.sqrt(12*getSize())));
	}
	
}
