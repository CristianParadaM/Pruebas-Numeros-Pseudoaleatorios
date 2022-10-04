package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.JFrameMain;
import view.utils.Constants;

public class Controller implements ActionListener {

	private JFrameMain view;
	private static Controller controller = null;
	private File fileSelected;

	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
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
		case Constants.COMMAND_START-> startTest();
		case Constants.COMMAND_LOAD_FILE -> loadFile();
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
		//TODO
	}

}
