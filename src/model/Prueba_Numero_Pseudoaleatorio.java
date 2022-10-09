package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Prueba_Numero_Pseudoaleatorio {
	protected File file;
	ArrayList<Double> list;

	public Prueba_Numero_Pseudoaleatorio(File file) {
		this.file = file;
		list = readNumbers();
	}
	
	public ArrayList<Double> getList() {
		return list;
	}
	
	private ArrayList<Double> readNumbers(){
		ArrayList<Double> list = new ArrayList<Double>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String number = scanner.nextLine().replace(",", ".");
				list.add(truncarNumber(Double.parseDouble((number))));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int getSize() {
		return list.size();
	}
	
	public double getAverage() {
		double sum = 0;
		for (Double double1 : list) {
			sum += double1;
		}
		return truncarNumber(sum/list.size());
	}
	
	protected double truncarNumber(double x) {
		String number = x+"";
		return Double.parseDouble((number.length()>7?number.substring(0,7):number));
	}
	
	public abstract boolean isApproved();
	
	public abstract double getLi();
	
	public abstract double getLs();
}
