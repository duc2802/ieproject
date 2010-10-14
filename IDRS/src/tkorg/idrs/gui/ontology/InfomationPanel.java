package tkorg.idrs.gui.ontology;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.properties.files.GUIProperties;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InfomationPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel informationJLabel;
	private JLabel editJLabel;
	private JLabel deleteJLabel;

	public InfomationPanel() {
		initComponents();
	}

	public InfomationPanel(String text) {
		initComponents();
		informationJLabel.setText(text);
	}
	
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getInformationJLabel(), new Constraints(new Leading(12, 95, 10, 10), new Leading(6, 12, 12)));
		add(getEditJLabel(), new Constraints(new Trailing(44, 12, 12), new Trailing(12, 12, 12)));
		add(getDeleteJLabel(), new Constraints(new Trailing(12, 12, 12), new Leading(12, 12, 12)));
		setSize(280, 44);
	}

	private JLabel getDeleteJLabel() {
		if (deleteJLabel == null) {
			deleteJLabel = new JLabel();
			deleteJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.DEL_ICON_FILE)));
			deleteJLabel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
					JOptionPane.showConfirmDialog(null, "Congatulation! Delete");
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return deleteJLabel;
	}

	private JLabel getEditJLabel() {
		if (editJLabel == null) {
			editJLabel = new JLabel();
			editJLabel.setIcon(new ImageIcon(getClass().getResource(GUIProperties.EDIT_ICON_FILE)));
			editJLabel.setDoubleBuffered(true);
			editJLabel.setOpaque(true);
			editJLabel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
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
					JOptionPane.showConfirmDialog(null, "Congatulation! Edit");
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return editJLabel;
	}

	private JLabel getInformationJLabel() {
		if (informationJLabel == null) {
			informationJLabel = new JLabel();
		}
		return informationJLabel;
	}

}
