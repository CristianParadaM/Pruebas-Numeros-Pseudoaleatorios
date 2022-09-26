package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.JFrameMain;
import view.utils.Constants;

public class Controller implements ActionListener {

	private JFrameMain view;
	private static Controller controller = null;

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
		}
	}

	private void startTest() {
		//TODO
	}

}
