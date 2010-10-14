package tkorg.idrs.gui.main;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AboutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JTextArea jTextArea0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JPanel jPanel3;

	public AboutFrame() {
		initComponents();
	}

	private void initComponents() {
		setTitle("About IDRS");
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(0, 172, 10, 10), new Leading(0, 257, 10, 10)));
		add(getJPanel1(), new Constraints(new Bilateral(173, 0, 0), new Leading(0, 257, 12, 12)));
		add(getJPanel2(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(258, 0, 0)));
		setSize(619, 327);
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJButton1(), new Constraints(new Leading(114, 31, 12, 12), new Leading(17, 29, 10, 10)));
			jPanel3.add(getJButton0(), new Constraints(new Leading(163, 31, 12, 12), new Leading(17, 29, 12, 12)));
			jPanel3.add(getJButton2(), new Constraints(new Leading(516, 10, 10), new Leading(18, 12, 12)));
		}
		return jPanel3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("OK");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setIcon(new ImageIcon(getClass().getResource("/tkorg/idrs/resources/image/del.png")));
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setIcon(new ImageIcon(getClass().getResource("/tkorg/idrs/resources/image/edit.png")));
		}
		return jButton0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJPanel3(), new Constraints(new Leading(2, 619, 10, 10), new Leading(0, 63, 10, 10)));
		}
		return jPanel2;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBackground(Color.white);
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane0(), new Constraints(new Bilateral(-1, 0, 22), new Leading(-1, 256, 10, 10)));
		}
		return jPanel1;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTextArea0());
		}
		return jScrollPane0;
	}

	private JTextArea getJTextArea0() {
		if (jTextArea0 == null) {
			jTextArea0 = new JTextArea();
			String temp = "";
			temp += "IDRS \n";
			temp += "Version: 1.0. \n";
			temp += "This product includes software developed by Th.S Tin.";
			jTextArea0.setText(temp);
		}
		return jTextArea0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBackground(new Color(255, 255, 128));
			jPanel0.setLayout(new GroupLayout());
		}
		return jPanel0;
	}

}
