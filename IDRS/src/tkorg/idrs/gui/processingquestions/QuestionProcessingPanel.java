package tkorg.idrs.gui.processingquestions;

import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class QuestionProcessingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public QuestionProcessingPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		setSize(320, 240);
	}

}
