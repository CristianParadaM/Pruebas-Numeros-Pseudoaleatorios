package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import controller.Controller;
import view.utils.Constants;

public class JPanelMain extends JPanel {

	private JPanel jPanelContainer;
	private JScrollPane jScrollPane;

	private JLabel jLabelTitle;
	private JButton jButtonLoadFile;
	private JButton jButtonStart;
	private JLabel jLabelPath;
	private JLabel jLabelHelp;

	private ArrayList<Object[]> activeButtons;
	private ArrayList<Object[]> activePanels;

	private JButton jButtonP1;
	private JButton jButtonP2;
	private JButton jButtonP3;
	private JButton jButtonP4;
	private JButton jButtonP5;

	private JPanelTestMedia jPanelContent1;
	private JPanelTestVarianza jPanelContent2;
	private JPanelTestKS jPanelContent3;
	private JPanelTestChi2 jPanelContent4;
	private JPanelTestPoker jPanelContent5;

	private JButton JButtonTest1;
	private JButton JButtonTest2;
	private JButton JButtonTest3;
	private JButton JButtonTest4;
	private JButton JButtonTest5;

	private JLabel jLabelResult;
	private JLabel jLabelImage;

	private int coordy;

	public JPanelMain() {
		super(new GridLayout());
		this.jLabelResult = new JLabel("Aun no has realizado alguna prueba", JLabel.CENTER);
		this.jLabelImage = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource(Constants.PATH_IMG_LOGO))
				.getImage().getScaledInstance(600 * JFrameMain.WIDTH_SCREEN / 1920,
						100 * JFrameMain.HEIGHT_SCREEN / 1080, Image.SCALE_SMOOTH)));
		this.jPanelContainer = new JPanel();
		this.jScrollPane = new JScrollPane();
		this.jLabelTitle = new JLabel("PROGRAMA DE VALIDACIÓN DE NÚMEROS PSEUDOALEATORIOS", JLabel.CENTER);
		this.jButtonLoadFile = new JButton("Seleccione algun archivo");
		this.jButtonStart = new JButton("Empezar Pruebas");
		this.jLabelPath = new JLabel("", JLabel.CENTER);
		this.jLabelHelp = new JLabel("", JLabel.CENTER);

		this.jButtonP1 = new JButton("Prueba de Medias");
		this.jButtonP2 = new JButton("Prueba de Varianza");
		this.jButtonP3 = new JButton("Prueba de Prueba KS");
		this.jButtonP4 = new JButton("Prueba de Chi2");
		this.jButtonP5 = new JButton("Prueba de Poker");

		this.jPanelContent1 = new JPanelTestMedia(null, null, false);
		this.jPanelContent2 = new JPanelTestVarianza(null, null, false);
		this.jPanelContent3 = new JPanelTestKS();
		this.jPanelContent4 = new JPanelTestChi2();
		this.jPanelContent5 = new JPanelTestPoker();

		ImageIcon icon = new ImageIcon(
				new ImageIcon("src/res/play.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

		this.JButtonTest1 = new JButton(icon);
		this.JButtonTest2 = new JButton(icon);
		this.JButtonTest3 = new JButton(icon);
		this.JButtonTest4 = new JButton(icon);
		this.JButtonTest5 = new JButton(icon);

		this.activeButtons = new ArrayList<Object[]>();
		this.activePanels = new ArrayList<Object[]>();
		activeButtons.add(new Object[] { jButtonP1, true });
		activeButtons.add(new Object[] { jButtonP2, true });
		activeButtons.add(new Object[] { jButtonP3, true });
		activeButtons.add(new Object[] { jButtonP4, true });
		activeButtons.add(new Object[] { jButtonP5, true });

		activePanels.add(new Object[] { jPanelContent1, false });
		activePanels.add(new Object[] { jPanelContent2, false });
		activePanels.add(new Object[] { jPanelContent3, false });
		activePanels.add(new Object[] { jPanelContent4, false });
		activePanels.add(new Object[] { jPanelContent5, false });
		init();
	}

	private void init() {
		this.jPanelContainer.setLayout(null);
		this.jPanelContainer.setOpaque(false);
		this.jScrollPane.setViewportView(jPanelContainer);
		this.jScrollPane.setOpaque(false);
		this.jScrollPane.getViewport().setOpaque(false);
		this.jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		this.jScrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
		this.jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.jScrollPane.getVerticalScrollBar().setUnitIncrement(5);
		this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(38, 38, 38);
			}

		});
		jScrollPane.getVerticalScrollBar().setBackground(new Color(175, 171, 171));
		
		this.jPanelContainer
				.setPreferredSize(new Dimension(JFrameMain.WIDTH_FRAME - (100 * JFrameMain.WIDTH_SCREEN / 1920), 750));
		
		this.jPanelContainer.setSize(new Dimension(JFrameMain.WIDTH_FRAME - (100 * JFrameMain.WIDTH_SCREEN / 1920),
				750 * JFrameMain.HEIGHT_SCREEN / 1080));

		configureLabel(jLabelTitle, Constants.FONT_SIZE_APP_TITLES, Font.BOLD, Color.WHITE);
		configureLabel(jLabelPath, Constants.FONT_SIZE_APP_LABELS, Font.BOLD, Color.WHITE);
		configureLabel(jLabelHelp, Constants.FONT_SIZE_APP_PLACEHOLDER, Font.PLAIN, Color.WHITE);
		configureLabel(jLabelResult, Constants.FONT_SIZE_APP_LABELS, Font.BOLD, Color.WHITE);
		configureButtons(jButtonLoadFile, Constants.COLOR_BACKGROUND_GRADIENT_TWO, Constants.FONT_SIZE_APP_BUTTONS,
				Controller.getInstance(), Constants.COMMAND_LOAD_FILE, null);
		configureButtons(jButtonStart, Constants.COLOR_BUTTONS_METHODS, Constants.FONT_SIZE_APP_BUTTONS,
				Controller.getInstance(), Constants.COMMAND_START, null);

		this.jLabelTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		this.jLabelPath.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		this.jLabelResult.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

		configureButtons(jButtonP1, Constants.COLOR_BACKGROUND_CONTENT, Constants.FONT_SIZE_APP_TITLES,
				JFrameMain.getInstance(), Constants.COMMAND_TEST1, JButtonTest1);
		configureButtons(jButtonP2, Constants.COLOR_BACKGROUND_CONTENT, Constants.FONT_SIZE_APP_TITLES,
				JFrameMain.getInstance(), Constants.COMMAND_TEST2, JButtonTest2);
		configureButtons(jButtonP3, Constants.COLOR_BACKGROUND_CONTENT, Constants.FONT_SIZE_APP_TITLES,
				JFrameMain.getInstance(), Constants.COMMAND_TEST3, JButtonTest3);
		configureButtons(jButtonP4, Constants.COLOR_BACKGROUND_CONTENT, Constants.FONT_SIZE_APP_TITLES,
				JFrameMain.getInstance(), Constants.COMMAND_TEST4, JButtonTest4);
		configureButtons(jButtonP5, Constants.COLOR_BACKGROUND_CONTENT, Constants.FONT_SIZE_APP_TITLES,
				JFrameMain.getInstance(), Constants.COMMAND_TEST5, JButtonTest5);

		configureJButtonsTest(JButtonTest1);
		configureJButtonsTest(JButtonTest2);
		configureJButtonsTest(JButtonTest3);
		configureJButtonsTest(JButtonTest4);
		configureJButtonsTest(JButtonTest5);

		addComponentsTop();
		addComponentsCenter();
		addComponentsBottom();
		this.add(jScrollPane);
		animate();
	}

	private void configureJButtonsTest(JButton button) {
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}

	private void animate() {
		new Thread(() -> {
			String text = Constants.HELP;
			while (true) {
				try {
					for (int i = 0; i < text.length(); i++) {
						jLabelHelp.setText(jLabelHelp.getText() + text.charAt(i) + "");
						Thread.sleep(100);
					}
					Thread.sleep(5000);
					jLabelHelp.setText("");
				} catch (InterruptedException e) {
				}
			}
		}).start();
	}

	private void addComponentsTop() {
		jLabelTitle.setVisible(true);
		jButtonLoadFile.setVisible(true);
		jButtonStart.setVisible(true);
		jLabelPath.setVisible(true);
		jLabelHelp.setVisible(true);
		this.jPanelContainer.add(jLabelTitle).setBounds(30 * JFrameMain.WIDTH_SCREEN / 1920,
				20 * JFrameMain.HEIGHT_SCREEN / 1080, JFrameMain.WIDTH_FRAME - (60 * JFrameMain.WIDTH_SCREEN / 1920),
				40 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.jPanelContainer.add(jButtonLoadFile).setBounds((JFrameMain.WIDTH_FRAME / 4) + 30,
				80 * JFrameMain.HEIGHT_SCREEN / 1080, 250 * JFrameMain.WIDTH_SCREEN / 1920,
				40 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.jPanelContainer.add(jButtonStart).setBounds((JFrameMain.WIDTH_FRAME / 2) + 10,
				80 * JFrameMain.HEIGHT_SCREEN / 1080, 250 * JFrameMain.WIDTH_SCREEN / 1920,
				40 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.jPanelContainer.add(jLabelPath).setBounds(200, 150 * JFrameMain.HEIGHT_SCREEN / 1080,
				900 * JFrameMain.WIDTH_SCREEN / 1920, 30 * JFrameMain.HEIGHT_SCREEN / 1080);
		this.jPanelContainer.add(jLabelHelp).setBounds(30 * JFrameMain.WIDTH_SCREEN / 1920,
				200 * JFrameMain.HEIGHT_SCREEN / 1080, JFrameMain.WIDTH_FRAME - (60 * JFrameMain.WIDTH_SCREEN / 1920),
				20 * JFrameMain.HEIGHT_SCREEN / 1080);
	}

	private void addComponentsCenter() {
		coordy = 240 * JFrameMain.HEIGHT_SCREEN / 1080;
		int aux = 0, aux2 = 0, contentH = 0;
		for (int i = 0; i < activeButtons.size(); i++) {
			aux2 = (boolean) activeButtons.get(i)[1] ? aux2 + 1 : aux;
			((JButton) activeButtons.get(i)[0]).setVisible((boolean) activeButtons.get(i)[1]);
			aux = (boolean) activePanels.get(i)[1] ? aux + 1 : aux;
			((JPanel) activePanels.get(i)[0]).setVisible((boolean) activePanels.get(i)[1]);
		}
		for (int i = 0; i < activeButtons.size(); i++) {
			this.jPanelContainer.add((JButton) activeButtons.get(i)[0]).setBounds(40 * JFrameMain.WIDTH_SCREEN / 1920,
					coordy, JFrameMain.WIDTH_FRAME - (100 * JFrameMain.WIDTH_SCREEN / 1920),
					55 * JFrameMain.HEIGHT_SCREEN / 1080);
			coordy += 62 * JFrameMain.HEIGHT_SCREEN / 1080;
			contentH = ((240 * JFrameMain.HEIGHT_SCREEN / 1080) + (((55 * JFrameMain.HEIGHT_SCREEN / 1080) * aux2)
					+ ((400 * JFrameMain.HEIGHT_SCREEN / 1080) * aux) + ((7 * JFrameMain.HEIGHT_SCREEN / 1080) * aux2)
					+ ((7 * JFrameMain.HEIGHT_SCREEN / 1080) * aux)));
			if ((boolean) activePanels.get(i)[1]) {
				deployPanel(contentH, i);
			}
			if (jPanelContainer.getHeight() > contentH) {
				jPanelContainer.setPreferredSize(
						new Dimension(1250 * JFrameMain.WIDTH_SCREEN / 1920, (200 * JFrameMain.HEIGHT_SCREEN / 1080)
								+ (jPanelContainer.getHeight() - (jPanelContainer.getHeight() - contentH))));
			}
		}
	}

	private void deployPanel(int contentH, int i) {
		this.jPanelContainer.add((JPanel) activePanels.get(i)[0]).setBounds(40 * JFrameMain.WIDTH_SCREEN / 1920, coordy,
				JFrameMain.WIDTH_FRAME - (100 * JFrameMain.WIDTH_SCREEN / 1920), 500 * JFrameMain.HEIGHT_SCREEN / 1080);
		if (contentH > jPanelContainer.getHeight()) {
			jPanelContainer.setPreferredSize(new Dimension(1250 * JFrameMain.WIDTH_SCREEN / 1920,
					jPanelContainer.getHeight() + 500 * JFrameMain.HEIGHT_SCREEN / 1080));
		}
		coordy += 507 * JFrameMain.HEIGHT_SCREEN / 1080;
	}

	private void addComponentsBottom() {
		jLabelResult.setVisible(true);
		jLabelImage.setVisible(true);
		coordy += 10 * JFrameMain.HEIGHT_SCREEN / 1080;
		this.jPanelContainer.add(jLabelResult).setBounds(
				(1250 * JFrameMain.WIDTH_SCREEN / 1920) / 2 - (200 * JFrameMain.WIDTH_SCREEN / 1920), coordy,
				400 * JFrameMain.WIDTH_SCREEN / 1920, 30 * JFrameMain.HEIGHT_SCREEN / 1080);
		coordy += 40;
		this.jPanelContainer.add(jLabelImage).setBounds(
				(1250 * JFrameMain.WIDTH_SCREEN / 1920) / 2 - (300 * JFrameMain.WIDTH_SCREEN / 1920), coordy,
				600 * JFrameMain.WIDTH_SCREEN / 1920, 100 * JFrameMain.HEIGHT_SCREEN / 1080);
	}

	private void configureButtons(JButton jButton, Color colorBackground, int size, ActionListener actionListener,
			String actionCommand, JButton buttonTest) {
		jButton.setFont(new Font(Constants.FONT_APP, Font.BOLD, size));
		jButton.setForeground(Color.WHITE);
		jButton.setFocusPainted(false);
		jButton.setBackground(colorBackground);
		jButton.setBorder(new LineBorder(Color.WHITE));
		jButton.addActionListener(actionListener);
		jButton.setActionCommand(actionCommand);
		jButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				jButton.setBorder(new LineBorder(Color.YELLOW));
				jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jButton.setBorder(new LineBorder(Color.WHITE));
				jButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});
		if (buttonTest != null) {
			jButton.setLayout(new BorderLayout());
			jButton.add(buttonTest, BorderLayout.EAST);
		}
	}

	private void configureLabel(JLabel jLabel, int fontSize, int style, Color color) {
		jLabel.setFont(new Font(Constants.FONT_APP, style, fontSize));
		jLabel.setForeground(color);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new GradientPaint(0, 0, Constants.COLOR_BACKGROUND_GRADIENT_ONE, 0, getHeight(),
				Constants.COLOR_BACKGROUND_GRADIENT_TWO));
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.WHITE);
	}

	public void showPanel(int index) {
		activePanels.get(index)[1] = !((boolean) activePanels.get(index)[1]);
		reOrganize();
	}

	public void removeComponents() {
		for (int i = 0; i < this.jPanelContainer.getComponentCount(); i++) {
			this.jPanelContainer.getComponent(i).setVisible(false);
		}
		this.jPanelContainer.removeAll();
	}

	public void reOrganize() {
		removeComponents();
		addComponentsTop();
		addComponentsCenter();
		addComponentsBottom();
	}

	public void showPath(String path) {
		this.jLabelPath.setText(path);
	}
}
