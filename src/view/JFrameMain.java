package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import view.utils.Constants;

public class JFrameMain extends JFrame implements ActionListener {

	public static final int WIDTH_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int HEIGHT_SCREEN = Toolkit.getDefaultToolkit().getScreenSize().height;
	public static final int WIDTH_FRAME = 1300 * WIDTH_SCREEN / 1920;
	public static final int HEIGHT_FRAME = 800 * HEIGHT_SCREEN / 1080;
	private JPanelMain jPanelMain;
	private static JFrameMain jFrameMain = null;

	public static JFrameMain getInstance() {
		if (jFrameMain == null) {
			jFrameMain = new JFrameMain();
		}
		return jFrameMain;
	}

	public JFrameMain() {
		super("Pruebas para numeros Pseudoaletarios");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case Constants.COMMAND_LOAD_FILE -> loadFile();
		case Constants.COMMAND_TEST1 -> showTest(0);
		case Constants.COMMAND_TEST2 -> showTest(1);
		case Constants.COMMAND_TEST3 -> showTest(2);
		case Constants.COMMAND_TEST4 -> showTest(3);
		case Constants.COMMAND_TEST5 -> showTest(4);
		}
	}

	private void showTest(int index) {
		jPanelMain.showPanel(index);
	}

	private void loadFile() {
	}

	public void init() {
		this.jPanelMain = new JPanelMain();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH_FRAME,HEIGHT_FRAME);
		this.setMinimumSize(new Dimension(1300 * WIDTH_FRAME / 1920, 800 * HEIGHT_SCREEN / 1080));
		this.setLocationRelativeTo(null);
		this.setContentPane(jPanelMain);
		this.setVisible(true);
	}

}
