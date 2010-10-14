/**
 * Created by: HNTin & Date: Jun 1, 2010
 */
package tkorg.idrs.gui.extraction;

import gate.Document;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.core.extraction.FactoryUtil;
import tkorg.idrs.core.extraction.PaperObject;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

/**
 * @author Huynh Ngoc Tin
 * 
 */
public class BuildCorpusRightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel fileListJPanel;
	public static JScrollPane fileListJScrollPane;
	protected JPopupMenu m_popup;

	// private JList corpusFileListJList;
	public static JTable fileJTable;
	public static Vector<String[]> data = new Vector<String[]>();
	ImageIcon file_icon;

	public BuildCorpusRightPanel() {
		this.setLayout(new GroupLayout());
		
		this.add(getFileListJPanel(), new Constraints(new Bilateral(0, 0, 0),
				new Bilateral(0, 0, 390)));
		initPopupMenu();
		fileJTable.add(m_popup);
		fileJTable.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					int x = e.getX();
					int y = e.getY();
					m_popup.show(fileJTable, x, y);
				}
			}
		});
	}

	public void initPopupMenu() {
		// TODO Auto-generated method stub
		m_popup = new JPopupMenu();

		Action remove = new AbstractAction(IDRSResourceBundle.res.getString("remove")) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = fileJTable.getSelectedRows();
				
				for (int i = selectedRows.length - 1; i >= 0; i--){
					
					BuildCorpusLeftPanel.corpus.remove(selectedRows[i]);
					data.remove(selectedRows[i]);
				}
				fileListJScrollPane.setViewportView(fileJTable);
				fileJTable.clearSelection();
			}
		};

		m_popup.add(remove);
	}

	private JPanel getFileListJPanel() {
		if (fileListJPanel == null) {
			fileListJPanel = new JPanel();
			fileListJPanel.setLayout(new GroupLayout());
			fileListJPanel.add(getFileListJScrollPane(), new Constraints(
					new Bilateral(0, 0, 22), new Bilateral(0, 0, 22)));
			fileListJPanel
					.setBorder(BorderFactory.createTitledBorder(IDRSResourceBundle.res.getString("corpus")));
		}
		return fileListJPanel;
	}

	private JScrollPane getFileListJScrollPane() {
		if (fileListJScrollPane == null) {
			fileListJScrollPane = new JScrollPane();
			fileListJScrollPane.setAutoscrolls(true);
			fileListJScrollPane.setBackground(Color.WHITE);
			fileListJScrollPane.setViewportView(getFileJTable());

		}
		return fileListJScrollPane;
	}

	private JTable getFileJTable() {
		if (fileJTable == null) {
			fileJTable = new JTable();
			fileJTable.setBackground(Color.WHITE);
			fileJTable.setModel(new DefaultTableModel() {

				private static final long serialVersionUID = 1L;
				String[] colum = new String[] { IDRSResourceBundle.res.getString("file.name"), IDRSResourceBundle.res.getString("size"), IDRSResourceBundle.res.getString("url") };

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

			fileJTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			fileJTable.getColumnModel().getColumn(0).setMinWidth(0);
			fileJTable.getColumnModel().getColumn(0).setPreferredWidth(450);
			fileJTable.getColumnModel().getColumn(1).setMinWidth(0);
			fileJTable.getColumnModel().getColumn(1).setPreferredWidth(100);
			fileJTable.getColumnModel().getColumn(2).setMinWidth(0);
			fileJTable.getColumnModel().getColumn(2).setPreferredWidth(445);

			BuildCorpusRightPanel.fileJTable.getColumn(
					BuildCorpusRightPanel.fileJTable.getColumnName(0))
					.setCellRenderer(new TableCellRenderer() {

						public Component getTableCellRendererComponent(
								JTable table, Object value, boolean isSelected,
								boolean hasFocus, int row, int column) {
							String type = value.toString().substring(
									value.toString().lastIndexOf(".") + 1);

							JLabel label = new JLabel();
							label.setIcon(getFileIcon(type));
							label.setText(value.toString());
							label.setOpaque(true);
							label.setBackground(table.getBackground());
							label.setForeground(table.getForeground());

							if (isSelected) {
								label.setBackground(table
										.getSelectionBackground());
								label.setForeground(table
										.getSelectionForeground());
							}
							if (hasFocus) {
								label.setBorder(BorderFactory
										.createLineBorder(Color.RED));
							}
							return label;
						}
					});

			//fileJTable.setAutoCreateRowSorter(true);
			fileJTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			fileJTable.setAutoscrolls(true);
			fileJTable.setShowGrid(true);
			fileJTable.setShowHorizontalLines(true);
			fileJTable.setShowVerticalLines(true);
			fileJTable.setRowHeight(20);
		}
		return fileJTable;
	}

	public ImageIcon getFileIcon(String filetype) {
		if (filetype.toLowerCase().equals("rdf"))
			return new ImageIcon(getClass().getResource(
					GUIProperties.RTF_ICON_FILE));
		else {
			if (filetype.toLowerCase().equals("txt"))
				return new ImageIcon(getClass().getResource(
						GUIProperties.TXT_ICON_FILE));
			else {
				if (filetype.toLowerCase().equals("html"))
					return new ImageIcon(getClass().getResource(
							GUIProperties.HTML_ICON_FILE));
				else {
					if (filetype.toLowerCase().equals("doc"))
						return new ImageIcon(getClass().getResource(
								GUIProperties.DOC_ICON_FILE));
					else {
						if (filetype.toLowerCase().equals("pdf"))
							return new ImageIcon(getClass().getResource(
									GUIProperties.PDF_ICON_FILE));
						else {
							if (filetype.toLowerCase().equals("zip"))
								return new ImageIcon(getClass().getResource(
										GUIProperties.ZIP_ICON_FILE));
							else {
								if (filetype.toLowerCase().equals("jpg")
										|| filetype.toLowerCase().equals("png")
										|| filetype.toLowerCase().equals("ico")
										|| filetype.toLowerCase().equals("bmp"))
									return new ImageIcon(
											getClass()
													.getResource(
															GUIProperties.IMAGE_ICON_FILE));
								else
									return new ImageIcon(
											getClass()
													.getResource(
															GUIProperties.TXT_ICON_FILE));
							}
						}
					}
				}
			}
		}
	}

}
