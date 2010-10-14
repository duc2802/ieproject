/**
 * Created by: HNTin & Date: Jun 1, 2010
 */
package tkorg.idrs.gui.extraction;


import gate.creole.ExecutionException;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.action.extraction.MetadataExtractionAction;
import tkorg.idrs.core.extraction.PaperCollection;
import tkorg.idrs.core.extraction.PaperObject;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;


public class MetadataExtractionLeftPanel extends JPanel {
	
	private JPanel actionJPanel;
	private JButton runJButton;
	private JButton exportToXMLJButton;
	private JButton manageMetadataSetJButton;
	private JComboBox extractionMethodsJComboBox;
	private JLabel methodsJLabel;
	private JPanel corpusFileListJPanel;
	private JScrollPane corpusFileListJScrollPane;
	public static JList corpusFileListJList;
	
	public MetadataExtractionLeftPanel() {
		this.setLayout(new GroupLayout());
		this.add(getActionJPanel(), new Constraints(new Bilateral(0, 0, 0), new Trailing(0, 157, 10, 10)));
		this.add(getCorpusFileListJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 169, 0)));
	}	
	
	private JPanel getActionJPanel() {
		if (actionJPanel == null) {
			actionJPanel = new JPanel();
			actionJPanel.setLayout(new GroupLayout());
			actionJPanel.add(getExportToXMLJButton(), new Constraints(new Bilateral(0, 0, 81), new Bilateral(127, 0, 26)));
			actionJPanel.add(getRunJButton(), new Constraints(new Bilateral(0, 0, 81), new Leading(95, 30, 10, 10)));
			actionJPanel.add(getManageMetadataSetJButton(), new Constraints(new Bilateral(0, 0, 81), new Leading(61, 32, 10, 10)));
			actionJPanel.add(getExtractionMethodsJComboBox(), new Constraints(new Bilateral(63, 0, 60), new Leading(18, 32, 32)));
			actionJPanel.add(getMethodsJLabel(), new Constraints(new Leading(4, 63, 10, 10), new Leading(17, 26, 32, 32)));
		}
		return actionJPanel;
	}
	
	private JButton getExportToXMLJButton() {
		if (exportToXMLJButton == null) {
			exportToXMLJButton = new JButton();
			exportToXMLJButton.setText(IDRSResourceBundle.res.getString("export.xml"));
			exportToXMLJButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					MetadataExtractionAction.exportMetadataToXMLFile(InformationExtractionPanel.paperCollection);					
				}
				
			});
		}
		return exportToXMLJButton;
	}
	
	private JButton getRunJButton() {
		if (runJButton == null) {
			runJButton = new JButton();
			runJButton.setText("Run");
			runJButton.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Thread thread = new Thread(new Runnable(){

						@Override
						public void run() {
							try {
								IDRSApplication.idrsStatus.setLoadingStatusBar(true);
								IDRSApplication.idrsJFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								MetadataExtractionAction action = new MetadataExtractionAction();												
								PaperCollection resultCollection = action.extractMetadata();
								PaperCollection.tranferData(resultCollection, InformationExtractionPanel.paperCollection);
								IDRSApplication.idrsJFrame.setCursor(null);
								IDRSApplication.idrsStatus.setLoadingStatusBar(false);
								JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, 
										IDRSResourceBundle.res.getString("extract.metadata.finished"), 
										IDRSResourceBundle.res.getString("message"), 
										JOptionPane.YES_OPTION, 
										new ImageIcon(getClass().getResource(GUIProperties.OK_IMAGE_FILE)));
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, 
										e2.getMessage(), 
										IDRSResourceBundle.res.getString("message"), 
										JOptionPane.YES_OPTION, 
										new ImageIcon(getClass().getResource(GUIProperties.CANCEL_IMAGE_FILE)));
							}
						}
						
					});
					
					thread.start();
				}
			});
		}
		return runJButton;
	}
	
	private JButton getManageMetadataSetJButton() {
		if (manageMetadataSetJButton == null) {
			manageMetadataSetJButton = new JButton();
			manageMetadataSetJButton.setText(IDRSResourceBundle.res.getString("manage.metadata.set"));
			
		}
		return manageMetadataSetJButton;
	}
	
	private JComboBox getExtractionMethodsJComboBox() {
		if (extractionMethodsJComboBox == null) {
			extractionMethodsJComboBox = new JComboBox();
			extractionMethodsJComboBox.setModel(new DefaultComboBoxModel(new Object[] {IDRSResourceBundle.res.getString("jape.grammar.rule")}));
			extractionMethodsJComboBox.setDoubleBuffered(false);
			extractionMethodsJComboBox.setBorder(null);
		}
		return extractionMethodsJComboBox;
	}
	
	private JLabel getMethodsJLabel() {
		if (methodsJLabel == null) {
			methodsJLabel = new JLabel();
			methodsJLabel.setText(IDRSResourceBundle.res.getString("methods"));
		}
		return methodsJLabel;
	}
	
	private JPanel getCorpusFileListJPanel() {
		if (corpusFileListJPanel == null) {
			corpusFileListJPanel = new JPanel();
			corpusFileListJPanel.setLayout(new GroupLayout());
			corpusFileListJPanel.add(getCorpusFileListJScrollPane(), new Constraints(new Bilateral(0, 0, 22), new Bilateral(1, 0, 22)));
		}
		return corpusFileListJPanel;
	}

	private JScrollPane getCorpusFileListJScrollPane() {
		if (corpusFileListJScrollPane == null) {
			corpusFileListJScrollPane = new JScrollPane();
			corpusFileListJScrollPane.setViewportView(getCorpusFileListJList());
		}
		return corpusFileListJScrollPane;
	}
	
	private JList getCorpusFileListJList() {
		if (corpusFileListJList == null) {			
			corpusFileListJList = new JList();			
		}
		return corpusFileListJList;
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getActionJPanel(), new Constraints(new Bilateral(0, 0, 0), new Trailing(0, 157, 10, 10)));
		add(getCorpusFileListJPanel(), new Constraints(new Bilateral(0, 0, 0), new Bilateral(0, 169, 0)));
		setSize(486, 327);
	}
}
