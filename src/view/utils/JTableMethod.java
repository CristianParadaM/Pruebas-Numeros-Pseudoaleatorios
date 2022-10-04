package view.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import view.JFrameMain;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 * @date 8/08/2022
 */
public class JTableMethod extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private ArrayList<Object[]> infoTable;
	private String[] columnNames;

	/**
	 * Constructor de JTableReports
	 * 
	 * @param info
	 * @param type
	 */
	public JTableMethod(ArrayList<Object[]> info, String[] columnNames) {
		super();
		this.infoTable = info;
		this.columnNames = columnNames;
		init();
	}

	/**
	 * Metodo que inicia esta tabla
	 * 
	 * @param type si es tabla de cajeros o de productos
	 */
	private void init() {
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		this.getVerticalScrollBar().setOpaque(false);
		this.getHorizontalScrollBar().setOpaque(false);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

		DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void setValue(Object value) {
				setFont(new Font(Constants.FONT_APP, Font.PLAIN, Constants.FONT_SIZE_APP_PLACEHOLDER));
				setForeground(Color.WHITE);
				setBackground(new Color(80, 78, 79));
				super.setValue(value);

			}
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
					int row, int column) {
				super.getTableCellRendererComponent(table, value, selected, focused, row, column);
				if (row % 2 == 0) {
					this.setBackground(new Color(80,78,78));
				} else {
					this.setBackground(new Color(59,56,56));
				}
				return this;
			}
		};
		cellRender.setHorizontalAlignment(JLabel.CENTER);

		TableModel dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				return infoTable.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				return infoTable.get(row)[col];
			}

			@Override
			public String getColumnName(int column) {
				return columnNames[column];
			}

			@Override
			public Class<? extends Object> getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				infoTable.get(row)[column] = aValue;
			}
		};
		this.setBorder(BorderFactory.createEmptyBorder());
		jTable = new JTable(dataModel);
		jTable.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
		jTable.getTableHeader().setFont(new Font(Constants.FONT_APP, Font.PLAIN, 20 * JFrameMain.WIDTH_FRAME / 1920));
		jTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jTable.getTableHeader().setPreferredSize(new Dimension(0, 40 * JFrameMain.HEIGHT_SCREEN / 1080));
		jTable.getTableHeader().setBackground(new Color(59,56,56));
		jTable.getTableHeader().setForeground(Color.WHITE);

		jTable.getTableHeader().setReorderingAllowed(false);

		for (int i = 0; i < columnNames.length; i++) {
			TableColumn tableColumn = jTable.getColumn(columnNames[i]);
			tableColumn.setCellRenderer(cellRender);

		}
		
		jTable.setShowGrid(false);

		this.setViewportView(jTable);
		this.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		jTable.setRowHeight(42 * JFrameMain.HEIGHT_SCREEN / 1080);
	}

}
