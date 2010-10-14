package tkorg.idrs.gui.extraction;
import gate.Document;
import gate.Factory;
import gate.util.Out;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

import com.hp.hpl.jena.graph.query.regexptrees.Text;

import tkorg.idrs.core.extraction.FactoryUtil;
import tkorg.idrs.core.extraction.GateExtractionObject;
import tkorg.idrs.core.extraction.PaperCollection;
import tkorg.idrs.core.extraction.PaperObject;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.gui.ontology.CustomCellRenderer;
import tkorg.idrs.properties.files.GUIProperties;
import tkorg.idrs.utilities.MyCellTableRenderer;

//VS4E -- DO NOT REMOVE THIS LINE!
public class InformationExtractionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel metadataExtractionJPanel;
	private JPanel buildCorpusJPanel;
	private JTabbedPane extractionJTabbedPane;
	private JSplitPane buildCorpusJSplitPane;
	private JSplitPane metadataExtractionJSplitPane;
	
	public static PaperCollection paperCollection;
	public static GateExtractionObject gateExtractionObject;
	
	public InformationExtractionPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getExtractionJTabbedPane(), new Constraints(new Bilateral(0, 0, 5), new Bilateral(0, 0, 7)));
		setSize(594, 492);
	}
	
	private JTabbedPane getExtractionJTabbedPane() {
		if (extractionJTabbedPane == null) {
			extractionJTabbedPane = new JTabbedPane();
			extractionJTabbedPane.addTab(IDRSResourceBundle.res.getString("build.corlus"), getBuildCorpusJPanel());
			extractionJTabbedPane.addTab(IDRSResourceBundle.res.getString("metadata.extraction"), getMetadataExtractionJPanel());
			extractionJTabbedPane.addMouseListener(new MouseListener(){
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					Thread thread = new Thread(new Runnable() {

						@Override
						public void run() {
							if(BuildCorpusLeftPanel.corpus.size() != 0) {
								if((extractionJTabbedPane.getSelectedIndex() + 1) == 2) {
									IDRSApplication.idrsJFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));									
									IDRSApplication.idrsStatus.setLoadingStatusBar(true);
									IDRSApplication.idrsStatus.setMessage(IDRSResourceBundle.res.getString("the.corpus.loading"));
									gateExtractionObject = new GateExtractionObject();
									MetadataExtractionLeftPanel.corpusFileListJList.repaint();
									refreshCorpusFileList();
									IDRSApplication.idrsStatus.setLoadingStatusBar(false);
									IDRSApplication.idrsJFrame.setCursor(null);
								}
							}
						}
						
					});
					thread.start();
				};
				
				public void mouseEntered(MouseEvent e){
					
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					
				};
			});
		}
		return extractionJTabbedPane;
	}
	
	/**
	 * 
	 * 
	 * @author Huynh Minh Duc
	 */
	public void refreshCorpusFileList() {
		DefaultListModel listModel = new DefaultListModel();		
		MetadataExtractionLeftPanel.corpusFileListJList.setCellRenderer(new CustomCellRenderer());
		paperCollection = new PaperCollection();
		ArrayList<Integer> isIncorectFile = new ArrayList<Integer>();
		int i = 0;
		
		for (File file : BuildCorpusLeftPanel.corpus) {		
			try {
				Document document = FactoryUtil.createDocument(file.toURI().toURL());				
				IDRSApplication.idrsStatus.setProcessMessage(file.getName());		
				
				if(GateExtractionObject.IsIncorectParser(document)) {
					isIncorectFile.add(i);
					JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, 
							IDRSResourceBundle.res.getString("the.file") + " " + i + " : " + file.getName() + " " + IDRSResourceBundle.res.getString("is.paper.incorrect.parser"), 
							IDRSResourceBundle.res.getString("message"), 
							JOptionPane.YES_OPTION, 
							new ImageIcon(getClass().getResource(GUIProperties.CANCEL_IMAGE_FILE)));
				} 
				else {
					JLabel iconLable = new JLabel();
					iconLable.setIcon(new ImageIcon(getClass().getResource(GUIProperties.PDF_ICON_FILE)));
					
					JLabel textLable = new JLabel(file.getName());
					textLable.setFont(new Font("Dialog", Font.PLAIN, 13));
					
					JPanel panel = new JPanel(new BorderLayout());
					panel.add(iconLable, BorderLayout.WEST);
					panel.add(textLable, BorderLayout.CENTER);	
					
					listModel.addElement(panel);
					PaperObject paper = new PaperObject();
					paper.setContent(document.getContent().toString());
					paper.setUrl(file.toURI().toURL().toString());
					InformationExtractionPanel.paperCollection.add(paper);
				}
				//unload persistent document from memory.
				Factory.deleteResource(document);
			} catch (Exception e) {
				// TODO: handle exception
			}
			i++;
		}		
		
		for (int integer = isIncorectFile.size() - 1; integer >= 0; integer--) {			
			BuildCorpusLeftPanel.corpus.remove((int)(isIncorectFile.get(integer)));		
			BuildCorpusRightPanel.data.remove((int)(isIncorectFile.get(integer)));
		}
		
		MetadataExtractionLeftPanel.corpusFileListJList.setModel(listModel);		
		MetadataExtractionLeftPanel.corpusFileListJList.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				
				IDRSApplication.idrsJFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				
				int index = MetadataExtractionLeftPanel.corpusFileListJList.getSelectedIndex();		
				DefaultTableModel defaultTableModel = MetadataExtractionRightPanel.createMetadateTableModel(InformationExtractionPanel.paperCollection.getPaperObject(index));
				MyCellTableRenderer myCellTableRenderer = new MyCellTableRenderer();
				
				MetadataExtractionRightPanel.metadataTable.setModel(defaultTableModel);
				MetadataExtractionRightPanel.metadataTable.getColumnModel().getColumn(1).setCellRenderer(myCellTableRenderer);			
				MetadataExtractionRightPanel.metadataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				MetadataExtractionRightPanel.metadataTable.getColumnModel().getColumn(0).setMinWidth(0);
				MetadataExtractionRightPanel.metadataTable.getColumnModel().getColumn(0).setPreferredWidth(200);
				MetadataExtractionRightPanel.metadataTable.getColumnModel().getColumn(1).setMinWidth(0);
				MetadataExtractionRightPanel.metadataTable.getColumnModel().getColumn(1).setPreferredWidth(800);
				
				MetadataExtractionRightPanel.metadataTable.repaint();
				String content = InformationExtractionPanel.paperCollection.getPaperObject(index).getContent();					
				MetadataExtractionRightPanel.refreshContent(content);
				
				IDRSApplication.idrsJFrame.setCursor(null);
				
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
	}	
	
	private JPanel getBuildCorpusJPanel() {
		if (buildCorpusJPanel == null) {
			buildCorpusJPanel = new JPanel();
			buildCorpusJPanel.setLayout(new GroupLayout());
			buildCorpusJPanel.add(getBuildCorpusJSplitPane(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 28)));
		}
		return buildCorpusJPanel;
	}

	private JSplitPane getBuildCorpusJSplitPane() {
		if (buildCorpusJSplitPane == null) {
			buildCorpusJSplitPane = new JSplitPane();
			buildCorpusJSplitPane.setDividerLocation(350);
			buildCorpusJSplitPane.setRightComponent(new BuildCorpusRightPanel());
			buildCorpusJSplitPane.setLeftComponent(new BuildCorpusLeftPanel());
		}
		return buildCorpusJSplitPane;
	}
	
	private JPanel getMetadataExtractionJPanel() {
		if (metadataExtractionJPanel == null) {
			metadataExtractionJPanel = new JPanel();
			metadataExtractionJPanel.setLayout(new GroupLayout());
			metadataExtractionJPanel.add(getMetadataExtractionJSplitPane(), new Constraints(new Bilateral(0, 0, 202), new Bilateral(0, 0, 28)));
		}
		return metadataExtractionJPanel;
	}

	private JSplitPane getMetadataExtractionJSplitPane() {
		if (metadataExtractionJSplitPane == null) {
			metadataExtractionJSplitPane = new JSplitPane();
			metadataExtractionJSplitPane.setDividerLocation(350);		
			metadataExtractionJSplitPane.setLeftComponent(new MetadataExtractionLeftPanel());
			metadataExtractionJSplitPane.setRightComponent(new MetadataExtractionRightPanel());
		}
		return metadataExtractionJSplitPane;
	}
}
