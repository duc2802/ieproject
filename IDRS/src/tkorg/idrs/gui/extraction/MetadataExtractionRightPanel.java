/**
 * Created by: HNTin & Date: Jun 1, 2010
 */
package tkorg.idrs.gui.extraction;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

import ch.randelshofer.quaqua.JSheet;

import tkorg.idrs.core.extraction.Author;
import tkorg.idrs.core.extraction.PaperObject;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.utilities.StringUtility;

public class MetadataExtractionRightPanel extends JPanel {

	private JSplitPane metadataExtractionRightJSplitPane;
	private JScrollPane metadataJScrollPane;
	private JPanel bottomPanel;
	private JSplitPane bottomSplitPane;
	private JScrollPane contentFileScrollPane;	
	public static JTextArea contentTextArea;
	public static JTable metadataTable;
	public static PaperObject paperShow;
	
	public MetadataExtractionRightPanel() {
		this.setLayout(new GroupLayout());
		this.add(getMetadataExtractionRightJSplitPane(), new Constraints(new Bilateral(0, 0, 101), new Bilateral(0, 0, 64)));
	}
	
	private JSplitPane getMetadataExtractionRightJSplitPane() {
		if (metadataExtractionRightJSplitPane == null) {
			metadataExtractionRightJSplitPane = new JSplitPane();
			metadataExtractionRightJSplitPane.setDividerLocation(400);
			metadataExtractionRightJSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
			metadataExtractionRightJSplitPane.setTopComponent(getMetadataJScrollPane());
			metadataExtractionRightJSplitPane.setBottomComponent(getBottomPanel());
		}
		return metadataExtractionRightJSplitPane;
	}
	
	private JTable getMetadataTable() {
		if(metadataTable == null) {
			metadataTable = new JTable();
			metadataTable.setAutoCreateRowSorter(true);			
			metadataTable.setAutoscrolls(true);
			metadataTable.setShowGrid(true);
			metadataTable.setShowHorizontalLines(true);
			metadataTable.setShowVerticalLines(true);
			metadataTable.setRowHeight(20);
			metadataTable.setModel(new DefaultTableModel());
			metadataTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			metadataTable.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getClickCount() == 1) {
						int[] rowSelection = metadataTable.getSelectedRows();
						Point locationOnScreen = new Point();
						locationOnScreen.setLocation(e.getXOnScreen(), e.getYOnScreen());
						
						switch (rowSelection[0]) {
						case 0:
							StringBuffer urlContent = new StringBuffer();
							urlContent.append("Url : ");
							urlContent.append(MetadataExtractionRightPanel.paperShow.getUrl());
							TooltipDialog.showToolTip(locationOnScreen, urlContent.toString());
							break;
						case 1:
							StringBuffer titleContent = new StringBuffer();
							titleContent.append("Title : ");
							titleContent.append(MetadataExtractionRightPanel.paperShow.getTitle());
							TooltipDialog.showToolTip(locationOnScreen, titleContent.toString());						
							break;						
						case 2:
							StringBuffer authorsString = new StringBuffer();
							if( MetadataExtractionRightPanel.paperShow.getAuthor() != null) {
								ArrayList<Author> authorList = (ArrayList) MetadataExtractionRightPanel.paperShow.getAuthor(); 
								for (Author author : authorList) {
									authorsString.append("Author : ");
									authorsString.append(author.getName());
									authorsString.append("\n ");
								}			
							}else authorsString.append("null"); 
							TooltipDialog.showToolTip(locationOnScreen, authorsString.toString());
							break;
						case 3:
							StringBuffer emailContent = new StringBuffer();
							emailContent.append("Email : \n");
							emailContent.append(MetadataExtractionRightPanel.paperShow.getEmail());
							TooltipDialog.showToolTip(locationOnScreen, emailContent.toString());						
							break;
						case 4:
							StringBuffer affiliationContent = new StringBuffer();
							affiliationContent.append("Affiliation Lines: \n");
							affiliationContent.append(MetadataExtractionRightPanel.paperShow.getAffiliation());
							TooltipDialog.showToolTip(locationOnScreen, affiliationContent.toString());						
							break;	
						case 5:
							StringBuffer publisherContent = new StringBuffer();
							publisherContent.append("Publisher : ");
							publisherContent.append(MetadataExtractionRightPanel.paperShow.getPublisher());
							TooltipDialog.showToolTip(locationOnScreen, publisherContent.toString());						
							break;						
						case 6:
							StringBuffer yearPublishContent = new StringBuffer();
							yearPublishContent.append("Year Publish : ");
							yearPublishContent.append(MetadataExtractionRightPanel.paperShow.getYearPublish());
							TooltipDialog.showToolTip(locationOnScreen, yearPublishContent.toString());
							break;
						case 7:
							StringBuffer abstractContent = new StringBuffer();
							abstractContent.append("Abstract : ");
							abstractContent.append(MetadataExtractionRightPanel.paperShow.getAbstractPaper());
							TooltipDialog.showToolTip(locationOnScreen, abstractContent.toString());
							break;
						case 8:
							
							break;
						default:
							if(MetadataExtractionRightPanel.paperShow.getReferences().get(rowSelection[0] - 9) != null) {
								PaperObject ref = MetadataExtractionRightPanel.paperShow.getReferences().get(rowSelection[0] - 9);
								StringBuffer refContent = new StringBuffer();
								refContent.append("[" + (rowSelection[0] - 8) + "].");
								refContent.append(StringUtility.removeNewLineCharacter(ref.getContent()));
								refContent.append("\n");
								refContent.append("\n");
								refContent.append("Title : ");
								refContent.append(ref.getTitle());
								refContent.append("\n");
								
								if(ref.getAuthor() != null) {
									ArrayList<Author> authorList = (ArrayList) MetadataExtractionRightPanel.paperShow.getReferences().get(rowSelection[0] - 9).getAuthor(); 
									for (Author author : authorList) {
										refContent.append("Author : ");
										refContent.append(author.getName());
										refContent.append("\n");
									}			
								}else {
									refContent.append("Author: ");
									refContent.append("\n");
								} 
								
								refContent.append("Publish Year : ");
								refContent.append(ref.getYearPublish());
								refContent.append("\n");
								
								TooltipDialog.showToolTip(locationOnScreen, refContent.toString());
							}
							break;	
						}
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					
					
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
		return metadataTable;
	}
	
	private JScrollPane getMetadataJScrollPane() {
		if (metadataJScrollPane == null) {
			metadataJScrollPane = new JScrollPane(getMetadataTable());			
		}
		return metadataJScrollPane;
	}
	
	public static DefaultTableModel createMetadateTableModel(PaperObject paperObject) {	
		paperShow = paperObject;
		
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return true;
			}
			
		};
		
		StringBuffer authorsString = new StringBuffer();
		if(paperObject.getAuthor() != null) {
			ArrayList<Author> authorList = (ArrayList)paperObject.getAuthor(); 
			for (Author author : authorList) {
				authorsString.append(author.getName());
				authorsString.append(" | ");
			}			
		}else authorsString.append("null"); 
			
		
		
		String[] metadataData = {"Url", "Title", "Authors", "Email", "Affiliation", "Publisher", "Year publish", "Abstract", "Refereneces"};
		String[] valueData = {StringUtility.showOverViewString(paperObject.getUrl()), 
				StringUtility.showOverViewString(paperObject.getTitle()),
				StringUtility.showOverViewString(authorsString.toString()),
				paperObject.getEmail(),
				paperObject.getAffiliation(),
				StringUtility.showOverViewString(paperObject.getPublisher()), 
				StringUtility.showOverViewString(paperObject.getYearPublish()),
				StringUtility.showOverViewString(paperObject.getAbstractPaper())};
		
		defaultTableModel.addColumn("Metadata", metadataData);
		defaultTableModel.addColumn("Value", valueData);
		
		if(paperObject.getReferences() != null) {
			ArrayList<PaperObject> referencesList = (ArrayList)paperObject.getReferences(); 
			int i = 1;
			for (PaperObject reference : referencesList) {
				StringBuffer referencesString = new StringBuffer();
				referencesString.append(" [" + i++ + "].");
				referencesString.append(StringUtility.showOverViewString(reference.getContent()));	
				defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[]{"", referencesString.toString()});
			}
		}else defaultTableModel.insertRow(defaultTableModel.getRowCount(), new Object[]{"", "null"});		
		
		return defaultTableModel;
	}
	
	private JPanel getBottomPanel() {
		if (bottomPanel == null) {
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new GridLayout(1, 2));
			bottomSplitPane = new JSplitPane(); 
			bottomSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			bottomSplitPane.setDividerLocation(1000);
			bottomSplitPane.setLeftComponent(getContentTextArea());			
			bottomPanel.add(getContentFilePanel());
		}
		return bottomPanel;
	}
	
	private JTextArea getContentTextArea() {
		if(contentTextArea == null) {
			contentTextArea = new JTextArea();
			contentTextArea.setAutoscrolls(true);
		}
		return contentTextArea;
	}
	
	public static void refreshContent(String content) {
		contentTextArea.setText(content);
		contentTextArea.setCaretPosition(0);
		contentTextArea.repaint();
	}
	
	private JScrollPane getContentFilePanel() {
		if (contentFileScrollPane == null) {
			contentFileScrollPane = new JScrollPane(getContentTextArea());
			
		}
		return contentFileScrollPane;
	}

}
