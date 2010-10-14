package tkorg.idrs.gui.main;
/*
 * 
 * author: duchuynh
 *  modif :tiendv
 *  
 *  27/4 add seach menu 
 */

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Event;
import java.awt.BorderLayout;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import tkorg.idrs.gui.classification.ClassificationMenu;
import tkorg.idrs.gui.crawler.CrawlerMenu;
import tkorg.idrs.gui.crawler.CrawlerToolBar;
import tkorg.idrs.gui.searchengines.SearchEnginesMenu;

import tkorg.idrs.gui.searchengines.SearchEnginesToolBar;
import tkorg.idrs.gui.extraction.InformationExtractionMenu;
import tkorg.idrs.gui.ontology.OntologyMenu;
import tkorg.idrs.gui.ontology.OntologyToolBar;
import tkorg.idrs.gui.processingquestions.ProcessingQuestionMenu;
import tkorg.idrs.properties.files.IDRSModulesProperties;

import java.util.Locale;

public class IDRSApplication {
	
	public static JFrame idrsJFrame = null;  //  @jve:decl-index=0:visual-constraint="170,36"

	private JMenuBar jJMenuBar = null;
	
	private static JMenu fileMenu = null;
	private static JMenu editMenu = null;
	private static JMenu optionMenu = null;
	private static JMenu helpMenu = null;
	private static OntologyMenu ontologyMenu = null;
	private static CrawlerMenu crawlerMenu = null;
	private static SearchEnginesMenu searchMenu = null;
	private static InformationExtractionMenu informationExtractionMenu = null;
	private static ClassificationMenu classificationMenu = null;
	private static ProcessingQuestionMenu processingQuestionMenu = null;
	
	private static JMenuItem exitMenuItem = null;
	private static JMenuItem cutMenuItem = null;
	private static JMenuItem copyMenuItem = null;
	private static JMenuItem pasteMenuItem = null;
	private static JMenuItem saveMenuItem = null;
	private static JMenuItem configuarionMenuItem = null;	
	private static JMenuItem aboutMenuItem = null;
	
	public static IDRSStatusBar idrsStatus = null;
	private IDRSToolBar idrsToolBar = null;	
	private static IDRSTabPanel idrsTabPanel = null;
	
	private static OntologyToolBar ontologyToolBar = null;
	private CrawlerToolBar crawlerToolBar = null;	
	private SearchEnginesToolBar searchToolbar = null;
	
	  //  @jve:decl-index=0:
	/**
	 * This method initializes idrsJFrame
	 */
	private JFrame getIDRSJFrame() {
		if (idrsJFrame == null) {
			idrsJFrame = new JFrame();
			idrsJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			idrsJFrame.setJMenuBar(getJJMenuBar());
			idrsJFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

			ComponentUtilities.setMiniSize(idrsJFrame);
			idrsJFrame.setTitle(IDRSResourceBundle.res.getString("application.name"));
			idrsJFrame.getContentPane().add(getOntologyToolBar(), BorderLayout.NORTH);
			idrsJFrame.getContentPane().add(getIDRSTabPanel(), BorderLayout.CENTER);
			idrsJFrame.getContentPane().add(getIDRSStatusBar(), BorderLayout.SOUTH);
		
			
		}
		return idrsJFrame;
	}

	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getEditMenu());
			jJMenuBar.add(getOptionMenu());
			jJMenuBar.add(getOntologyMenu());
			jJMenuBar.add(getCrawlerMenu());
			jJMenuBar.add(getSearchMenu());
			jJMenuBar.add(getExtractionMenu());
			jJMenuBar.add(getClassificationMenu());
			jJMenuBar.add(getProcessingQuestionMenu());
			jJMenuBar.add(getHelpMenu());
			
			enableClassificationMenu(false);
			enableCrawlerMenu(false);
			enableSearchMenu(false);
			enableExtractionMenu(false);
			enableProcessingQuestionMenu(false);
			enableOntologyMenu(true);
			
		}
		return jJMenuBar;
	}


	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText(IDRSResourceBundle.res.getString("file"));
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getEditMenu() {
		if (editMenu == null) {
			editMenu = new JMenu();
			editMenu.setText(IDRSResourceBundle.res.getString("edit"));
			editMenu.add(getCutMenuItem());
			editMenu.add(getCopyMenuItem());
			editMenu.add(getPasteMenuItem());
		}
		return editMenu;
	}

	/**
	 * 
	 * @return
	 */
	private JMenu getOptionMenu() {
		if (optionMenu == null) {
			optionMenu = new JMenu();
			optionMenu.setText(IDRSResourceBundle.res.getString("option"));
			optionMenu.add(getConfigurationMenuItem());
		}
		return optionMenu;
	}
	
	/**
	 * 
	 * @return
	 */
	private OntologyMenu getOntologyMenu() {
		if(ontologyMenu == null){
			ontologyMenu = new OntologyMenu();
			ontologyMenu.setText(IDRSResourceBundle.res.getString("ontology"));
			//ontologyMenu.setEnabled(b)
		}
		return ontologyMenu;
	}
	
	/**
	 * 
	 * @return
	 */
	private CrawlerMenu getCrawlerMenu(){
		if(crawlerMenu == null){
			crawlerMenu = new CrawlerMenu();
			crawlerMenu.setText(IDRSResourceBundle.res.getString("crawler"));
		}
		return crawlerMenu;
	} 

	private SearchEnginesMenu getSearchMenu() {
		if(searchMenu == null){
			searchMenu = new SearchEnginesMenu();
			searchMenu.setText(IDRSResourceBundle.res.getString("search")); 
			
		}
		// TODO Auto-generated method stub
		return searchMenu;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	private InformationExtractionMenu getExtractionMenu(){
		if(informationExtractionMenu == null){
			informationExtractionMenu = new InformationExtractionMenu();
			informationExtractionMenu.setText(IDRSResourceBundle.res.getString("information.extraction"));
		}
		return informationExtractionMenu;	
	}
	
	/**
	 * 
	 * @return
	 */
	private ClassificationMenu getClassificationMenu(){
		if(classificationMenu == null) {
			classificationMenu = new ClassificationMenu();
			classificationMenu.setText(IDRSResourceBundle.res.getString("classification"));
		}
		return classificationMenu;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	private ProcessingQuestionMenu getProcessingQuestionMenu(){
		if(processingQuestionMenu == null){
			processingQuestionMenu = new ProcessingQuestionMenu();
			processingQuestionMenu.setText(IDRSResourceBundle.res.getString("question.processing"));
		}
		return processingQuestionMenu;
	}
	
	/**
	 * This method initializes jMenu	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText(IDRSResourceBundle.res.getString("help"));
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText(IDRSResourceBundle.res.getString("edit"));
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}
	
	/**
	 * 
	 * @return
	 */
	private JMenuItem getConfigurationMenuItem() {
		if (configuarionMenuItem == null) {
			configuarionMenuItem = new JMenuItem();
			configuarionMenuItem.setText(IDRSResourceBundle.res.getString("configuation"));
			configuarionMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IDRSConfigurationDialog configurationDlg = new IDRSConfigurationDialog(idrsJFrame);
					configurationDlg.setVisible(true);
				}
			});
		}
		return configuarionMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText(IDRSResourceBundle.res.getString("about"));
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					IDRSAboutDialog aboutDialog = new IDRSAboutDialog(idrsJFrame);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCutMenuItem() {
		if (cutMenuItem == null) {
			cutMenuItem = new JMenuItem();
			cutMenuItem.setText(IDRSResourceBundle.res.getString("cut"));
			cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
					Event.CTRL_MASK, true));
		}
		return cutMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getCopyMenuItem() {
		if (copyMenuItem == null) {
			copyMenuItem = new JMenuItem();
			copyMenuItem.setText(IDRSResourceBundle.res.getString("copy"));
			copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
					Event.CTRL_MASK, true));
		}
		return copyMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getPasteMenuItem() {
		if (pasteMenuItem == null) {
			pasteMenuItem = new JMenuItem();
			pasteMenuItem.setText(IDRSResourceBundle.res.getString("paste"));
			pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
					Event.CTRL_MASK, true));
		}
		return pasteMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText(IDRSResourceBundle.res.getString("save"));
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
		}
		return saveMenuItem;
	}
	
	/**
	 * This method initializes IDRS Status Bar	
	 * 	
	 * @return tkorg.idrs.gui.ontology.IDRSStatusBar	
	 */
	private IDRSStatusBar getIDRSStatusBar() {
		if(idrsStatus == null) {
			idrsStatus = new IDRSStatusBar();
		}		
		return idrsStatus;
	}
	
	
	/**
	 * This method initializes IDRS Status Bar	
	 * 	
	 * @return tkorg.idrs.gui.ontology.IDRSStatusBar	
	 */
	private IDRSToolBar getIDRSToolBar() {
		if(idrsToolBar == null) {
			idrsToolBar = new IDRSToolBar();
			
		}
		return idrsToolBar;
	}
	
	/**
	 * This method initializes Ontology ToolBar
	 * 
	 */
	private OntologyToolBar getOntologyToolBar() {
		if(ontologyToolBar == null) {
			ontologyToolBar = new OntologyToolBar(idrsJFrame);
		}
		return ontologyToolBar;
	};
	
	/**
	 * 
	 * 
	 */
	private CrawlerToolBar getCrawlerToolBar() {
		if(crawlerToolBar == null) {
			crawlerToolBar = new CrawlerToolBar();
		}
		return crawlerToolBar;
	};
	
	/*
	 * 
	 * 
	 */
	private SearchEnginesToolBar getSearchEngineToolBar() {
		if(searchToolbar == null) {
			searchToolbar = new SearchEnginesToolBar();
		}
		return searchToolbar;
	};
	
	
	public void enableOntologyMenu(boolean flag){
		ontologyMenu.setEnabled(flag);
	}
	
	public void enableCrawlerMenu(boolean flag){
		crawlerMenu.setEnabled(flag);
	}
	
	public void enableSearchMenu(boolean flag){
		searchMenu.setEnabled(flag);
	}
	public void enableExtractionMenu(boolean flag){
		informationExtractionMenu.setEnabled(flag);
	}
	public void enableClassificationMenu(boolean flag){
		classificationMenu.setEnabled(flag);
	}
	public void enableProcessingQuestionMenu(boolean flag){
		processingQuestionMenu.setEnabled(flag);
	}
	
	/**
	 * 
	 * 
	 */
	public void disableOntologyToolBar() {
		if(ontologyToolBar != null){
			idrsJFrame.getContentPane().remove(ontologyToolBar);
		}
	}
	
	/**
	 * 
	 * 
	 */
	public void disableIDRSToolBar() {
		if(idrsToolBar != null){
			idrsJFrame.getContentPane().remove(idrsToolBar);
		}
	}
	
	/**
	 * 
	 * 
	 */
	public void disableCrawlerToolBar() {
		if(crawlerToolBar != null){
			idrsJFrame.getContentPane().remove(crawlerToolBar);
		}
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public void disableSearchToolBar() {
		if(searchToolbar != null){
			idrsJFrame.getContentPane().remove(searchToolbar);
		}
	}
	/*
	 * 
	 * 
	 */
	private IDRSTabPanel getIDRSTabPanel() {
		if(idrsTabPanel == null){
			idrsTabPanel = new IDRSTabPanel();
			idrsTabPanel.addChangeListener(new ChangeListener(){

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					setIDRSToolBar(idrsTabPanel.getSelectedIndex() + 1);
				}
				
			});
		}
		return idrsTabPanel;
	}
	/**
	 *  
	 * @param IDToolBar
	 */
	public void setIDRSToolBar(int IDToolBar){
		switch (IDToolBar) {
			case IDRSModulesProperties.ONTOLOGY_MODULE_NAME:
				
				enableClassificationMenu(false);
				enableCrawlerMenu(false);
				enableSearchMenu(false);
				enableExtractionMenu(false);
				enableProcessingQuestionMenu(false);
				enableOntologyMenu(true);
				
				disableIDRSToolBar();
				disableCrawlerToolBar();
				disableSearchToolBar();
				
				idrsJFrame.getContentPane().repaint();
				idrsJFrame.getContentPane().add(getOntologyToolBar(), BorderLayout.NORTH);
				idrsJFrame.invalidate();
				idrsJFrame.validate();
				idrsJFrame.getContentPane().repaint();
				break;
			case IDRSModulesProperties.CRAWLER_MODULE_NAME:	
				
				enableClassificationMenu(false);
				enableCrawlerMenu(true);
				enableSearchMenu(false);
				enableExtractionMenu(false);
				enableProcessingQuestionMenu(false);
				enableOntologyMenu(false);
				
				disableOntologyToolBar();			
				disableIDRSToolBar();
				disableSearchToolBar();
				
				idrsJFrame.getContentPane().repaint();	
				idrsJFrame.getContentPane().repaint();			
				idrsJFrame.getContentPane().add(getCrawlerToolBar(), BorderLayout.NORTH);
				idrsJFrame.invalidate();
				idrsJFrame.validate();
				idrsJFrame.getContentPane().repaint();
				break;
			case IDRSModulesProperties.SEARCH_MODULE_NAME:
				enableClassificationMenu(false);
				enableCrawlerMenu(false);
				enableSearchMenu(true);
				enableExtractionMenu(false);
				enableProcessingQuestionMenu(false);
				enableOntologyMenu(false);
				
				disableOntologyToolBar();
				disableIDRSToolBar();
				disableCrawlerToolBar();
				
				idrsJFrame.getContentPane().repaint();			
				idrsJFrame.getContentPane().add(getSearchEngineToolBar(), BorderLayout.NORTH);
				idrsJFrame.invalidate();
				idrsJFrame.validate();
				idrsJFrame.getContentPane().repaint();
				break;
			
			case IDRSModulesProperties.EXTRACTION_MODULE_NAME:
				
				enableClassificationMenu(false);
				enableCrawlerMenu(false);
				enableSearchMenu(false);
				enableExtractionMenu(true);
				enableProcessingQuestionMenu(false);
				enableOntologyMenu(false);
				
				disableOntologyToolBar();
				disableCrawlerToolBar();
				disableSearchToolBar();
				
				idrsJFrame.getContentPane().repaint();			
				//idrsJFrame.getContentPane().add(getInformationExtractionToolBar(), BorderLayout.NORTH);
				idrsJFrame.invalidate();
				idrsJFrame.validate();
				idrsJFrame.getContentPane().repaint();
				break;
			case IDRSModulesProperties.CLASSIFICATION_MODULE_NAME:	
				
				enableClassificationMenu(true);
				enableCrawlerMenu(false);
				enableSearchMenu(false);
				enableExtractionMenu(false);
				enableProcessingQuestionMenu(false);
				enableOntologyMenu(false);
				
				disableOntologyToolBar();
				disableCrawlerToolBar();
				disableSearchToolBar();
				
				idrsJFrame.getContentPane().repaint();			
				//idrsJFrame.getContentPane().add(getIDRSToolBar(), BorderLayout.NORTH);
				idrsJFrame.invalidate();
				idrsJFrame.validate();
				idrsJFrame.getContentPane().repaint();
				break;		
			case IDRSModulesProperties.QUESTION_PROCESSING_MODULE_NAME:
				
				enableClassificationMenu(false);
				enableCrawlerMenu(false);
				enableExtractionMenu(false);
				enableProcessingQuestionMenu(true);
				enableOntologyMenu(false);
				enableSearchMenu(false);
				
				disableOntologyToolBar();			
				disableCrawlerToolBar();
				disableSearchToolBar();
				
				idrsJFrame.getContentPane().repaint();			
				//idrsJFrame.getContentPane().add(getIDRSToolBar(), BorderLayout.NORTH);
				idrsJFrame.invalidate();
				idrsJFrame.validate();
				idrsJFrame.getContentPane().repaint();
				break;
		}
	}	
	
	/**
	 * 
	 * 
	 */
	public static void updateTextOfComponents(){
		
		idrsJFrame.setTitle(IDRSResourceBundle.res.getString("application.name"));
		
		fileMenu.setText(IDRSResourceBundle.res.getString("file"));
		editMenu.setText(IDRSResourceBundle.res.getString("edit"));
		optionMenu.setText(IDRSResourceBundle.res.getString("option"));
		ontologyMenu.setText(IDRSResourceBundle.res.getString("ontology"));
		crawlerMenu.setText(IDRSResourceBundle.res.getString("crawler"));
		searchMenu.setText(IDRSResourceBundle.res.getString("search"));
		informationExtractionMenu.setText(IDRSResourceBundle.res.getString("information.extraction"));
		classificationMenu.setText(IDRSResourceBundle.res.getString("classification"));
		processingQuestionMenu.setText(IDRSResourceBundle.res.getString("question.processing"));
		helpMenu.setText(IDRSResourceBundle.res.getString("help"));
		
		exitMenuItem.setText(IDRSResourceBundle.res.getString("edit"));
		configuarionMenuItem.setText(IDRSResourceBundle.res.getString("configuation"));
		aboutMenuItem.setText(IDRSResourceBundle.res.getString("about"));
		cutMenuItem.setText(IDRSResourceBundle.res.getString("cut"));
		copyMenuItem.setText(IDRSResourceBundle.res.getString("copy"));
		pasteMenuItem.setText(IDRSResourceBundle.res.getString("paste"));
		saveMenuItem.setText(IDRSResourceBundle.res.getString("save"));
		
		ontologyMenu.updateTextOfComponents();
		ontologyToolBar.updateTextOfComponents();		
		idrsTabPanel.updateTextOfComponents();
		idrsStatus.setMessage(IDRSResourceBundle.res.getString("copyright"));
		
		idrsTabPanel.setTitleAt(0, IDRSResourceBundle.res.getString("ontology"));
		idrsTabPanel.setTitleAt(1, IDRSResourceBundle.res.getString("crawler"));
		idrsTabPanel.setTitleAt(2, IDRSResourceBundle.res.getString("search"));
		idrsTabPanel.setTitleAt(3, IDRSResourceBundle.res.getString("information.extraction"));
		idrsTabPanel.setTitleAt(4, IDRSResourceBundle.res.getString("classification"));
		idrsTabPanel.setTitleAt(5, IDRSResourceBundle.res.getString("question.processing"));
	}
	
	/**
	 * 
	 * Launches this application
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Locale locale = Locale.US;
					Locale.setDefault(locale);	
					IDRSResourceBundle.res = IDRSResourceBundle.initResources();
					UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
					IDRSApplication application = new IDRSApplication();
					application.getIDRSJFrame().setVisible(true);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	
}
