/**
 * @author tiendv
 * Get page content without HTML Tag use HTML Parser
 */
package tkorg.idrs.action.searchengines;

import org.htmlparser.beans.StringBean;

public class GetPageFromURLAction extends Thread {
	public GetPageFromURLAction() {
		this.start();
	}
	public String stringPageContent;
	public String targerURL;
	/**
	 * @param url : Url for get page content
	 * @return : String page content
	 */
	
	static public String getUrlContentsAsText(String url) { 
         String content = ""; 
         StringBean stringBean = new StringBean(); 
         stringBean.setURL(url); 
         content = stringBean.getStrings(); 
         return content; 
	} 
	
	@Override
	public void run() {
		this.stringPageContent=GetPageFromURLAction.getUrlContentsAsText(targerURL);
	}
	public String getStringPageContent() {
		return stringPageContent;
	}
	public void setStringPageContent(String stringPageContent) {
		this.stringPageContent = stringPageContent;
	}
	public String getTargerURL() {
		return targerURL;
	}
	public void setTargerURL(String targerURL) {
		this.targerURL = targerURL;
	}
	
}
