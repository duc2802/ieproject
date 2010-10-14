package websphinx.workbench;

import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Box.Filler;

import com.sun.org.apache.bcel.internal.generic.NEW;

import websphinx.*;

@SuppressWarnings("serial")
public class Crawling extends Panel{
	private Crawler crawler;
	
	private SimpleCrawlerEditor simpleCrawlerEditor;
		
	private EventLog logger;
	private JPanel outlineJPanel;
	private WebOutline outline;
	private DownloadParameters downloadParameter = new DownloadParameters();
	public static FilterProperties filter = new FilterProperties();
	
	private ArrayList<String> urlList = new ArrayList<String>();
	private ArrayList<String> htmlURLList = new ArrayList<String>();
	private ArrayList<String> pdfURLList = new ArrayList<String>();
	private ArrayList<String> docURLList = new ArrayList<String>();
	private ArrayList<String> docxURLList = new ArrayList<String>();
	private ArrayList<String> xmlURLList = new ArrayList<String>();
	private ArrayList<String> textURLList = new ArrayList<String>();
	
	private int linkNumber = 0;
	private int docNumber = 0;
	
//	private boolean pdfType;
//	private boolean docType;
//	private boolean docxType;
	
	public Crawling(){
		this(makeDefaultCrawler());
		return;
	}
	
	private static Crawler makeDefaultCrawler(){
		Crawler crawler = new Crawler();
		crawler.setDomain(Crawler.SUBTREE);		
		return crawler;
	}
	
	public Crawling(Crawler crawler){
		Browser browser = Context.getBrowser();
		
		makeDefaultCrawler();
		simpleCrawlerEditor = new SimpleCrawlerEditor();		
				
		outlineJPanel = new JPanel();
		
		logger = new EventLog();
		
		outline = new WebOutline();
		
		if (browser != null) {
			outline.addLinkViewListener(browser);
		}
		
		outline.setForeground(Color.blue);
		outline.setBackground(Color.white);
		
		outlineJPanel.add(outline);
		outlineJPanel.setVisible(true);
		
		setCrawler(crawler);
		
		this.setURL(this.getURL());
	}
	
	public void getLink(Crawler crawler){
		
	}
	public void setCrawler(Crawler crawler){
		if(this.crawler != crawler){
			if(this.crawler != null){
				clear();
				disconnectVisualization(this.crawler, this, false);
				disconnectVisualization(this.crawler, outline, true);
				disconnectVisualization(this.crawler, logger, true);
			}
			
			connectVisualization(crawler, this, false);
			connectVisualization(crawler, outline, true);
			connectVisualization(crawler, logger, true);
		}		
		crawler.setAction(null);
		this.crawler =  crawler;
		
		//set configuration crawler		
		crawler.setMaxDepth(filter.getDepth());
		simpleCrawlerEditor.setCrawler(crawler);
	}
	
	public Crawler getCrawler(){
		return crawler;
	}
	
	public void connectVisualization(Crawler crawler, Object viz, boolean linksToo){
		if(viz instanceof CrawlListener){
			crawler.addCrawlListener((CrawlListener)viz);
		}
		if(linksToo && viz instanceof LinkListener){
			crawler.addLinkListener((LinkListener)viz);
		}
	}
	
	public void disconnectVisualization(Crawler crawler, Object viz, boolean linksToo){
		if(viz instanceof CrawlListener){
			crawler.removeCrawlListener((CrawlListener)viz);
		}
		if(linksToo && viz instanceof LinkListener){
			crawler.removeLinkListener((LinkListener)viz);
		}
	}	

	//set and get links to crawl
	public void setLinksToCrawl(String url){		
		simpleCrawlerEditor.urlField.setText(url);
	}
	
	public String getLinksToCrawl(){
		return simpleCrawlerEditor.urlField.getText();
	}
	
	public void start(Crawler crawler){
		
		configureCrawler();
		
		if(crawler.getState() == CrawlEvent.STOPPED){
			crawler.clear();
		}
	
		Thread thread = new Thread(crawler, crawler.getName());
			
		thread.setDaemon(true);
		thread.start();			
	}

	public void configureCrawler(){	
		
		downloadParameter.setMaxThreads(filter.getThreads());		
		downloadParameter.setDownloadParamters(crawler);
	
		simpleCrawlerEditor.getCrawler();	
		//SaveCrawlerConfigure saveCrawlerConfigure = new SaveCrawlerConfigure();
		//saveCrawlerConfigure.getDirectoryToSave(crawler);
	}	
	
	public void pause(){
		this.crawler.pause();
	}
	
	public void stop(){
		this.crawler.stop();
	}

	public void clear(){
		this.crawler.clear();
	}

	public void setOutline(WebOutline outline){
		this.outline = outline;
	}
	
	public WebOutline getOutline(){		
		return this.outline;		
	}

	public void printHashtable(){
		Hashtable hashtable = new Hashtable();
		
		hashtable = outline.links;
		
		Enumeration e = hashtable.keys();
		ArrayList<String> urlList = new ArrayList<String>();
		while (e.hasMoreElements()) {

			String linkRoot = e.nextElement().toString();
			System.out.println(linkRoot);
			
			String[] temp = linkRoot.split("\"");
			
			for(int i = 0; i < temp.length; i++){
				if((temp[i].matches("(?i)http://.*") == true && temp[i].matches("(?i).*.pdf") == true && filter.getPDFType())
						|| (temp[i].matches("(?i)http://.*") == true && temp[i].matches("(?i).*.doc") == true && filter.getDocType())
						|| (temp[i].matches("(?i)http://.*") == true && temp[i].matches("(?i).*.docx") == true && filter.getDocxType())
						|| (temp[i].matches("(?i)http://.*") == true && temp[i].matches("(?i).*.xml") == true && filter.getXmlType())
						|| (temp[i].matches("(?i)http://.*") == true && temp[i].matches("(?i).*.text") == true && filter.getTextType())
						|| (temp[i].matches("(?i)http://.*") == true && temp[i].matches("(?i).*.html") == true && filter.getHtmlType())
						|| (temp[i].matches("(?i).*.pdf") == true && filter.getPDFType() == true)
						|| (temp[i].matches("(?i).*.doc") == true && filter.getDocType() == true)
						|| (temp[i].matches("(?i).*.docx") == true && filter.getDocxType())
						|| (temp[i].matches("(?i).*.xml") == true && filter.getXmlType())
						|| (temp[i].matches("(?i).*.text") == true && filter.getTextType())
						|| (temp[i].matches("(?i).*.html") == true && filter.getHtmlType()))
				{
					urlList.add(temp[i].toString());
				}
			}		

		}
		this.setURL(urlList);
	
		this.classificationType(urlList, filter.getPDFType(), filter.getDocType(), filter.getDocxType(),
				filter.getHtmlType(), filter.getXmlType(), filter.getTextType());
				
	}
	
	public void classificationType(ArrayList<String> url, boolean pdfType, boolean docType, boolean docxType,
			boolean htmlType, boolean xmlType, boolean textType ){
		for(int i = 0; i < url.size(); i++){
			if(url.get(i).matches("(?i).*.pdf") == true && pdfType == true){
				pdfURLList.add(url.get(i));
				this.setDocNumber(this.getDocNumber() + 1);
				this.setLinkNumber(this.getLinkNumber() + 1);
			}else if(url.get(i).matches("(?i).*.doc") == true && docType == true){
				docURLList.add(url.get(i));
				this.setDocNumber(this.getDocNumber() + 1);
				this.setLinkNumber(this.getLinkNumber() + 1);
			}else if(url.get(i).matches("(?i).*.docx") == true && docxType == true){
				docxURLList.add(url.get(i));
				this.setDocNumber(this.getDocNumber() + 1);
				this.setLinkNumber(this.getLinkNumber() + 1);
			}else if(url.get(i).matches("(?i).*.xml") == true && xmlType == true){
				xmlURLList.add(url.get(i));
				this.setDocNumber(this.getDocNumber() + 1);
				this.setLinkNumber(this.getLinkNumber() + 1);
			}else if(url.get(i).matches("(?i).*.text") == true && textType == true){
				textURLList.add(url.get(i));
				this.setDocNumber(this.getDocNumber() + 1);
				this.setLinkNumber(this.getLinkNumber() + 1);
			}else if(url.get(i).matches("(?i).*.gif") == true 
					|| url.get(i).matches("(?i).*.png") == true){
				
			}else if( htmlType == true){
				htmlURLList.add(url.get(i));
				this.setLinkNumber(this.getLinkNumber() + 1);
			}			
		}
	}
	
	public void setURL(ArrayList<String> urlList){
		this.urlList = urlList;
	}
	
	public ArrayList<String> getURL(){
		return urlList;
	}
	
	public void setDocNumber(int docNumber){
		this.docNumber = docNumber;
	}
	
	public int getDocNumber(){
		return this.docNumber;
	}
	
	public void setLinkNumber(int linkNumber){
		this.linkNumber = linkNumber;
	}
	
	public int getLinkNumber(){
		return this.linkNumber;
	}
}