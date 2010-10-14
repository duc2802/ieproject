package tkorg.idrs.core.crawler;

import java.util.ArrayList;

public class CrawlerCore {
	private ArrayList<String> urls = new ArrayList<String>();
	
	public CrawlerCore(){
		this.setURL(this.getURL());
	}
	
	public CrawlerCore(ArrayList<String> urls){
		this.setURL(urls);
	}
	
	public void setURL(ArrayList<String> urls){
		this.urls = urls;
	}
	
	public ArrayList<String> getURL() {
		return this.urls;		
	}
}
