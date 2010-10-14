package tkorg.idrs.gui.crawler;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.gui.main.IDRSResourceBundle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CrawlingFilterDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel maximizeDepthJLabel;
	private JLabel maximizeNumPagesJLabel;
	private JCheckBox pdfTypeJCheckBox;
	private JCheckBox docTypeJCheckBox;
	private JCheckBox docxTypeJCheckBox;
	private JCheckBox xmlTypeJCheckBox;
	private JCheckBox htmlTypeJCheckBox;
	private JCheckBox textTypeJCheckBox;
	private JPanel typeDocJPanel;
	public JComboBox maximizeNumPagesComboBox;
	public JSpinner maximizeFilterSpinner;
	private JPanel crawlingFilterPanel;
	private JButton closeJButton;
	private JButton okButton;
	
	private JFrame filterJframe;
	private JDialog mainDialog;
	private int width = 510;
	private int height = 340;
	private int xLocation;
	private int yLocation;
	
	private JLabel maximizeThreadsJLabel;
	private JSpinner maximizeThreadsJSpinner;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	public CrawlingFilterDialog() {
		initComponents();
	}

	public CrawlingFilterDialog(JFrame mainFrame) {
		super(mainFrame, true);
		filterJframe = mainFrame;
		mainDialog = this;
		xLocation = filterJframe.getX() + (filterJframe.getWidth() - width)/2;
		yLocation = filterJframe.getY() + (filterJframe.getHeight() - height)/2;
		initComponents();
	}

	public CrawlingFilterDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public CrawlingFilterDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public CrawlingFilterDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public CrawlingFilterDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public CrawlingFilterDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public CrawlingFilterDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public CrawlingFilterDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public CrawlingFilterDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public CrawlingFilterDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public CrawlingFilterDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public CrawlingFilterDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public CrawlingFilterDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public CrawlingFilterDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public CrawlingFilterDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle(IDRSResourceBundle.res.getString("crawling.filter"));
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getCrawlingFilterPanel(), new Constraints(new Bilateral(12, 12, 0), new Leading(12, 233, 10, 10)));
		add(getOkButton(), new Constraints(new Leading(263, 10, 10), new Trailing(12, 253, 257)));
		add(getCloseJButton(), new Constraints(new Leading(382, 10, 10), new Trailing(12, 253, 257)));
		setSize(width, height);
		setLocation(xLocation, yLocation);
		setResizable(false);
	}

	private JSpinner getMaximizeThreadsJSpinner() {
		if (maximizeThreadsJSpinner == null) {
			maximizeThreadsJSpinner = new JSpinner();
			maximizeThreadsJSpinner.setModel(new SpinnerNumberModel(Integer.parseInt(websphinx.workbench.Crawling.filter.getThreads()), 1, 10, 1));
		}
		return maximizeThreadsJSpinner;
	}

	private JLabel getMaximizeThreadsJLabel() {
		if (maximizeThreadsJLabel == null) {
			maximizeThreadsJLabel = new JLabel();
			maximizeThreadsJLabel.setText(IDRSResourceBundle.res.getString("maximize.threads"));
		}
		return maximizeThreadsJLabel;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText(IDRSResourceBundle.res.getString("ok"));
			okButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
				
					websphinx.workbench.Crawling.filter.setThreads(maximizeThreadsJSpinner.getValue().toString());
					websphinx.workbench.Crawling.filter.setDepth(Integer.parseInt(maximizeFilterSpinner.getValue().toString()));
					if(pdfTypeJCheckBox.isSelected() == true){
						websphinx.workbench.Crawling.filter.setPDFType(true);
					}
					else
						websphinx.workbench.Crawling.filter.setPDFType(false);
					if(docTypeJCheckBox.isSelected() == true){
						websphinx.workbench.Crawling.filter.setDocType(true);
					}
					else
						websphinx.workbench.Crawling.filter.setDocType(false);
					if(docxTypeJCheckBox.isSelected() == true){
						websphinx.workbench.Crawling.filter.setDocxType(true);
					}
					else
						websphinx.workbench.Crawling.filter.setDocxType(false);
					
					if(htmlTypeJCheckBox.isSelected() == true){
						websphinx.workbench.Crawling.filter.setHtmlType(true);
					}
					else
						websphinx.workbench.Crawling.filter.setHtmlType(false);
					
					if(xmlTypeJCheckBox.isSelected() == true){
						websphinx.workbench.Crawling.filter.setXmlType(true);
					}
					else
						websphinx.workbench.Crawling.filter.setXmlType(false);
					
					if(textTypeJCheckBox.isSelected() == true){
						websphinx.workbench.Crawling.filter.setTextType(true);
					}
					else
						websphinx.workbench.Crawling.filter.setTextType(false);
					
					JOptionPane.showMessageDialog(null, IDRSResourceBundle.res.getString("saved.changed"));
					
					mainDialog.dispose();
				}				
				
			});
		}
		return okButton;
	}

	private JButton getCloseJButton() {
		if (closeJButton == null) {
			closeJButton = new JButton();
			closeJButton.setText(IDRSResourceBundle.res.getString("close"));
			closeJButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					mainDialog.dispose();
				}
			});
		}
		return closeJButton;
	}

	private JPanel getCrawlingFilterPanel() {
		if (crawlingFilterPanel == null) {
			crawlingFilterPanel = new JPanel();
			crawlingFilterPanel.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("web.crawling.filter"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
					new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			crawlingFilterPanel.setLayout(new GroupLayout());
			crawlingFilterPanel.add(getTypeDocJPanel(), new Constraints(new Leading(29, 414, 10, 10), new Leading(86, 107, 10, 10)));
			crawlingFilterPanel.add(getMaximizeDepthJLabel(), new Constraints(new Leading(29, 119, 12, 12), new Leading(12, 12, 12)));
			crawlingFilterPanel.add(getMaximizeFilterSpinner(), new Constraints(new Leading(149, 60, 10, 10), new Leading(8, 25, 12, 12)));
			crawlingFilterPanel.add(getMaximizeNumPagesJLabel(), new Constraints(new Leading(29, 118, 12, 12), new Leading(45, 12, 12)));
			crawlingFilterPanel.add(getMaximizeNumPagesComboBox(), new Constraints(new Leading(149, 58, 12, 12), new Leading(43, 12, 12)));
			crawlingFilterPanel.add(getMaximizeThreadsJLabel(), new Constraints(new Leading(250, 10, 10), new Leading(12, 12, 12)));
			crawlingFilterPanel.add(getMaximizeThreadsJSpinner(), new Constraints(new Leading(356, 61, 10, 10), new Leading(8, 29, 12, 12)));
		}
		return crawlingFilterPanel;
	}

	private JSpinner getMaximizeFilterSpinner() {
		if (maximizeFilterSpinner == null) {
			maximizeFilterSpinner = new JSpinner();
			maximizeFilterSpinner.setModel(new SpinnerNumberModel(websphinx.workbench.Crawling.filter.getDepth(), 1, 10, 1));
		}
		return maximizeFilterSpinner;
	}

	private JComboBox getMaximizeNumPagesComboBox() {
		if (maximizeNumPagesComboBox == null) {
			maximizeNumPagesComboBox = new JComboBox();
			maximizeNumPagesComboBox.setModel(new DefaultComboBoxModel(new Object[] { "10", "20", "30" }));
			maximizeNumPagesComboBox.setDoubleBuffered(false);
			maximizeNumPagesComboBox.setBorder(null);
		}
		return maximizeNumPagesComboBox;
	}

	private JPanel getTypeDocJPanel() {
		if (typeDocJPanel == null) {
			typeDocJPanel = new JPanel();
			typeDocJPanel.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("type.document"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			typeDocJPanel.setLayout(new GroupLayout());
			typeDocJPanel.add(getPdfTypeJCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(51, 10, 10)));
			typeDocJPanel.add(getDocTypeJCheckBox(), new Constraints(new Leading(8, 8, 8), new Leading(8, 8, 8)));
			typeDocJPanel.add(getdocxTypeJCheckBox(), new Constraints(new Leading(150, 10, 10), new Leading(8, 8, 8)));
			typeDocJPanel.add(getXmlTypeJCheckBox(), new Constraints(new Leading(296, 10, 10), new Leading(8, 8, 8)));
			typeDocJPanel.add(getHtmlTypeJCheckBox(), new Constraints(new Leading(150, 8, 8), new Leading(51, 10, 10)));
			typeDocJPanel.add(getTextTypeJCheckBox(), new Constraints(new Leading(296, 8, 8), new Leading(51, 10, 10)));
		}
		return typeDocJPanel;
	}

	private JCheckBox getTextTypeJCheckBox() {
		if (textTypeJCheckBox == null) {
			textTypeJCheckBox = new JCheckBox();
			textTypeJCheckBox.setSelected(websphinx.workbench.Crawling.filter.getTextType());
			textTypeJCheckBox.setText(IDRSResourceBundle.res.getString("text"));
		}
		return textTypeJCheckBox;
	}

	private JCheckBox getHtmlTypeJCheckBox() {
		if (htmlTypeJCheckBox == null) {
			htmlTypeJCheckBox = new JCheckBox();
			htmlTypeJCheckBox.setSelected(websphinx.workbench.Crawling.filter.getHtmlType());
			htmlTypeJCheckBox.setText(IDRSResourceBundle.res.getString("html"));
		}
		return htmlTypeJCheckBox;
	}

	private JCheckBox getXmlTypeJCheckBox() {
		if (xmlTypeJCheckBox == null) {
			xmlTypeJCheckBox = new JCheckBox();
			xmlTypeJCheckBox.setSelected(websphinx.workbench.Crawling.filter.getXmlType());
			xmlTypeJCheckBox.setText(IDRSResourceBundle.res.getString("xml"));
		}
		return xmlTypeJCheckBox;
	}

	private JCheckBox getdocxTypeJCheckBox() {
		if (docxTypeJCheckBox == null) {
			docxTypeJCheckBox = new JCheckBox();
			docxTypeJCheckBox.setSelected(websphinx.workbench.Crawling.filter.getDocxType());
			docxTypeJCheckBox.setText(IDRSResourceBundle.res.getString("docx"));
		}
		return docxTypeJCheckBox;
	}

	private JCheckBox getDocTypeJCheckBox() {
		if (docTypeJCheckBox == null) {
			docTypeJCheckBox = new JCheckBox();
			docTypeJCheckBox.setSelected(websphinx.workbench.Crawling.filter.getDocType());
			docTypeJCheckBox.setText(IDRSResourceBundle.res.getString("doc"));
		}
		return docTypeJCheckBox;
	}

	private JCheckBox getPdfTypeJCheckBox() {
		if (pdfTypeJCheckBox == null) {
			pdfTypeJCheckBox = new JCheckBox();
			pdfTypeJCheckBox.setSelected(websphinx.workbench.Crawling.filter.getPDFType());			
			pdfTypeJCheckBox.setText(IDRSResourceBundle.res.getString("pdf"));
		}
		return pdfTypeJCheckBox;
	}

	private JLabel getMaximizeNumPagesJLabel() {
		if (maximizeNumPagesJLabel == null) {
			maximizeNumPagesJLabel = new JLabel();
			maximizeNumPagesJLabel.setText(IDRSResourceBundle.res.getString("maximize.pages"));
		}
		return maximizeNumPagesJLabel;
	}

	private JLabel getMaximizeDepthJLabel() {
		if (maximizeDepthJLabel == null) {
			maximizeDepthJLabel = new JLabel();
			maximizeDepthJLabel.setText(IDRSResourceBundle.res.getString("maximize.depth"));
		}
		return maximizeDepthJLabel;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				CrawlingFilterDialog dialog = new CrawlingFilterDialog();
				dialog
						.setDefaultCloseOperation(CrawlingFilterDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle(IDRSResourceBundle.res.getString("Crawling.Filter.Dialog"));
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
