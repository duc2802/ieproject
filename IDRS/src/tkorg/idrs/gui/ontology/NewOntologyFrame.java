package tkorg.idrs.gui.ontology;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class NewOntologyFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel;
	private JLabel secondLabel;
	private JTextField uriJTextField;
	private JButton continueJButton;
	private JButton cancelJButton;
	private JLabel pictureJLabel;
	private JTextArea jTextArea;
	private JScrollPane jScrollPane;

	public NewOntologyFrame() {
		initComponents();
	}

	private void initComponents() {
		setTitle("Create Ontology wizard");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tkorg/idrs/resources/image/duc.png")));
		setLayout(new GroupLayout());
		add(getJPanel(), new Constraints(new Bilateral(89, 12, 0), new Leading(34, 201, 10, 10)));
		add(getContinueJButton(), new Constraints(new Leading(296, 10, 10), new Leading(253, 12, 12)));
		add(getCancelJButton(), new Constraints(new Leading(395, 12, 12), new Leading(253, 12, 12)));
		add(getPictureJLabel(), new Constraints(new Leading(3, 498, 10, 10), new Leading(3, 292, 10, 10)));
		setSize(504, 299);
	}

	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBorder(null);
			jScrollPane.setViewportView(getJTextArea());
		}
		return jScrollPane;
	}

	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setEditable(false);
			jTextArea.setLineWrap(true);
			jTextArea
					.setText("The ontology URI is used to identify the ontology in the context of the world wide web. Additionally, ontologies that import this ontology will use the URI for the import.  It is recommended that you  set the ontology URI to be the URL where the ontology will be published.");
			jTextArea.setWrapStyleWord(true);
			jTextArea.setBorder(null);
		}
		return jTextArea;
	}

	private JLabel getPictureJLabel() {
		if (pictureJLabel == null) {
			pictureJLabel = new JLabel();
			pictureJLabel.setIcon(new ImageIcon(getClass().getResource("/tkorg/idrs/resources/image/duc.png")));
			pictureJLabel.setVerticalAlignment(SwingConstants.TOP);
		}
		return pictureJLabel;
	}

	private JButton getCancelJButton() {
		if (cancelJButton == null) {
			cancelJButton = new JButton();
			cancelJButton.setText("Cancel");
		}
		return cancelJButton;
	}

	private JButton getContinueJButton() {
		if (continueJButton == null) {
			continueJButton = new JButton();
			continueJButton.setText("Continue");
		}
		return continueJButton;
	}

	private JTextField getUriJTextField() {
		if (uriJTextField == null) {
			uriJTextField = new JTextField();
			uriJTextField.setText("http://www.semanticweb.org/ontologies/Ontology1271315.owl");
		}
		return uriJTextField;
	}

	private JLabel getSecondLabel() {
		if (secondLabel == null) {
			secondLabel = new JLabel();
			secondLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			secondLabel.setText("Please specify the ontology URI.");
		}
		return secondLabel;
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setBackground(Color.white);
			jPanel.setOpaque(false);
			jPanel.setLayout(new GroupLayout());
			jPanel.add(getSecondLabel(), new Constraints(new Leading(23, 10, 10), new Leading(12, 12, 12)));
			jPanel.add(getUriJTextField(), new Constraints(new Leading(23, 359, 10, 10), new Leading(137, 12, 12)));
			jPanel.add(getJScrollPane(), new Constraints(new Bilateral(22, 12, 22), new Leading(39, 66, 10, 10)));
		}
		return jPanel;
	}

}
