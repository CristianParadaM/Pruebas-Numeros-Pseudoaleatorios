package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import view.utils.Constants;
import view.utils.JTableMethod;

public class JPanelTestKS extends JPanel {

	private JPanel jPanelContent1;
	private JPanel jPanelContent2;

	private JPanel jPanelBottom1;
	private JPanel jPanelBottom2;

	private JLabel jLabelEntries;
	private JLabel jLabelCriteries;
	private JLabel jLabelResult;
	private JLabel jLabelResultText;

	private JTableMethod jTableEntries;
	private JTableMethod jTableCriteries2;
	private JTableMethod jTableCriteries;
	private JTableMethod jTableResult;

	private JButton jButtonViewTableKS;

	private GridBagConstraints gbc;

	public JPanelTestKS(Object... objects) {
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
		criteries.add(new Object[] { "95%", "5%", 50, 0.5432, 0.025, 1.962 });
		String[] criteriesNames = new String[] { "aceptacion", "α", "n", "Media", "Min", "Max" };

		ArrayList<Object[]> criteries2 = new ArrayList<Object[]>();
		criteries2.add(new Object[] { 0, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });
		criteries2.add(new Object[] { 1, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });
		criteries2.add(new Object[] { 2, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });
		criteries2.add(new Object[] { 3, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });
		criteries2.add(new Object[] { 4, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });
		criteries2.add(new Object[] { 5, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });
		criteries2.add(new Object[] { 6, 0, 0.1, 0.5432, 0.025, 0.340, 0.2314, 0.340, 0.12 });

		String[] criteries2Names = new String[] { "i", "inicial", "final", "Frec. Obt", "Frec. Obt. A", "Prob. Obt",
				"Frec. Esp A.", "Prob. Esp", "Diferencia" };

		ArrayList<Object[]> result = new ArrayList<Object[]>();
		result.add(new Object[] { 0.06, 0.18841 });

		String[] resultNames = new String[] { "DMax", "DMaxP" };

		this.jPanelBottom1 = new JPanel(new GridBagLayout());
		this.jPanelBottom2 = new JPanel(new GridBagLayout());
		this.jPanelContent1 = new JPanel(new GridBagLayout());
		this.jPanelContent2 = new JPanel(new GridBagLayout());
		this.jLabelEntries = new JLabel("Entradas:         ");
		this.jLabelCriteries = new JLabel("Criterios de evaluacion:");
		this.jLabelResult = new JLabel("Resultado:");
		if (false) {
			this.jLabelResultText = new JLabel("<html><p style='text-align:justify;'>"
					+ "<font color='2FC714'>Felicidades</font>, ha superado la prueba debido a que la máxima diferencia DMax no supera a la máxima diferencia de la tabla DMaxP para un error de 5%"
					+ "</p></html>");
		} else {
			this.jLabelResultText = new JLabel("<html><p style='text-align:justify;'>"
					+ "<font color='ff7c80'>No ha pasado la prueba</font>, debido a que la máxima diferencia (DMax) supera a la máxima diferencia de la tabla (DMaxP) para un error de 5%"
					+ "</p></html>");
		}
		this.jTableEntries = new JTableMethod(entries, entriesNames);
		this.jTableCriteries2 = new JTableMethod(criteries, criteriesNames);
		this.jTableCriteries = new JTableMethod(criteries2, criteries2Names);
		this.jTableResult = new JTableMethod(result, resultNames);
		this.jButtonViewTableKS = new JButton("    Ver tabla    ");
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
		configureButtons(jButtonViewTableKS); // TODO
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
		gbc.weighty = 1;
		gbc.gridx = 0;
		this.jPanelContent2.add(jTableCriteries, gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		this.jPanelContent2.add(jPanelBottom1, gbc);
		gbc.gridx = 1;
		this.jPanelContent2.add(jPanelBottom2, gbc);

		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = 1;
		gbc.gridwidth = 2;
		gbc.insets.top = 30;
		this.jPanelBottom1.add(jTableCriteries2, gbc);
		gbc.insets.top = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		this.jPanelBottom1.add(jTableResult, gbc);
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weighty = 0;
		gbc.gridx = 1;
		gbc.fill = 0;
		this.jPanelBottom1.add(jButtonViewTableKS, gbc);

		gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.insets.right = 20;
		this.jPanelBottom2.add(jLabelResult, gbc);
		gbc.gridy = 1;
		this.jPanelBottom2.add(jLabelResultText, gbc);

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
			jDialog.setSize(610,940);
			jDialog.setLocationRelativeTo(null);
			jDialog.setResizable(false);

			JLabel jLabelImage = new JLabel(new ImageIcon(
					new ImageIcon("src/res/tablaks.jpg").getImage().getScaledInstance(600, 900, Image.SCALE_SMOOTH)));
			jDialog.add(jLabelImage);
			jDialog.setVisible(true);
		});
	}

	private void configureLabels(JLabel jLabel, int fontSize, int style) {
		jLabel.setFont(new Font(Constants.FONT_APP, style, fontSize));
		jLabel.setForeground(Color.WHITE);
	}

}
