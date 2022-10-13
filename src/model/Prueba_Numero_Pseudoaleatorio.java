package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Prueba_Numero_Pseudoaleatorio {
	public static final String PATH_CHI2 = "src/data/Prueba Chi2.csv";
	protected File file;
	ArrayList<Double> list;
	private File fileChi2;
	protected ArrayList<Double> table_0025;
	protected ArrayList<Double> table_005;
	protected ArrayList<Double> table_0975;

	public Prueba_Numero_Pseudoaleatorio(File file) {
		this.file = file;
		this.fileChi2 = new File(PATH_CHI2);
		table_0025 = new ArrayList<Double>();
		table_005 = new ArrayList<Double>();
		table_0975 = new ArrayList<Double>();
		readNumberChi2();
		list = readNumbers();
	}
	
	public Prueba_Numero_Pseudoaleatorio(ArrayList<Double> numbers) {
		this.list = numbers;
	}
	
	public ArrayList<Double> getList() {
		return list;
	}
	
	private void readNumberChi2() {
		try {
			Scanner scanner = new Scanner(fileChi2);
			while (scanner.hasNext()) {
				String[] numbers = scanner.nextLine().split(";");
				for (int i = 0; i < numbers.length; i++) {
					numbers[i] = numbers[i].replace(",", ".");
				}
				table_0025.add(Double.parseDouble((numbers[0])));
				table_005.add(Double.parseDouble((numbers[1])));
				table_0975.add(Double.parseDouble((numbers[2])));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Double> readNumbers(){
		ArrayList<Double> list = new ArrayList<Double>();
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String number = scanner.nextLine().replace(",", ".");
				list.add(Double.parseDouble((number)));
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