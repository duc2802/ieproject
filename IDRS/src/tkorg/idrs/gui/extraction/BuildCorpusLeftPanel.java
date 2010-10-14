package tkorg.idrs.gui.extraction;

import gate.Document;
import gate.Factory;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

import tkorg.idrs.core.extraction.FactoryUtil;
import tkorg.idrs.core.extraction.PaperCollection;
import tkorg.idrs.core.extraction.PaperObject;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

public class BuildCorpusLeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public final ImageIcon ICON_COMPUTER = new ImageIcon(getClass()
			.getResource(GUIProperties.COMPUTER_ICON_FILE));
	public final ImageIcon ICON_DISK = new ImageIcon(getClass().getResource(
			GUIProperties.DISK_ICON_FILE));
	public final ImageIcon ICON_FOLDER = new ImageIcon(getClass().getResource(
			GUIProperties.CLOSE_FOLDER_ICON_FILE));
	public final ImageIcon ICON_EXPANDEDFOLDER = new ImageIcon(getClass()
			.getResource(GUIProperties.OPEN_FOLDER_ICON_FILE));

	protected JTree m_tree;
	protected DefaultTreeModel m_model;
	protected JTextField m_display;
	protected JPopupMenu m_popup;
	protected Action m_action;
	protected TreePath m_clickedPath;

	public FileNode selectednode;
	String filetype = IDRSResourceBundle.res.getString("pdf");

	public static ArrayList<File> corpus;

	public BuildCorpusLeftPanel() {

		this.setLayout(new BorderLayout());
		setSize(400, 300);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode(new IconData(
				ICON_COMPUTER, null, "Computer"));

		DefaultMutableTreeNode node;
		File[] roots = File.listRoots();
		for (int k = 0; k < roots.length; k++) {
			node = new DefaultMutableTreeNode(new IconData(ICON_DISK, null,
					new FileNode(roots[k])));
			top.add(node);
			node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}

		selectednode = null;
		corpus = new ArrayList<File>();

		m_model = new DefaultTreeModel(top);
		m_tree = new JTree(m_model);

		m_tree.putClientProperty("JTree.lineStyle", "Angled");

		TreeCellRenderer renderer = new IconCellRenderer();
		m_tree.setCellRenderer(renderer);

		m_tree.addTreeExpansionListener(new DirExpansionListener());

		m_tree.addTreeSelectionListener(new DirSelectionListener());

		m_tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		m_tree.setShowsRootHandles(true);
		m_tree.setEditable(false);

		JScrollPane s = new JScrollPane();
		s.getViewport().add(m_tree);
		this.add(s, BorderLayout.CENTER);

		m_display = new JTextField();
		m_display.setEditable(false);
		this.add(m_display, BorderLayout.NORTH);

		initPopupMenu();

		m_tree.add(m_popup);
		m_tree.addMouseListener(new PopupTrigger());
		setVisible(true);
	}

	/**
	 * 
	 * comment :
	 * 
	 * @author Huynh Minh Duc
	 */
	public void initPopupMenu() {

		m_popup = new JPopupMenu();
		m_action = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				if (m_clickedPath == null)
					return;
				if (m_tree.isExpanded(m_clickedPath))
					m_tree.collapsePath(m_clickedPath);
				else
					m_tree.expandPath(m_clickedPath);
			}
		};

		m_popup.add(m_action);
		m_popup.addSeparator();

		Action addToCorpus = new AbstractAction(IDRSResourceBundle.res.getString("add.to.corpus")) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				m_tree.repaint();

				Thread t = new Thread(new Runnable() {
					public void run() {
						
						IDRSApplication.idrsStatus.setLoadingStatusBar(true);
						IDRSApplication.idrsJFrame.setCursor(Cursor
								.getPredefinedCursor(Cursor.WAIT_CURSOR));
						ArrayList<File> corpus_temp = getAllFileFrom(selectednode.m_file);

						for (File file : corpus_temp) {
							if (file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase().equals(filetype)) {
								if (!existInCorpus(file)) {
									try {
									corpus.add(file);	
									BuildCorpusRightPanel.data.add(new String[] {file.getName(),
																				file.length() / 1024 + " KB", 
																				file.getPath() });
									}catch (Exception e) {}
								} else if (selectednode.m_file.isFile())
									JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, 
											IDRSResourceBundle.res.getString("file.existed.in.corpus"), 
											IDRSResourceBundle.res.getString("message"), 
											JOptionPane.YES_OPTION, 
											new ImageIcon(getClass().getResource(GUIProperties.CANCEL_IMAGE_FILE)));
							} else
								JOptionPane.showMessageDialog(null,
										IDRSResourceBundle.res.getString("corpus.contain") + filetype
												+ IDRSResourceBundle.res.getString("file.type.only"));

						}
						BuildCorpusRightPanel.fileListJScrollPane
								.setViewportView(BuildCorpusRightPanel.fileJTable);
						IDRSApplication.idrsStatus.setLoadingStatusBar(false);
						JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, 
														IDRSResourceBundle.res.getString("load.pdf.file.complete"), 
														IDRSResourceBundle.res.getString("message"), 
														JOptionPane.YES_OPTION, 
														new ImageIcon(getClass().getResource(GUIProperties.OK_IMAGE_FILE)));
						IDRSApplication.idrsJFrame.setCursor(null);
					}
				});

				t.start();

			}
		};

		m_popup.add(addToCorpus);
	}

	/**
	 * 
	 * comment :
	 * 
	 * @param rootFile
	 * @return
	 * @author Huynh Minh Duc
	 * @throws InterruptedException
	 */
	public ArrayList<File> getAllFileFrom(File rootFile) {
		ArrayList<File> fileList = new ArrayList<File>();
		ArrayList<File> dirList = new ArrayList<File>();

		if (rootFile.isFile()) {
			fileList.add(rootFile);
		} else {
			if (rootFile.isDirectory()) {
				dirList.add(rootFile);
			}
		}

		while (dirList != null && dirList.size() != 0) {
			File dir = dirList.get(0);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i] != null) {
					if (files[i].isFile()) {
						if (files[i].getName() != null
								&& files[i].getName().substring(files[i].getName().lastIndexOf(".") + 1).toLowerCase().equals(filetype)) {
							
							fileList.add(files[i]);
						}
					} else
						dirList.add(dirList.size(), files[i]);
				}

			}
			dirList.remove(0);
		}
		return fileList;

	}

	boolean existInCorpus(File file) {
		for (int i = 0; i < corpus.size(); i++)
			if (corpus.get(i).equals(file))
				return true;

		return false;
	}

	DefaultMutableTreeNode getTreeNode(TreePath path) {
		return (DefaultMutableTreeNode) (path.getLastPathComponent());
	}

	FileNode getFileNode(DefaultMutableTreeNode node) {
		if (node == null)
			return null;
		Object obj = node.getUserObject();
		if (obj instanceof IconData)
			obj = ((IconData) obj).getObject();
		if (obj instanceof FileNode)
			return (FileNode) obj;
		else
			return null;
	}

	class PopupTrigger extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) {
				int x = e.getX();
				int y = e.getY();
				TreePath path = m_tree.getPathForLocation(x, y);
				if (path != null) {
					if (m_tree.isExpanded(path))
						m_action.putValue(Action.NAME, IDRSResourceBundle.res.getString("collapse"));
					else
						m_action.putValue(Action.NAME, IDRSResourceBundle.res.getString("expand"));
					m_popup.show(m_tree, x, y);
					m_clickedPath = path;
				}
			}
		}
	}

	// Make sure expansion is threaded and updating the tree model
	// only occurs within the event dispatching thread.
	class DirExpansionListener implements TreeExpansionListener {
		public void treeExpanded(TreeExpansionEvent event) {
			final DefaultMutableTreeNode node = getTreeNode(event.getPath());
			final FileNode fnode = getFileNode(node);

			Thread runner = new Thread() {
				public void run() {
					if (fnode != null && fnode.expand(node)) {
						Runnable runnable = new Runnable() {
							public void run() {
								m_model.reload(node);
							}
						};
						SwingUtilities.invokeLater(runnable);
					}
				}
			};
			runner.start();
		}

		public void treeCollapsed(TreeExpansionEvent event) {
		}
	}

	class DirSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent event) {
			DefaultMutableTreeNode node = getTreeNode(event.getPath());
			selectednode = getFileNode(node);
			if (selectednode != null)
				m_display.setText(selectednode.getFile().getAbsolutePath());
			else
				m_display.setText("");
		}
	}

}

class IconCellRenderer extends JLabel implements TreeCellRenderer {

	private static final long serialVersionUID = 1L;
	protected Color m_textSelectionColor;
	protected Color m_textNonSelectionColor;
	protected Color m_bkSelectionColor;
	protected Color m_bkNonSelectionColor;
	protected Color m_borderSelectionColor;

	protected boolean m_selected;

	public IconCellRenderer() {
		super();
		m_textSelectionColor = UIManager.getColor("Tree.selectionForeground");
		m_textNonSelectionColor = UIManager.getColor("Tree.textForeground");
		m_bkSelectionColor = UIManager.getColor("Tree.selectionBackground");
		m_bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
		m_borderSelectionColor = UIManager
				.getColor("Tree.selectionBorderColor");
		setOpaque(false);
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus)

	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Object obj = node.getUserObject();
		setText(obj.toString());

		if (obj instanceof Boolean)
			setText("Retrieving data...");

		if (obj instanceof IconData) {
			IconData idata = (IconData) obj;
			if (expanded)
				setIcon(idata.getExpandedIcon());
			else
				setIcon(idata.getIcon());
		} else
			setIcon(null);

		setFont(tree.getFont());
		setForeground(sel ? m_textSelectionColor : m_textNonSelectionColor);
		setBackground(sel ? m_bkSelectionColor : m_bkNonSelectionColor);
		m_selected = sel;
		return this;
	}

	public void paintComponent(Graphics g) {
		Color bColor = getBackground();
		Icon icon = getIcon();

		g.setColor(bColor);
		int offset = 0;
		if (icon != null && getText() != null)
			offset = (icon.getIconWidth() + getIconTextGap());
		g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

		if (m_selected) {
			g.setColor(m_borderSelectionColor);
			g.drawRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);
		}

		super.paintComponent(g);
	}
}

class IconData {
	protected Icon m_icon;
	protected Icon m_expandedIcon;
	protected Object m_data;

	public IconData(Icon icon, Object data) {
		m_icon = icon;
		m_expandedIcon = null;
		m_data = data;
	}

	public IconData(Icon icon, Icon expandedIcon, Object data) {
		m_icon = icon;
		m_expandedIcon = expandedIcon;
		m_data = data;
	}

	public Icon getIcon() {
		return m_icon;
	}

	public Icon getExpandedIcon() {
		return m_expandedIcon != null ? m_expandedIcon : m_icon;
	}

	public Object getObject() {
		return m_data;
	}

	public String toString() {
		return m_data.toString();
	}
}

class FileNode {
	protected File m_file;

	public FileNode(File file) {
		m_file = file;
	}

	public File getFile() {
		return m_file;
	}

	public String getFileType() {
		if (m_file.isDirectory())
			return "Directory";
		else {

			int idx = (m_file.getName()).lastIndexOf(".");

			return m_file.getName().substring(idx + 1);

		}
	}

	public String toString() {
		return m_file.getName().length() > 0 ? m_file.getName() : m_file
				.getPath();
	}

	@SuppressWarnings("unchecked")
	public boolean expand(DefaultMutableTreeNode parent) {
		DefaultMutableTreeNode flag = (DefaultMutableTreeNode) parent
				.getFirstChild();
		if (flag == null) // No flag
			return false;
		Object obj = flag.getUserObject();
		if (!(obj instanceof Boolean))
			return false; // Already expanded

		parent.removeAllChildren(); // Remove Flag

		File[] files = listFiles();
		if (files == null)
			return true;

		Vector v = new Vector();

		for (int k = 0; k < files.length; k++) {
			if(files[k].isDirectory()) {
				File f = files[k];

				FileNode newNode = new FileNode(f);
				
				boolean isAdded = false;
				for (int i = 0; i < v.size(); i++) {
					FileNode nd = (FileNode) v.elementAt(i);
					if (newNode.compareTo(nd) < 0) {
						v.insertElementAt(newNode, i);
						isAdded = true;
						break;
					}
				}
				if (!isAdded)
					v.addElement(newNode);
			}
		}
		
		for (int k = 0; k < files.length; k++) {
			if(files[k].isFile()) {
				File f = files[k];
				FileNode newNode = new FileNode(f);
				v.addElement(newNode);
			}
		}
		
		for (int i = 0; i < v.size(); i++) {
			FileNode nd = (FileNode) v.elementAt(i);
			String type = nd.getFileType();
			IconData idata = null;

			if (type.equals("Directory")) {
				idata = new IconData(new ImageIcon(getClass().getResource(
						GUIProperties.CLOSE_FOLDER_ICON_FILE)), new ImageIcon(
						getClass().getResource(
								GUIProperties.OPEN_FOLDER_ICON_FILE)), nd);
			} else {
				if (type.toLowerCase(Locale.ENGLISH).equals("pdf"))
					idata = new IconData(new ImageIcon(getClass().getResource(
							GUIProperties.PDF_ICON_FILE)),
							new ImageIcon(getClass().getResource(
									GUIProperties.PDF_ICON_FILE)), nd);
				else {
					if (type.toLowerCase(Locale.ENGLISH).equals("rtf"))

						idata = new IconData(new ImageIcon(getClass()
								.getResource(GUIProperties.RTF_ICON_FILE)),
								new ImageIcon(getClass().getResource(
										GUIProperties.RTF_ICON_FILE)), nd);
					else {
						if (type.toLowerCase(Locale.ENGLISH).equals("txt"))

							idata = new IconData(new ImageIcon(getClass()
									.getResource(GUIProperties.TXT_ICON_FILE)),
									new ImageIcon(getClass().getResource(
											GUIProperties.TXT_ICON_FILE)), nd);
						else {
							if (type.toLowerCase(Locale.ENGLISH).equals("html"))

								idata = new IconData(new ImageIcon(getClass()
										.getResource(
												GUIProperties.HTML_ICON_FILE)),
										new ImageIcon(getClass().getResource(
												GUIProperties.HTML_ICON_FILE)),
										nd);
							else {
								if (type.toLowerCase(Locale.ENGLISH).equals("doc"))

									idata = new IconData(
											new ImageIcon(
													getClass()
															.getResource(
																	GUIProperties.DOC_ICON_FILE)),
											new ImageIcon(
													getClass()
															.getResource(
																	GUIProperties.DOC_ICON_FILE)),
											nd);
								else {
									if (type.toLowerCase(Locale.ENGLISH).equals("jpg")
											|| type.toLowerCase(Locale.ENGLISH).equals("png")
											|| type.toLowerCase(Locale.ENGLISH).equals("ico")
											|| type.toLowerCase(Locale.ENGLISH).equals("bmp"))
										idata = new IconData(
												new ImageIcon(
														getClass()
																.getResource(
																		GUIProperties.IMAGE_ICON_FILE)),
												new ImageIcon(
														getClass()
																.getResource(
																		GUIProperties.IMAGE_ICON_FILE)),
												nd);
									else
										
									idata = new IconData(
											new ImageIcon(
													getClass()
															.getResource(
																	GUIProperties.TXT_ICON_FILE)),
											new ImageIcon(
													getClass()
															.getResource(
																	GUIProperties.TXT_ICON_FILE)),
											nd);
								}
							}
						}
					}
				}
			}

			DefaultMutableTreeNode node = new DefaultMutableTreeNode(idata);
			parent.add(node);

			if (nd.hasSubDirs())
				node.add(new DefaultMutableTreeNode(new Boolean(true)));
		}

		return true;
	}

	public boolean hasSubDirs() {
		File[] files = listFiles();
		if (files == null)
			return false;
		for (int k = 0; k < files.length; k++) {
			if (files[k].isDirectory() || files[k].isFile())
				return true;
		}
		return false;
	}

	public int compareTo(FileNode toCompare) {
		return m_file.getName().compareToIgnoreCase(toCompare.m_file.getName());
	}

	protected File[] listFiles() {
		if (!m_file.isDirectory())
			return null;
		try {
			return m_file.listFiles();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error reading directory "
					+ m_file.getAbsolutePath(), "Warning",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
}