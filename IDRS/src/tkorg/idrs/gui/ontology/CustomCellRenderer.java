package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

public class CustomCellRenderer implements ListCellRenderer{

	 public Component getListCellRendererComponent
	    (JList list, Object value, int index,
	     boolean isSelected,boolean cellHasFocus) {
		     Component component = (Component)value;
		     component.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					JOptionPane.showMessageDialog(null, "dfldkf");
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
		    	 
		     });
		     component.setBackground
		      (isSelected ? Color.lightGray : Color.white);
		     component.setForeground
		      (isSelected ? Color.white : Color.lightGray);
		     return component;
	     }

}
