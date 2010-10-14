package tkorg.idrs.gui.ontology;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JTableLabelRenderer implements TableCellRenderer {
  private TableCellRenderer __defaultRenderer;

  public JTableLabelRenderer(TableCellRenderer renderer) {
	  __defaultRenderer = renderer;
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
						 boolean isSelected,
						 boolean hasFocus,
						 int row, int column)
  {
    if(value instanceof Component)
      return (Component)value;
    return __defaultRenderer.getTableCellRendererComponent(
	   table, value, isSelected, hasFocus, row, column);
  }
}