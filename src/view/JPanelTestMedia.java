package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.utils.Constants;
import view.utils.JTableMethod;

public class JPanelTestMedia extends JPanel{
	private JTableMethod tableData;
	private JTableMethod tableInfo;
	private JLabel tittleData;
	private JLabel tittleInfo;
	private JLabel tittleResult;
	private JLabel response;
	private JPanel jPanelEntry;
	private JPanel jPanelProcess;
	private GridBagConstraints gbc;
	
	public JPanelTestMedia(ArrayList<Object[]> tableData, ArrayList<Object[]> tableInfo, boolean approved) {
		super(new GridBagLayout());
		ArrayList<Object[]> tableData1 = new ArrayList<Object[]>();
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		tableData1.add(new Object[] {1, 0.123});
		ArrayList<Object[]> tableData2 = new ArrayList<Object[]>();
		tableData2.add(new Object[] {"95%", "5%", 50, 23.4, 0.303, 0.232, 0.323, 0.321});
		this.tableData = new JTableMethod(tableData1, new String[] {"i","Ri"});
		this.tableInfo = new JTableMethod(tableData2, new String[] {"Aceptacion","α", "n", "Media", "1-(α/2)", "z", "Li", "Ls"});
		this.tittleData = new JLabel("Entradas:       ");
		this.tittleInfo = new JLabel("Criterios de evaluación");
		this.tittleResult = new JLabel("Resultados");
		this.response = new JLabel();
		this.gbc = new GridBagConstraints();
		this.jPanelEntry = new JPanel(new GridBagLayout());
		this.jPanelProcess = new JPanel(new GridBagLayout());
		init(approved);
	}




	private void init(boolean approved) {
		this.setBackground(new Color(46,42,42));
		
		gbc.weightx = 1;
		gbc.fill = 1;
		gbc.insets = new Insets(20, 20, 10, 10);
		
		jPanelEntry.setOpaque(false);
		tittleData.setFont(new Font(Constants.FONT_APP, Font.BOLD, Constants.FONT_SIZE_APP_TITLES));
		tittleData.setForeground(Color.WHITE);
		
		jPanelEntry.add(tittleData, gbc);
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.insets.top = 5;
		gbc.insets.bottom = 30;
		
		jPanelEntry.add(tableData, gbc);
		
		this.gbc = new GridBagConstraints();
		gbc.weightx = 1;
		gbc.fill = 1;	
		gbc.insets = new Insets(20, 20, 10, 10);	
		jPanelProcess.setOpaque(false);
		tittleInfo.setFont(new Font(Constants.FONT_APP, Font.BOLD, Constants.FONT_SIZE_APP_TITLES));
		tittleInfo.setForeground(Color.WHITE);
		
		jPanelProcess.add(tittleInfo, gbc);
		
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.insets.top = 5;
		
		jPanelProcess.add(tableInfo, gbc);
		
		gbc.weighty = 0;
		gbc.gridy = 2;
		gbc.insets.bottom = 20;
		
		tittleResult.setFont(new Font(Constants.FONT_APP, Font.BOLD, Constants.FONT_SIZE_APP_TITLES));
		tittleResult.setForeground(Color.WHITE);
		tittleResult.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		jPanelProcess.add(tittleResult, gbc);
		
		gbc.gridy = 3;
		gbc.insets.bottom = 70;
		
		if (approved) {
			response.setText("<html><p style = 'text-aling:justify;'><font color= '27DB3F'>Felicidades</font>, los números han pasado la prueba debido a que la media de los \n números se encuentra entre el limite inferior y el limite superior.</p></html>");
		}else {
			response.setText("<html><p style = 'text-aling:justify;'><font color= 'e67f86'>No ha pasado la prueba</font>, debido a que la media de los números <font color= 'e67f86'>NO</font> se encuentra \n entre el limite inferior y el limite superior.</p></html>");
		}
		
		response.setFont(new Font(Constants.FONT_APP, Font.ITALIC, Constants.FONT_SIZE_APP_LABELS));
		response.setForeground(Color.WHITE);
		
		jPanelProcess.add(response, gbc);
		
		this.gbc = new GridBagConstraints();
		gbc.weighty = 1;
		gbc.fill = 1;	
		
		this.add(jPanelEntry, gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 1;
		
		this.add(jPanelProcess, gbc);
	}
}
