
package tkorg.idrs.core.searchengines;

import java.util.ArrayList;
import java.util.Hashtable;
import tkorg.idrs.action.searchengines.GetPageFromURLAction;

/**
 * @author Huynh Ngoc Tin
 * modify: tiendv 13/5
 * Add thread for getpageresult
 */
public class SearchEngineResults {
	public static Hashtable<String, String> yahooHashTable = new Hashtable<String, String>();
	public static Hashtable<String, String> googleHashTable = new Hashtable<String, String>();
	/**
	 * 
	 * @param googleResult : Results from Google
	 * @param maxGoogleResult : Max results from Google
	 * @throws InterruptedException
	 */
	public static void putGoolgeResultIntoHashTable(ArrayList<String> googleResult, int maxGoogleResult) throws InterruptedException {
		// put result into googleHashTable
		
		if (googleHashTable != null) {
			for (int i = 0; i < maxGoogleResult; i++) {
				String[] temp = googleResult.get(i).split("<br>");
				String resultURL = temp[0];
				String resultPageContentFromURL=null;
				Thread getPageContentThead = new GetPageFromURLAction();
				((GetPageFromURLAction)getPageContentThead).setTargerURL(resultURL);
				getPageContentThead.join();
				resultPageContentFromURL = ((GetPageFromURLAction)getPageContentThead).getStringPageContent();
				googleHashTable.put(resultURL, resultPageContentFromURL);
				getPageContentThead.stop();
				
			}
		}
	}
	/**
	 * 
	 * @param yahooSearchResult: result from Yahoo
	 * @param maxYahooResult : max results from user
	 * @throws InterruptedException
	 */
	
	public static void putYahooResultIntoHashTable(ArrayList<String> yahooSearchResult, int maxYahooResult) throws InterruptedException {
		// Put result in yahooHasstable
	
			if (yahooHashTable != null) {
				for (int i = 0; i < maxYahooResult; i++) {
					String[] temp = yahooSearchResult.get(i).split("<br>");
					String resultURL = temp[0];
					String resultPageContentFromURL=null;
					Thread getPageContentThead = new GetPageFromURLAction();
					((GetPageFromURLAction)getPageContentThead).setTargerURL(resultURL);
					getPageContentThead.join();
					resultPageContentFromURL = ((GetPageFromURLAction)getPageContentThead).getStringPageContent();
					yahooHashTable.put(resultURL, resultPageContentFromURL);
					getPageContentThead.stop();
				}
			}
		
	}

	public static Hashtable<String, String> getYahooResultHastable() {
		return yahooHashTable;
	}
	public static  Hashtable<String, String> getGoogleResultHastable() {
		return googleHashTable;
	}
	
}
