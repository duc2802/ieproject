package tkorg.idrs.gui.ontology;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class JTableLabelModel extends DefaultTableModel {
	protected Vector dataVector =new Vector();;
	protected Vector columnIdentifiers =new Vector();
	
	public JTableLabelModel (Vector data, Vector columnNames){
		super(data,columnNames);	
	}
 
 /* public boolean isCellEditable(int row, int column) {
    return true;
  }
*/
  public Class getColumnClass(int column) {
    return getValueAt(0, column).getClass();
  }
}
