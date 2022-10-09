package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.PruebaChi2;
import model.PruebaKS;
import model.Prueba_Medias;
import model.Prueba_Poker;
import model.Prueba_Varianza;
import view.JFrameMain;
import view.utils.Constants;

public class Controller implements ActionListener {

	public static final double ACEPTACION = 0.95;
	public static final double ERROR = 0.05;
	public static final double COMPLEMENT = 0.975;
	private JFrameMain view;
	private static Controller controller = null;
	private boolean[] startMethods;
	private File fileSelected;
	private Object[] objects1;
	private Object[] objects2;
	private Object[] objects3;
	private Object[] objects4;
	private Object[] objects5;
	private boolean[] aproval;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public Controller() {
		this.objects1 = null;
		this.objects2 = null;
		this.objects3 = null;
		this.objects4 = null;
		this.objects5 = null;
		this.startMethods = new boolean[] { false, false, false, false, false };
		this.aproval = new boolean[5];
	}

	public void initApp() {
		view = JFrameMain.getInstance();
		view.init();
	}

	public static void main(String[] args) {
		Controller.getInstance().initApp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Constants.COMMAND_START -> startTest();
		case Constants.COMMAND_LOAD_FILE -> loadFile();
		case Constants.COMMAND_TEST1_START -> startTest(0);
		case Constants.COMMAND_TEST2_START -> startTest(1);
		case Constants.COMMAND_TEST3_START -> startTest(2);
		case Constants.COMMAND_TEST4_START -> startTest(3);
		case Constants.COMMAND_TEST5_START -> startTest(4);
		case Constants.COMMAND_TEST1 -> showTest(0);
		case Constants.COMMAND_TEST2 -> showTest(1);
		case Constants.COMMAND_TEST3 -> showTest(2);
		case Constants.COMMAND_TEST4 -> showTest(3);
		case Constants.COMMAND_TEST5 -> showTest(4);
		}
	}

	private void showTest(int index) {
		if (startMethods[index]) {
			view.showTest(index, index == 0 ? objects1
					: index == 1 ? objects2 : index == 2 ? objects3 : index == 3 ? objects4 : objects5);
		} else {
			view.alert("No se ha realizado el test, a continuacion seleccione iniciar test o iniciar individualmente",
					Constants.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean getInfoMedias() {
		Prueba_Medias medias = new Prueba_Medias(fileSelected);
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		mergeI(data, medias.getList());
		ArrayList<Object[]> info = new ArrayList<Object[]>();
		info.add(new Object[] { (int) (ACEPTACION * 100) + "%", (int) (ERROR * 100) + "%", medias.getSize(),
				medias.getAverage(), 1 - (ERROR / 2), Prueba_Medias.Z, medias.getLi(), medias.getLs() });
		objects1 = new Object[] { data, info, medias.isApproved() };
		boolean apr = medias.isApproved();
		aproval[0] = apr;
		return apr;
	}

	private void mergeI(ArrayList<Object[]> data, ArrayList<Double> list) {
		for (int i = 0; i < list.size(); i++) {
			data.add(new Object[] { i + 1, list.get(i) });
		}
	}

	private void startTest(int index) {
		if (fileSelected != null) {
			switch (index) {
			case 0:
				startMethods[index] = true;
				view.setAprovalTest(index, getInfoMedias(), objects1);
				break;
			case 1:
				startMethods[index] = true;
				view.setAprovalTest(index, getInfoVarianza(), objects2);
				break;
			case 2:
				startMethods[index] = true;
				view.setAprovalTest(index, getInfoKS(), objects3);
				break;
			case 3:
				startMethods[index] = true;
				view.setAprovalTest(index, getInfoChi2(), objects4);
				break;
			case 4:
				startMethods[index] = true;
				view.setAprovalTest(index, getInfoPoker(), objects5);
				break;
			}
		} else {
			view.alert("No se ha seleccionado el archivo, por favor seleccione algun archivo CSV", Constants.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean getInfoPoker() {
		Prueba_Poker poker = new Prueba_Poker(fileSelected);
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		mergeI(data, poker.getList());
		objects5 = new Object[] { data, poker.getTable(), poker.getTableErrors(), poker.isApproved() };
		boolean apr = poker.isApproved();
		aproval[4] = apr;
		return apr;
	}

	private boolean getInfoChi2() {
		PruebaChi2 chi2 = new PruebaChi2(fileSelected);
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		mergeI(data, chi2.getList());
		objects4 = new Object[] { data, chi2.getTable(), chi2.getTableLiberty(), chi2.isApproved() };
		boolean apr = chi2.isApproved();
		aproval[3] = apr;
		return apr;
	}

	private boolean getInfoKS() {
		PruebaKS ks = new PruebaKS(fileSelected);
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		mergeI(data, ks.getList());
		ArrayList<Object[]> info = new ArrayList<Object[]>();
		info.add(new Object[] { (int) (ACEPTACION * 100) + "%", (int) (ERROR * 100) + "%", ks.getSize(),
				ks.getAverage(), 0, 1 });
		objects3 = new Object[] { data, ks.getTable(), info, ks.getTableDMaxP(), ks.isApproved() };
		boolean apr = ks.isApproved();
		aproval[2] = apr;
		return apr;
	}

	private boolean getInfoVarianza() {
		Prueba_Varianza varianza = new Prueba_Varianza(fileSelected);
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		mergeI(data, varianza.getList());
		ArrayList<Object[]> info = new ArrayList<Object[]>();
		info.add(new Object[] { (int) (ACEPTACION * 100) + "%", (int) (ERROR * 100) + "%", varianza.getSize(),
				varianza.getAverage(), varianza.getVariation(), (ERROR / 2), 1 - (ERROR / 2),
				varianza.getChiCuadrado025(), varianza.getChiCuadrado0975(), varianza.getLi(), varianza.getLs() });
		objects2 = new Object[] { data, info, varianza.isApproved() };
		boolean apr = varianza.isApproved();
		aproval[1] = apr;
		return apr;
	}

	private void loadFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("CSV (,)", "csv");
		chooser.setFileFilter(imgFilter);
		int result = chooser.showOpenDialog(view);
		if (result != JFileChooser.CANCEL_OPTION) {
			fileSelected = chooser.getSelectedFile();
			view.showPath(fileSelected.getPath());
			view.reset();
		}
		this.startMethods = new boolean[] { false, false, false, false, false };
	}

	private void startTest() {
		if (fileSelected != null) {
			new Thread(() -> {
				try {
					int delay = 200;
					startTest(0);
					Thread.sleep(delay);
					startTest(1);
					Thread.sleep(delay);
					startTest(2);
					Thread.sleep(delay);
					startTest(3);
					Thread.sleep(delay);
					startTest(4);
					view.showResult(isAproval());
				} catch (InterruptedException e) {
				}
			}).start();
		} else {
			view.alert("No se ha seleccionado el archivo, por favor seleccione algun archivo CSV", Constants.ERROR,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean isAproval() {
		for (boolean b : aproval) {
			if (!b) {
				return b;
			}
		}
		return true;
	}

}
