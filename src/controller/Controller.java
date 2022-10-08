package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.JFrameMain;
import view.utils.Constants;

public class Controller implements ActionListener {

	private JFrameMain view;
	private static Controller controller = null;
	private File fileSelected;
	private boolean[] startMethods;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public Controller() {
		this.startMethods = new boolean[] { false, false, false, false, false };
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
		case Constants.COMMAND_TEST1_START -> startTest(Constants.COMMAND_TEST1_START);
		case Constants.COMMAND_TEST2_START -> startTest(Constants.COMMAND_TEST2_START);
		case Constants.COMMAND_TEST3_START -> startTest(Constants.COMMAND_TEST3_START);
		case Constants.COMMAND_TEST4_START -> startTest(Constants.COMMAND_TEST4_START);
		case Constants.COMMAND_TEST5_START -> startTest(Constants.COMMAND_TEST5_START);
		case Constants.COMMAND_TEST1 -> showTest(0);
		case Constants.COMMAND_TEST2 -> showTest(1);
		case Constants.COMMAND_TEST3 -> showTest(2);
		case Constants.COMMAND_TEST4 -> showTest(3);
		case Constants.COMMAND_TEST5 -> showTest(4);
		}
	}

	private void showTest(int index) {
		if (startMethods[index]) {
			Object[] objects = null;
			switch (index) {
//				case 0 -> 
			}
			view.showTest(index);
		} else {
			view.alert("No se ha realizado el test, a continuacion seleccione iniciar test o iniciar individualmente",
					Constants.ERROR, JOptionPane.ERROR_MESSAGE);
		}
	}

	private void startTest(String command) {
		if (fileSelected != null) {

		}
	}

	private void loadFile() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("CSV (,)", "csv");
		chooser.setFileFilter(imgFilter);
		int result = chooser.showOpenDialog(view);
		if (result != JFileChooser.CANCEL_OPTION) {
			fileSelected = chooser.getSelectedFile();
			view.showPath(fileSelected.getPath());
		}
	}

	private void startTest() {
		// TODO
	}

}
