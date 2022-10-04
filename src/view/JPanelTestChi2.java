package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.utils.Constants;
import view.utils.JTableMethod;

public class JPanelTestChi2 extends JPanel {

	private JPanel jPanelContent1;
	private JPanel jPanelContent2;

	private JPanel jPanelBottom1;
	private JPanel jPanelBottom2;

	private JLabel jLabelEntries;
	private JLabel jLabelCriteries;
	private JLabel jLabelResult;
	private JLabel jLabelResultText;

	private JTableMethod jTableEntries;
	private JTableMethod jTableCriteries;
	private JTableMethod jTableResult;

	private JButton jButtonViewTableChi2;
	private GridBagConstraints gbc;

	public JPanelTestChi2(Object... objects) {
		super(new GridBagLayout());
		ArrayList<Object[]> entries = new ArrayList<Object[]>();
		entries.add(new Object[] { 1, 0.21312 });
		entries.add(new Object[] { 2, 0.21312 });
		entries.add(new Object[] { 3, 0.21312 });
		entries.add(new Object[] { 4, 0.21312 });
		entries.add(new Object[] { 5, 0.21312 });
		entries.add(new Object[] { 6, 0.21312 });
		entries.add(new Object[] { 7, 0.21312 });
		entries.add(new Object[] { 8, 0.21312 });
		entries.add(new Object[] { 9, 0.21312 });
		entries.add(new Object[] { 10, 0.21312 });
		entries.add(new Object[] { 11, 0.21312 });
		String[] entriesNames = new String[] { "i", "xi" };

		ArrayList<Object[]> criteries = new ArrayList<Object[]>();
		criteries.add(new Object[] { 0, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 1, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 2, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 3, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 4, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 5, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 6, 0, 0.1, 0.5432, 0.025, 0.340});
		criteries.add(new Object[] { 7, 0, 0.1, 0.5432, 0.025, 0.340});

		String[] criteriesNames = new String[] { "i", "inicial", "final", "Frec. Obt", "Frec. Esp", "Chi2"};

		ArrayList<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { 0.06, 7, 0.1421 });

		String[] resultNames = new String[] { "Sumatoria Chi2 (Error)", "Grados de libertad", "Maximo error (Tabla)" };

		this.jPanelBottom1 = new JPanel(new GridBagLayout());
		this.jPanelBottom2 = new JPanel(new GridBagLayout());
		this.jPanelContent1 = new JPanel(new GridBagLayout());
		this.jPanelContent2 = new JPanel(new GridBagLayout());
		this.jLabelEntries = new JLabel("Entradas:         ");
		this.jLabelCriteries = new JLabel("Criterios de evaluacion:");
		this.jLabelResult = new JLabel("Resultado:");

		if (false) {
			this.jLabelResultText = new JLabel("<html><p style='text-align:justify;'>"
					+ "<font color='2FC714'>Felicidades</font>, ha superado la prueba debido a que el error obtenido (Sumatoria Chi2) no supera al máximo error de la tabla (Máximo error) para un error de 5%"
					+ "</p></html>");
		} else {
			this.jLabelResultText = new JLabel("<html><p style='text-align:justify;'>"
					+ "<font color='ff7c80'>No ha pasado la prueba</font>, debido a que el error obtenido (Sumatoria Chi2) supera al máximo error de la tabla (Máximo error) para un error de 5%"
					+ "</p></html>");
		}
		this.jTableEntries = new JTableMethod(entries, entriesNames);
		this.jTableCriteries = new JTableMethod(criteries, criteriesNames);
		this.jTableResult = new JTableMethod(result, resultNames);
		this.jButtonViewTableChi2 = new JButton("    Ver tabla    ");
		this.gbc = new GridBagConstraints();
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension());
		this.jPanelBottom1.setOpaque(false);
		this.jPanelBottom2.setOpaque(false);
		this.jPanelContent1.setOpaque(false);
		this.jPanelContent2.setOpaque(false);
		this.setBackground(new Color(46, 42, 42));
		configureLabels(jLabelEntries, Constants.FONT_SIZE_APP_TITLES, Font.BOLD);
		configureLabels(jLabelCriteries, Constants.FONT_SIZE_APP_TITLES, Font.BOLD);
		configureLabels(jLabelResult, Constants.FONT_SIZE_APP_TITLES, Font.BOLD);
		configureLabels(jLabelResultText, Constants.FONT_SIZE_APP_LABELS, Font.ITALIC);
		configureButtons(jButtonViewTableChi2);
		addItems();
	}

	private void addItems() {
		gbc.insets = new Insets(10, 20, 0, 20);
		gbc.weightx = 1;
		gbc.fill = 1;
		this.jPanelContent1.add(jLabelEntries, gbc);

		gbc.insets.top = 0;
		gbc.weighty = 1;
		gbc.gridy = 1;
		gbc.insets.bottom = 39;
		this.jPanelContent1.add(jTableEntries, gbc);

		gbc = new GridBagConstraints();
		gbc.weighty = 1;
		gbc.fill = 1;
		this.add(jPanelContent1, gbc);
		gbc.weightx = 1;
		gbc.gridx = 1;
		this.add(jPanelContent2, gbc);

		gbc = new GridBagConstraints();
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.insets.right = 20;
		gbc.insets.top = 10;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		this.jPanelContent2.add(jLabelCriteries, gbc);
		gbc.insets.top = 0;
		gbc.gridy = 1;
		gbc.weighty = 4;
		gbc.gridx = 0;
		this.jPanelContent2.add(jTableCriteries, gbc);
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		gbc.gridy = 2;
		this.jPanelContent2.add(jPanelBottom1, gbc);
		gbc.gridx = 1;
		this.jPanelContent2.add(jPanelBottom2, gbc);

		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;
		this.jPanelBottom1.add(jLabelResult, gbc);
		gbc.gridy = 1;
		this.jPanelBottom1.add(jLabelResultText, gbc);
		
		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = 1;
		gbc.insets.top = 20;
		this.jPanelBottom2.add(jTableResult, gbc);
		gbc.insets.top = 0;
		gbc.insets.bottom = 20;
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.fill =0;
		gbc.insets.right = 60;
		gbc.insets.left = 60;
		this.jPanelBottom2.add(jButtonViewTableChi2, gbc);
		
		

	}

	private void configureButtons(JButton jButton) {
		jButton.setFont(new Font(Constants.FONT_APP, Font.PLAIN, Constants.FONT_SIZE_APP_PLACEHOLDER));
		jButton.setForeground(Color.WHITE);
		jButton.setBackground(Constants.COLOR_BUTTONS_METHODS);
		jButton.setBorder(new LineBorder(Color.WHITE));
		jButton.setFocusPainted(false);
		jButton.addActionListener((e) -> {
			JDialog jDialog = new JDialog(JFrameMain.getInstance(), true);
			jDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			jDialog.setSize(960,740);
			jDialog.setLocationRelativeTo(null);
			jDialog.setResizable(false);

			JLabel jLabelImage = new JLabel(new ImageIcon(
					new ImageIcon("src/res/tablachi2.png").getImage().getScaledInstance(950, 700, Image.SCALE_SMOOTH)));
			jDialog.add(jLabelImage);
			jDialog.setVisible(true);
		});
	}

	private void configureLabels(JLabel jLabel, int fontSize, int style) {
		jLabel.setFont(new Font(Constants.FONT_APP, style, fontSize));
		jLabel.setForeground(Color.WHITE);
	}

}
