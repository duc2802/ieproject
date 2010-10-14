package tkorg.idrs.gui.ontology;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import tkorg.idrs.properties.files.GUIProperties;

public class JTableModelListener extends JTableLabelMouseListener{

	  public JTableModelListener(JTable table) {
		super(table);
		// TODO Auto-generated constructor stub
	}

	public void mouseClicked(MouseEvent e) {
		if(col==2){
		DefaultTableModel.class.cast(__table.getModel()).removeRow(row) ;
		col=-1;
		row=-1;
		}
		
	  }

	  public void mousePressed(MouseEvent e) {
		 
		  __forwardEventToLabel(e);
	    if(col==1)
	    label.setIcon(new ImageIcon(getClass().getResource(
				GUIProperties.EDIT_BUTTON_PRESSED_FILE)));
	    else if(col==2)
	    	 label.setIcon(new ImageIcon(getClass().getResource(
	    				GUIProperties.DELETE_BUTTON_SHOW_FILE)));
	  }

	  public void mouseReleased(MouseEvent e) {
		 
	    __forwardEventToLabel(e);
	    if(col==1)
	        label.setIcon(new ImageIcon(getClass().getResource(
	    			GUIProperties.EDIT_BUTTON_APPEAR_FILE)));
	        else if(col==2)
	        	 label.setIcon(new ImageIcon(getClass().getResource(
	        				GUIProperties.DELETE_BUTTON_PRESSED_FILE)));
	  }
}
