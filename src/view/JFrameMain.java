package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.utils.Constants;

public class JFrameMain extends JFrame {

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

	public void showTest(int index, Object... object) {
		jPanelMain.showPanel(index, object);
	}

	public void init() {
		this.jPanelMain = new JPanelMain();
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		this.setMinimumSize(new Dimension(1400 * WIDTH_FRAME / 1920, 800 * HEIGHT_SCREEN / 1080));
		this.setLocationRelativeTo(null);
		this.setContentPane(jPanelMain);
		this.setVisible(true);
	}

	public void showPath(String path) {
		jPanelMain.showPath(path);
	}

	public void alert(String message, String title, int type) {
		JOptionPane.showMessageDialog(this, message, title, type);
	}

}
