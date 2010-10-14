/**
 * 
 */
package tkorg.idrs.utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class MyCellTableRenderer extends JTextArea implements TableCellRenderer {
	public MyCellTableRenderer() {
		setLineWrap(true);
		setWrapStyleWord(true);
		setEditable(true);
	}
	
	
	public void setForeColor(Color color) {
		setForeground(color);
	}

	/* comment : 
	 * (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, 
													Object value, 
													boolean isSelected, 
													boolean hasFocus, 
													int row, 
													int column) {
		setText((String) value);
		setSize(table.getColumnModel().getColumn(column).getWidth(),
				getPreferredSize().height);
		if (table.getRowHeight(row) != getPreferredSize().height) {
			table.setRowHeight(row, getPreferredSize().height);
		}
		//setToolTipText(table.getModel().getValueAt(row, 1).toString());

		return this;
		
	}
} 

