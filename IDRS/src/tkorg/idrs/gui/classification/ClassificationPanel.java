package tkorg.idrs.gui.classification;

import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class ClassificationPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ClassificationPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(320, 240);
	}

}
