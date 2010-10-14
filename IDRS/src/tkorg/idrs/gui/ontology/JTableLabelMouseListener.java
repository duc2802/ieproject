package tkorg.idrs.gui.ontology;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

import tkorg.idrs.properties.files.GUIProperties;

public class JTableLabelMouseListener implements MouseListener {
  public JTable __table;
  Object value;
  JLabel label;
  int col=-1;
  int row=-1;

  public JTableLabelMouseListener(JTable table) {
	  __table = table;
  }
	public void __forwardEventToLabel(MouseEvent e) {
	    TableColumnModel columnModel = __table.getColumnModel();
	    int column = columnModel.getColumnIndexAtX(e.getX());
	    row    = e.getY() / __table.getRowHeight();
	    
	    MouseEvent labelEvent;

	    if(row >= __table.getRowCount() || row < 0 ||
	       column >= __table.getColumnCount() || column < 0)
	      return;

	    value = __table.getValueAt(row, column);

	    if(!(value instanceof JLabel))
	      return;
	    col=column;
	 
	    label = (JLabel)value;

	    labelEvent =
	      (MouseEvent)SwingUtilities.convertMouseEvent(__table, e, label);
	    label.dispatchEvent(labelEvent);
	  
	    __table.repaint();
	  }
  public void mouseClicked(MouseEvent e) {
	 
    __forwardEventToLabel(e);
    
  }

  public void mouseEntered(MouseEvent e) {
	
  }

  public void mouseExited(MouseEvent e) {
	
  }

  public void mousePressed(MouseEvent e) {
   
  }

  public void mouseReleased(MouseEvent e) {
	  
  }
  
}
