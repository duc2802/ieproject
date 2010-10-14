package tkorg.idrs.gui.main;

/*
 * 
 * Author : Duchuynh.
 * Modifi: Tiendv.
 * 
 * 24/4  add tab seach.
 */

import javax.swing.JTabbedPane;

import tkorg.idrs.gui.classification.ClassificationPanel;
import tkorg.idrs.gui.crawler.CrawlerPanel;
import tkorg.idrs.gui.extraction.InformationExtractionPanel;
import tkorg.idrs.gui.ontology.OntologyPanel;
import tkorg.idrs.gui.processingquestions.QuestionProcessingPanel;
import tkorg.idrs.gui.searchengines.SearchEnginesPanel;

public class IDRSTabPanel extends JTabbedPane{	
	
	private static final long serialVersionUID = 1L;
	
	private static OntologyPanel ontologyPanel = null;
	private static CrawlerPanel crawlerPanel = null;
	private static SearchEnginesPanel searchEnginePanel = null;
	private static InformationExtractionPanel informationExtractionPanel = null;
	private static ClassificationPanel classificationPanel = null;
	
	private QuestionProcessingPanel questionProcessingPanel = null;
	
	public IDRSTabPanel() {
		super();		
		add(getOntologyPanel(), IDRSResourceBundle.res.getString("ontology"));
		addTab(IDRSResourceBundle.res.getString("crawler"), getCrawlerPanel());
		addTab(IDRSResourceBundle.res.getString("search"),getSearchEnginePanel());
		addTab(IDRSResourceBundle.res.getString("information.extraction"), getExtractionPanel());		
		//addTab(IDRSResourceBundle.res.getString("classification"), getClassificationPanel());		
		//addTab(IDRSResourceBundle.res.getString("question.processing"), getQuestionProcessingPanel());		
		
	}

	/**
	 * 
	 * @return
	 */
	private OntologyPanel getOntologyPanel() {
		if(ontologyPanel == null) {
			ontologyPanel = new OntologyPanel();
		}
		return ontologyPanel;
	}
	/**
	 * 
	 */
	private CrawlerPanel getCrawlerPanel() {
		if(crawlerPanel == null) {
			crawlerPanel = new CrawlerPanel();
		}
		return crawlerPanel;
	}
	/*
	 * 
	 * 
	 */
	private SearchEnginesPanel getSearchEnginePanel() {
		if(searchEnginePanel == null)
		{
			searchEnginePanel = new SearchEnginesPanel();
		}
		
		// TODO Auto-generated method stub
		return searchEnginePanel;
	}
	/**
	 * 
	 */
	private InformationExtractionPanel getExtractionPanel() {
		if(informationExtractionPanel == null) {
			informationExtractionPanel = new InformationExtractionPanel();
		}
		return informationExtractionPanel;
	}
	
	/**
	 * 
	 */
	private ClassificationPanel getClassificationPanel() {
		if(classificationPanel == null) {
			classificationPanel = new ClassificationPanel();
		}
		return classificationPanel;
	}
	
	
	/**
	 * 
	 */
	private QuestionProcessingPanel getQuestionProcessingPanel() {
		if(questionProcessingPanel == null) {
			questionProcessingPanel = new QuestionProcessingPanel();
		}
		return questionProcessingPanel;
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public int getSelectedPanel() {
		return getSelectedIndex();
	};
	
	/**
	 * 
	 * 
	 */
	public static void updateTextOfComponents() { 
		
		ontologyPanel.updateTextOfComponents();
		//more panel update text of here....
	}
}
