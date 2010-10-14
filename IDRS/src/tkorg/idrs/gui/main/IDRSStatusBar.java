package tkorg.idrs.gui.main;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.tree.DefaultTreeCellRenderer;

public class IDRSStatusBar extends JPanel{
	
	private static final long serialVersionUID = 1L;
	public static JLabel idrsStatus = null;
	public static JProgressBar idrsProgressBar = null;
	public static JLabel idrsProcessMessage = null;
	
	public IDRSStatusBar() {
		super();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(this.getIDRSStatus());
		this.add(this.getIDRSProgressBar());
		this.add(this.getIDRSProcessMessage());		
		this.setMessage(IDRSResourceBundle.res.getString("copyright"));
	}
	
	/**
	 * 
	 * @param isLoading
	 * @author Huynh Minh Duc
	 */
	public void setLoadingStatusBar(boolean isLoading) {
		if(isLoading) {
			this.setMessage("Loading : ");
			this.idrsProgressBar.setIndeterminate(true);
			this.idrsProgressBar.setVisible(true);
		}else {
			this.setMessage(IDRSResourceBundle.res.getString("copyright"));
			this.idrsProgressBar.setIndeterminate(false);
			this.idrsProgressBar.setVisible(false);
			this.idrsProcessMessage.setText("");
		}
	}
	
	/**
	 * 
	 * @return
	 * @author Huynh Minh Duc
	 */
	private JProgressBar getIDRSProgressBar() {
		if (idrsProgressBar == null) {
			idrsProgressBar = new JProgressBar(0, 100);
			idrsProgressBar.setValue(0);
			idrsProgressBar.setVisible(false);
		}
		return idrsProgressBar;
	}
	
	private JLabel getIDRSStatus() {
		if(idrsStatus == null) {
			idrsStatus = new JLabel();
		}
		return idrsStatus;
	}
	
	/**
	 * 
	 * @return
	 * @author Huynh Minh Duc
	 */
	private JLabel getIDRSProcessMessage() {
		if(idrsProcessMessage == null) {
			idrsProcessMessage = new JLabel();
		}
		return idrsProcessMessage;
	}
	
	/**
	 * 
	 * @param message
	 * @author Huynh Minh Duc
	 */
	public void setProcessMessage(String message) {
		idrsProcessMessage.setText(message);
	}
	/**
	 * 
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		idrsStatus.setText(" " + message);
	}
	
	/**
	 * 
	 * 
	 */
	public static void updateTextOfComponents() { 
		//setMessage(IDRSResourceBundle.res.getString("copyright"));
	}
}
