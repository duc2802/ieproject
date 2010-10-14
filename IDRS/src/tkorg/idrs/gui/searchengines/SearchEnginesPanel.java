/**
 * author: tiendv   
 * last modify:
 */
package tkorg.idrs.gui.searchengines;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.action.searchengines.SearchEnginesAction;
import tkorg.idrs.action.searchengines.SelectHyperLinkAction;
import tkorg.idrs.action.searchengines.GetCandidateAnswersAction;
import tkorg.idrs.core.searchengines.CandidateAnswer;
import tkorg.idrs.core.searchengines.Token;
import tkorg.idrs.core.searchengines.TextChunks;


//VS4E -- DO NOT REMOVE THIS LINE!
public class SearchEnginesPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel queryLabel;
	private JTextField queryStringTextField;
	private JPanel queryPanel;

	private JCheckBox googleCheckBox;
	private JLabel googleMaxResultLabel;
	private JComboBox googleResultComboBox;
	private JComboBox yahooResultComboBox;
	private JLabel yahooMaxResultLabel;
	private JCheckBox yahooCheckBox;
	private JButton submitButton;
	private JButton downloadButton;
	private JButton processButton;
	private JTabbedPane resultTabbedPane;
	private JScrollPane googleResultScrollPane;
	private JScrollPane yahooResultScrollPane;
	private JScrollPane googleTextChunksScrollPane;
	private JScrollPane yahooTextChunksScrollPane;
	private JScrollPane googleCandidateAnswersScrollPane;
	private JScrollPane yahooCandidateAnswersScrollPane;
	private JEditorPane yahooSearchResultEditorPane;
	private JEditorPane googleSearchResultEditorPane;
	private JProgressBar downloadProgressBar;
	private JTable googleTextChunksJTable;
	private JTable yahooTextChunksJTable;
	private JTable googleCandidateAnswersJTable;
	private JTable yahooCandidateAnswersJTable;
	public static Vector<String[]> data = new Vector<String[]>();
	public static Vector<Vector> googleTextChunksdata = new Vector<Vector>();
	ArrayList<String> googleSearchResult = null, yahooSearchResult = null;
	
	public SearchEnginesPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getQueryPanel(), new Constraints(new Bilateral(9, 12, 0),new Leading(9, 141, 10, 10)));
		add(getResultTabbedPane(), new Constraints(new Bilateral(12, 12, 75),new Bilateral(152, 30, 10, 110)));
		add(getDownloadButton(), new Constraints(new Trailing(20, 10, 233),new Trailing(3, 10, 263)));
		add(getDownloadProgressBar(), new Constraints(new Bilateral(12,150,200),new Trailing(12,12,12)));
		setSize(319, 292);
		downloadButton.setEnabled(false);
	}
	private JProgressBar getDownloadProgressBar() {
		if (downloadProgressBar == null) {
			downloadProgressBar = new JProgressBar();
		}
		return downloadProgressBar;
		
	}
	private JButton getDownloadButton() {
		if (downloadButton == null) {
			downloadButton = new JButton();
			downloadButton.setText(IDRSResourceBundle.res.getString("download"));
			downloadButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					downloadButton.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					final String queryString = queryStringTextField.getText();
					Thread downloadThread = new Thread(new Runnable(){
						@Override
						public void run() {
							try {
								SearchEnginesAction.downloadSeachEnginesResults(queryPanel, queryString);
								downloadButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
							} catch (IOException e) {
								e.printStackTrace();
							}		
						}				
					});
					downloadThread.start();		
				}
				
			});
		}
		return downloadButton;
	}
	
	private JScrollPane getGoogleTextChunksScrollPane() {
		if (googleTextChunksScrollPane == null) {
			googleTextChunksScrollPane = new JScrollPane();
			googleTextChunksScrollPane.setBorder(null);
			googleTextChunksScrollPane.setViewportView(getGoogleTextChunksJTable() );
		}
		return googleTextChunksScrollPane;
	}
	
	private JScrollPane getYahooTextChunksScrollPane() {
		if (yahooTextChunksScrollPane == null) {
			yahooTextChunksScrollPane = new JScrollPane();
			yahooTextChunksScrollPane.setBorder(null);
			yahooTextChunksScrollPane.setViewportView(getYahooTextChunksJTable());
		}
		return yahooTextChunksScrollPane;
	}
	
	private JScrollPane getGoogleCandidateAnswersScrollPane() {
		if (googleCandidateAnswersScrollPane == null) {
			googleCandidateAnswersScrollPane = new JScrollPane();
			googleCandidateAnswersScrollPane.setBorder(null);
			googleCandidateAnswersScrollPane.setViewportView(getGoogleCandidateAnswersJTable());
		}
		return googleCandidateAnswersScrollPane;
	}
	
	private JScrollPane getYahooCandidateAnswersScrollPane() {
		if (yahooCandidateAnswersScrollPane == null) {
			yahooCandidateAnswersScrollPane = new JScrollPane();
			yahooCandidateAnswersScrollPane.setBorder(null);
			yahooCandidateAnswersScrollPane.setViewportView(getYahooCandidateAnswersJTable());
		}
		return yahooCandidateAnswersScrollPane;
	}
	
	private JScrollPane getYahooResultScrollPane() {
		if (yahooResultScrollPane == null) {
			yahooResultScrollPane = new JScrollPane();
			yahooResultScrollPane.setBorder(null);
			yahooResultScrollPane.setViewportView(getYahooSearchResultEditorPane());
		}
		return yahooResultScrollPane;
	}
	
	private JScrollPane getGoogleResultScrollPane() {
		if (googleResultScrollPane == null) {
			googleResultScrollPane = new JScrollPane();
			googleResultScrollPane.setBorder(null);
			googleResultScrollPane.setViewportView(getGoolgleSearchResultEditorPane());
		}
		return googleResultScrollPane;
	}
	
	private JEditorPane getYahooSearchResultEditorPane() {
		if (yahooSearchResultEditorPane == null) {
			yahooSearchResultEditorPane = new JEditorPane("text/html", "");
			yahooSearchResultEditorPane.setBorder(null);
			yahooSearchResultEditorPane.setEditable(false);	
		}
		return yahooSearchResultEditorPane;
	}
	
	private JEditorPane getGoolgleSearchResultEditorPane() {
		if (googleSearchResultEditorPane == null) {
			googleSearchResultEditorPane = new JEditorPane("text/html", "");
			googleSearchResultEditorPane.setBorder(null);
			googleSearchResultEditorPane.setEditable(false);
		}
		return googleSearchResultEditorPane;
	}
/*	private JTable getGoogleTextChunksJTable() {
			if(googleTextChunksJTable == null) {
				googleTextChunksJTable = new JTable();
				googleTextChunksJTable.setAutoCreateRowSorter(true);			
				googleTextChunksJTable.setAutoscrolls(true);
				googleTextChunksJTable.setShowGrid(true);
				googleTextChunksJTable.setShowHorizontalLines(true);
				googleTextChunksJTable.setShowVerticalLines(true);
				googleTextChunksJTable.setRowHeight(20);
				googleTextChunksJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
				googleTextChunksJTable.setModel(new DefaultTableModel());
			}
		return googleTextChunksJTable;
	}*/
	private JTable getGoogleTextChunksJTable() {
		if (googleTextChunksJTable == null) {
			final Vector<String> colum = new Vector<String>();
			colum.addElement("Text Chunks");
			colum.addElement("Weight of TextChunk");
			googleTextChunksJTable = new JTable(googleTextChunksdata,colum);
			googleTextChunksJTable.setBackground(Color.WHITE);
			googleTextChunksJTable.setModel(new DefaultTableModel() {
				private static final long serialVersionUID = 1L;
				//String[] colum = new String[] { "Text Chunks","Weight of TextChunk" };				
				@Override
				public int getRowCount() {
					return data.size();
				}

				@Override
				public int getColumnCount() {
					return colum.size();
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					String[] row = (String[]) data.get(rowIndex);
					return row[columnIndex];
				}

				@Override
				public String getColumnName(int column) {
					return colum.get(column);
				}
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
					
			googleTextChunksJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			googleTextChunksJTable.getColumnModel().getColumn(0).setMinWidth(0);
			googleTextChunksJTable.getColumnModel().getColumn(0).setPreferredWidth(700);
			googleTextChunksJTable.getColumnModel().getColumn(1).setMinWidth(0);
			googleTextChunksJTable.getColumnModel().getColumn(1).setPreferredWidth(550);
			googleTextChunksJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			googleTextChunksJTable.setAutoscrolls(true);
			googleTextChunksJTable.setShowGrid(true);
			googleTextChunksJTable.setShowHorizontalLines(true);
			googleTextChunksJTable.setShowVerticalLines(true);
			googleTextChunksJTable.setRowHeight(20);
		}
		return googleTextChunksJTable;
	}
	private JTable getYahooTextChunksJTable() {
		if (yahooTextChunksJTable == null) {
			yahooTextChunksJTable = new JTable();
			yahooTextChunksJTable.setBackground(Color.WHITE);
			yahooTextChunksJTable.setModel(new DefaultTableModel() {
				
				private static final long serialVersionUID = 1L;
				String[] colum = new String[] { "Text Chunks","Weight of TextChunk" };
				@Override
				public int getRowCount() {
					return data.size();
				}

				@Override
				public int getColumnCount() {
					return colum.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					String[] row = (String[]) data.get(rowIndex);
					return row[columnIndex];
				}

				@Override
				public String getColumnName(int column) {
					return colum[column];
				}
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			
			yahooTextChunksJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			yahooTextChunksJTable.getColumnModel().getColumn(0).setMinWidth(0);
			yahooTextChunksJTable.getColumnModel().getColumn(0).setPreferredWidth(700);
			yahooTextChunksJTable.getColumnModel().getColumn(1).setMinWidth(0);
			yahooTextChunksJTable.getColumnModel().getColumn(1).setPreferredWidth(550);
			
			yahooTextChunksJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			yahooTextChunksJTable.setAutoscrolls(true);
			yahooTextChunksJTable.setShowGrid(true);
			yahooTextChunksJTable.setShowHorizontalLines(true);
			yahooTextChunksJTable.setShowVerticalLines(true);
			yahooTextChunksJTable.setRowHeight(20);
		}
		return yahooTextChunksJTable;
	}
	private JTable getYahooCandidateAnswersJTable() {
		if (yahooCandidateAnswersJTable == null) {
			yahooCandidateAnswersJTable = new JTable();
			yahooCandidateAnswersJTable.setBackground(Color.WHITE);
			yahooCandidateAnswersJTable.setModel(new DefaultTableModel() {

				private static final long serialVersionUID = 1L;
				String[] colum = new String[] { "Word","Frequency","AvgRank","NumDoc","NFreq","NFW" };
				
				@Override
				public int getRowCount() {
					return data.size();
				}

				@Override
				public int getColumnCount() {
					return colum.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					String[] row = (String[]) data.get(rowIndex);
					return row[columnIndex];
				}

				@Override
				public String getColumnName(int column) {
					return colum[column];
				}
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			
			yahooCandidateAnswersJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(0).setMinWidth(0);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(0).setPreferredWidth(250);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(1).setMinWidth(0);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(1).setPreferredWidth(200);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(2).setMinWidth(0);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(2).setPreferredWidth(200);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(3).setMinWidth(0);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(3).setPreferredWidth(200);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(4).setMinWidth(0);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(4).setPreferredWidth(200);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(5).setMinWidth(0);
			yahooCandidateAnswersJTable.getColumnModel().getColumn(5).setPreferredWidth(200);
			
			yahooCandidateAnswersJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			yahooCandidateAnswersJTable.setAutoscrolls(true);
			yahooCandidateAnswersJTable.setShowGrid(true);
			yahooCandidateAnswersJTable.setShowHorizontalLines(true);
			yahooCandidateAnswersJTable.setShowVerticalLines(true);
			yahooCandidateAnswersJTable.setRowHeight(20);
		}
		return yahooCandidateAnswersJTable;
	}
	private JTable getGoogleCandidateAnswersJTable() {
		if (googleCandidateAnswersJTable == null) {
			googleCandidateAnswersJTable = new JTable();
			googleCandidateAnswersJTable.setBackground(Color.WHITE);
			googleCandidateAnswersJTable.setModel(new DefaultTableModel() {

				private static final long serialVersionUID = 1L;
				String[] colum = new String[] { "Word","Frequency","AvgRank","NumDoc","NFreq","NFW" };

				@Override
				public int getRowCount() {
					return data.size();
				}

				@Override
				public int getColumnCount() {
					return colum.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					String[] row = (String[]) data.get(rowIndex);
					return row[columnIndex];
				}

				@Override
				public String getColumnName(int column) {
					return colum[column];
				}
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			
			googleCandidateAnswersJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			googleCandidateAnswersJTable.getColumnModel().getColumn(0).setMinWidth(0);
			googleCandidateAnswersJTable.getColumnModel().getColumn(0).setPreferredWidth(250);
			googleCandidateAnswersJTable.getColumnModel().getColumn(1).setMinWidth(0);
			googleCandidateAnswersJTable.getColumnModel().getColumn(1).setPreferredWidth(200);
			googleCandidateAnswersJTable.getColumnModel().getColumn(2).setMinWidth(0);
			googleCandidateAnswersJTable.getColumnModel().getColumn(2).setPreferredWidth(200);
			googleCandidateAnswersJTable.getColumnModel().getColumn(3).setMinWidth(0);
			googleCandidateAnswersJTable.getColumnModel().getColumn(3).setPreferredWidth(200);
			googleCandidateAnswersJTable.getColumnModel().getColumn(4).setMinWidth(0);
			googleCandidateAnswersJTable.getColumnModel().getColumn(4).setPreferredWidth(200);
			googleCandidateAnswersJTable.getColumnModel().getColumn(5).setMinWidth(0);
			googleCandidateAnswersJTable.getColumnModel().getColumn(5).setPreferredWidth(200);
			
			googleCandidateAnswersJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			googleCandidateAnswersJTable.setAutoscrolls(true);
			googleCandidateAnswersJTable.setShowGrid(true);
			googleCandidateAnswersJTable.setShowHorizontalLines(true);
			googleCandidateAnswersJTable.setShowVerticalLines(true);
			googleCandidateAnswersJTable.setRowHeight(20);
		}
		return googleCandidateAnswersJTable;
	}
	private JTabbedPane getResultTabbedPane() {
		if (resultTabbedPane == null) {
			resultTabbedPane = new JTabbedPane();
			resultTabbedPane.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("result")));
			resultTabbedPane.addTab(IDRSResourceBundle.res.getString("google"),getGoogleResultScrollPane());
			resultTabbedPane.addTab(IDRSResourceBundle.res.getString("yahoo"),getYahooResultScrollPane());
			resultTabbedPane.addTab(IDRSResourceBundle.res.getString("google.textchunks"), getGoogleTextChunksScrollPane());
			resultTabbedPane.addTab(IDRSResourceBundle.res.getString("yahoo.textchunks"), getYahooTextChunksScrollPane());
			resultTabbedPane.addTab(IDRSResourceBundle.res.getString("google.candidate.answers"),getGoogleCandidateAnswersScrollPane());
			resultTabbedPane.addTab(IDRSResourceBundle.res.getString("yahoo.candidate.answers"), getYahooCandidateAnswersScrollPane());
			
		}
		return resultTabbedPane;
	}

	private JButton getSubmitButton() {
		if (submitButton == null) {
			submitButton = new JButton();
			submitButton.setIcon(new ImageIcon(getClass().getResource("")));
			submitButton.setText(IDRSResourceBundle.res.getString("submit"));
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					queryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					googleSearchResultEditorPane.setText("");
					yahooSearchResultEditorPane.setText("");
					downloadButton.setEnabled(false);
					processButton.setEnabled(false);
					
					SearchEnginesAction queryAction = new SearchEnginesAction();
					
					if (queryStringTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, IDRSResourceBundle.res.getString("query.string.empty"));
						queryStringTextField.requestFocus();
						queryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					} 
					
					if(googleCheckBox.isSelected()== false &&yahooCheckBox.isSelected()==false ) {
						JOptionPane.showMessageDialog(null,IDRSResourceBundle.res.getString("select.seachengine"));
						queryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						return;
					}
					
					ArrayList<Token> keywords = new ArrayList<Token>();
					String querystring = "";
					GetCandidateAnswersAction getCA = new GetCandidateAnswersAction();
					keywords = getCA.getKeywordsFromUser(queryStringTextField.getText());
					
					for(int i =0; i<keywords.size();i++ ) {
						querystring +=keywords.get(i).s + " ";
					}
					
					if (googleCheckBox.isSelected()) {
						try {
							googleSearchResult = queryAction.submitQueryToGoogle(querystring , googleCheckBox.isSelected(),  
									Integer.parseInt(googleResultComboBox.getSelectedItem().toString()));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
					if (yahooCheckBox.isSelected()) {
						yahooSearchResult = queryAction.submitQueryToYahoo(querystring, yahooCheckBox.isSelected(), 
								Integer.parseInt(yahooResultComboBox.getSelectedItem().toString()));
					}
					updateSearchResultOnJEditorPane(googleSearchResult, yahooSearchResult);	
					if(yahooSearchResultEditorPane.getText()!="" || googleSearchResultEditorPane.getText()!= "") {
						downloadButton.setEnabled(true);
						processButton.setEnabled(true);
					}	
					queryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			});
		}
		return submitButton;
	}
	private JButton getProcessButton() {
		if (processButton == null) {
			processButton = new JButton();
			processButton.setIcon(new ImageIcon(getClass().getResource("")));
			processButton.setText(IDRSResourceBundle.res.getString("process"));
			processButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					queryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					
					ArrayList<Token> keywords = new ArrayList<Token>();
					ArrayList<CandidateAnswer> candi = new ArrayList<CandidateAnswer>();
					GetCandidateAnswersAction getCA = new GetCandidateAnswersAction();
					keywords = getCA.getKeywordsFromUser(queryStringTextField.getText());
					if (googleSearchResult != null) {
						ArrayList<TextChunks> arraylistTextChunkGoogleSearchResult = new ArrayList<TextChunks>();
						arraylistTextChunkGoogleSearchResult = getCA.getArrayListTexchunkFromSearchResult(keywords, googleSearchResult);
						//updateJTableDataGoogleTextchunks(arraylistTextChunkGoogleSearchResult);
						//buildModelForGoogleTextchunks(arraylistTextChunkGoogleSearchResult);
						
						DefaultTableModel test = buildModelForGoogleTextchunks(arraylistTextChunkGoogleSearchResult);
						googleTextChunksJTable.setModel(test);
						googleTextChunksJTable.repaint();
						candi = getCA.getCandidateAnswer(arraylistTextChunkGoogleSearchResult);	
						for(int i = 0; i<candi.size();i++ )
						{
							for(int j = 0; j<candi.get(i).candidate.size();j++)
							{
								System.out.println(candi.get(i).candidate.get(j).s);
							/*	System.out.println(" Avrg");
								System.out.println(candi.get(i).candidate.get(j).getTotalAveageRank());
								System.out.println(" Total Fre");
								System.out.println(candi.get(i).candidate.get(j).getToltalfrequency());
								System.out.println(" Num ber doc");
								System.out.println(candi.get(i).candidate.get(j).getNumberofDocuments());
								System.out.println(" total NF");
								System.out.println(candi.get(i).candidate.get(j).getTotalNormalizedFrequency());
								System.out.println(" Total NFW");
								System.out.println(candi.get(i).candidate.get(j).getNormalizedFrequencyWeight());*/

							}
						}
					}
					queryPanel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
				
					
			});
		}
		return processButton;
	}
	
	private JCheckBox getYahooCheckBox() {
		if (yahooCheckBox == null) {
			yahooCheckBox = new JCheckBox();
			yahooCheckBox.setSelected(true);
			yahooCheckBox.setText(IDRSResourceBundle.res.getString("yahoo"));
			yahooCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {
				}
			});
		}
		return yahooCheckBox;
	}

	private JLabel getYahooMaxResultLabel() {
		if (yahooMaxResultLabel == null) {
			yahooMaxResultLabel = new JLabel();
			yahooMaxResultLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			yahooMaxResultLabel.setText(IDRSResourceBundle.res.getString("max.result"));
		}
		return yahooMaxResultLabel;
	}

	private JComboBox getYahooResultComboBox() {
		if (yahooResultComboBox == null) {
			yahooResultComboBox = new JComboBox();
			yahooResultComboBox.setModel(new DefaultComboBoxModel(new Object[] {
					"5", "10", "15", "20", "25", "30", "35" }));
			yahooResultComboBox.setDoubleBuffered(false);
			yahooResultComboBox.setBorder(null);
			yahooResultComboBox.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent event) {

				}
			});
		}
		return yahooResultComboBox;
	}

	private JComboBox getGoogleResultComboBox() {
		if (googleResultComboBox == null) {
			googleResultComboBox = new JComboBox();
			googleResultComboBox.setModel(new DefaultComboBoxModel(
					new Object[] { "5", "10", "15", "20", "25", "30", "35" }));
			googleResultComboBox.setDoubleBuffered(false);
			googleResultComboBox.setBorder(null);
			googleResultComboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent event) {

				}
			});
		}
		return googleResultComboBox;
	}

	private JLabel getGoogleMaxResultLabel() {
		if (googleMaxResultLabel == null) {
			googleMaxResultLabel = new JLabel();
			googleMaxResultLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
			googleMaxResultLabel.setText(IDRSResourceBundle.res.getString("max.result"));
		}
		return googleMaxResultLabel;
	}

	private JCheckBox getGoogleCheckBox() {
		if (googleCheckBox == null) {
			googleCheckBox = new JCheckBox();
			googleCheckBox.setSelected(true);
			googleCheckBox.setText(IDRSResourceBundle.res.getString("google"));
			googleCheckBox.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent event) {
				}
			});
		}
		return googleCheckBox;
	}
	
	private JPanel getQueryPanel() {
		if (queryPanel == null) {
			queryPanel = new JPanel();
			queryPanel.setBorder(BorderFactory.createTitledBorder("Query"));
			queryPanel.setLayout(new GroupLayout());
			queryPanel.add(getQueryLabel(), new Constraints(new Leading(25, 36,36), new Leading(0, 12, 12)));
			queryPanel.add(getGoogleCheckBox(), new Constraints(new Leading(53,10, 10), new Leading(30, 8, 8)));
			queryPanel.add(getGoogleMaxResultLabel(), new Constraints(new Leading(143, 69, 12, 12), new Leading(34, 12, 12)));
			queryPanel.add(getGoogleResultComboBox(), new Constraints(new Leading(215, 10, 10), new Leading(30, 12, 12)));
			queryPanel.add(getYahooCheckBox(), new Constraints(new Leading(53,8, 8), new Leading(64, 8, 8)));
			queryPanel.add(getSubmitButton(), new Constraints(new Leading(280, 82, 10, 10), new Leading(30, 10, 10)));
			queryPanel.add(getProcessButton(), new Constraints(new Leading(280, 82, 10, 10), new Leading(60, 10, 10)));
			queryPanel.add(getQueryStringTextField(), new Constraints(new Bilateral(105, 26, 155), new Leading(0, 10, 82)));
			queryPanel.add(getYahooResultComboBox(), new Constraints(new Leading(215, 12, 12), new Leading(62, 12, 12)));
			queryPanel.add(getYahooMaxResultLabel(), new Constraints(new Leading(143, 69, 12, 12), new Leading(67, 12, 12)));
			processButton.setEnabled(false);
				
		}
		return queryPanel;
	}

	private JTextField getQueryStringTextField() {
		if (queryStringTextField == null) {
			queryStringTextField = new JTextField();
		}
		return queryStringTextField;
	}

	private JLabel getQueryLabel() {
		if (queryLabel == null) {
			queryLabel = new JLabel();
			queryLabel.setText(IDRSResourceBundle.res.getString("query.string"));
		}
		return queryLabel;
	}
	
	/**
	 * Update results for JEditor
	 * @param googleSearchResult : Search results from GoogleSearchEngine
	 * @param yahooSearResult : Search results from YahooSeachEngine
	 */
	
	private void updateSearchResultOnJEditorPane(ArrayList<String> googleSearchResult, ArrayList<String> yahooSearResult) {
		if (googleSearchResult != null && googleSearchResult.size() > 0)
			googleSearchResultEditorPane.setText(buildHTMLStringGoogleEditorPane(googleSearchResult));
		if (yahooSearResult != null && yahooSearResult.size() > 0)
			yahooSearchResultEditorPane.setText(buildHTMLStringYahooEditorPane(yahooSearResult));
	}
	
	/**
	 * Build string to view result for GoogleEditorPane
	 * @param googleSearchResult : Arraylist string googleSearchResult
	 * @return String for display in GooogleEditorPane
	 */
	
	private String buildHTMLStringGoogleEditorPane(ArrayList<String> googleSearchResult) {
		ActionMap actionMap = new ActionMap(); 
		String resultStr = "";
		resultStr += "<html><body>";
		for (int i=0; i<googleSearchResult.size(); i++){
			String[] tempArr = googleSearchResult.get(i).split("<br>");
			resultStr += "<a href='" + tempArr[0] + "'>" + (i+1) + ". " + tempArr[0] + "</>";
			resultStr += "<br>" + tempArr[1] + "<br>";
			resultStr += "<hr>";
			actionMap.put(tempArr[0], new SelectHyperLinkAction(tempArr[0]));
		}
		googleSearchResultEditorPane.addHyperlinkListener(new ActionBasedHyperlinkListener(actionMap));		
		resultStr += "</body></html>";
		return resultStr;
	}
	
	/**
	 * Build string to view result for YahooEditorPane
	 * @param yahooSearResult : Arraylist String yahooSearchResult
	 * @return String for display in YahooSearchResult
	 */
	
	private String buildHTMLStringYahooEditorPane(ArrayList<String> yahooSearResult) {
		ActionMap actionMap = new ActionMap();
		String resultStr = "";
		resultStr += "<html><body>";
		for (int i=0; i<yahooSearResult.size(); i++){
			String[] tempArr = yahooSearResult.get(i).split("<br>");
			resultStr += "<a href='" + tempArr[0] + "'>" + (i+1) + ". " + tempArr[0] + "</>";
			resultStr += "<br>" + tempArr[1] + "<br>";
			resultStr += "<hr>";
			
			actionMap.put(tempArr[0], new SelectHyperLinkAction(tempArr[0]));
		}
		yahooSearchResultEditorPane.addHyperlinkListener(new ActionBasedHyperlinkListener(actionMap));
		
		resultStr += "</body></html>";
		return resultStr;
	}
	
	public static DefaultTableModel buildModelForGoogleTextchunks (ArrayList<TextChunks> googleFinalTextchunks) {
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		Vector<String> metadataData = new Vector<String>();
		Vector<String> valueData = new Vector<String>();
		for( int i =0; i< googleFinalTextchunks.size(); i++){
			String stringTextChunk="";
			for(int j =0; j< googleFinalTextchunks.get(i).arrt.size() ; j++) {
				stringTextChunk += googleFinalTextchunks.get(i).arrt.get(j).s + " ";
			}
			String weightTextChunk= Integer.toString(googleFinalTextchunks.get(i).weigh);
			metadataData.addElement(stringTextChunk);
			valueData.addElement(weightTextChunk);	
		}
		defaultTableModel.addColumn("TextChunks", metadataData);
		defaultTableModel.addColumn("Weighted", valueData);
		return defaultTableModel;
	}
	public void updateJTableDataGoogleTextchunks (ArrayList<TextChunks> finalTextchunks) {
		for( int i =0; i< finalTextchunks.size(); i++){
			Vector<String> rowOne = new Vector<String>();
			String stringTextChunk="";
			for(int j =0; j< finalTextchunks.get(i).arrt.size() ; j++) {
				stringTextChunk += finalTextchunks.get(i).arrt.get(j).s + " ";
			}
			String weightTextChunk= Integer.toString(finalTextchunks.get(i).weigh);
			rowOne.addElement(stringTextChunk);
			rowOne.addElement(weightTextChunk);	
			googleTextChunksdata.addElement(rowOne);
		}
	}
	
}
