package tkorg.idrs.action.searchengines;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tkorg.idrs.core.searchengines.FileLoadder;
import tkorg.idrs.core.searchengines.GoogleSearchEngine;
import tkorg.idrs.core.searchengines.SearchEngineResults;
import tkorg.idrs.core.searchengines.Token;
import tkorg.idrs.core.searchengines.YahooSearchEngine;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.gui.searchengines.DownLoadDialog;
/**
 * @author Huynh Ngoc Tin
 * Modifier : tiendv 
 * 
 */
public class SearchEnginesAction {
	static ArrayList<String> googleSearchResult = null;
	static ArrayList<String> yahooSearchResult = null;

	public SearchEnginesAction() {
	}
	
	/**
	 * Submit Query To Google
	 * @param queryString : A String for query 
	 * @param googleChecked: if true submit query to google . 
	 * @param maxGoogleResult : Max reuslt from user
	 * @return: Arraylist String from Google include HTML Tag,...
	 * @throws InterruptedException
	 */
	
	public  ArrayList<String> submitQueryToGoogle(String queryString, boolean googleChecked, int maxGoogleResult) throws InterruptedException {
		if (googleChecked == true) {
			Thread googleSearchEngineThread = new GoogleSearchEngine();	
			((GoogleSearchEngine) googleSearchEngineThread).setQueryString(queryString);
			((GoogleSearchEngine) googleSearchEngineThread).setMaxResult(maxGoogleResult);
			googleSearchEngineThread.start();
			googleSearchEngineThread.join();
			googleSearchResult = ((GoogleSearchEngine) googleSearchEngineThread).getResultGoogleArrayList();
			googleSearchEngineThread.stop();
		} 
		return googleSearchResult;
	}
	
	/**
	 * Submit Query To Yahoo
	 * @param queryString : String for query
	 * @param yahooChecked : if true submit to yahoo
	 * @param maxYahooResult : Max reuslt from user
	 * @return : ArrayList String from yahoo after sumbit to Yahoo
	 */
	
	public ArrayList<String> submitQueryToYahoo(String queryString, boolean yahooChecked, int maxYahooResult) {
	
		
		if (yahooChecked == true) {
			Thread yahooSearchEngineThread = new  YahooSearchEngine("Jm3V0PbV34GKpO58IjWbVvW26XjoKlrkriC2D4idXRBm8No3VDoCCjQLhBqsjJ9wRVI");
			((YahooSearchEngine)yahooSearchEngineThread).setQuery(queryString);
			((YahooSearchEngine)yahooSearchEngineThread).setLimit(maxYahooResult);			
			yahooSearchEngineThread.start();
			try {
				yahooSearchEngineThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			yahooSearchResult = new ArrayList<String>();
			for (int i = 0; i < maxYahooResult; i++) {
				yahooSearchResult.add(((YahooSearchEngine) yahooSearchEngineThread).getUrls()[i] + "<br>" + ((YahooSearchEngine) yahooSearchEngineThread).getTitles()[i]);
			}	
			yahooSearchEngineThread.stop();
		} 
		
		return yahooSearchResult;
	}
	/**
	 * Download SeachEnginesResults into File 
	 * @param queryPanel
	 * @param queryString : String for query 
	 * @throws IOException
	 */
	public static void downloadSeachEnginesResults(JPanel queryPanel, String queryString) throws IOException {
			
		DownLoadDialog downloadDialog = new DownLoadDialog();
		int result = downloadDialog.showSaveDialog(queryPanel);
		if(result == DownLoadDialog.APPROVE_OPTION) {
			if(googleSearchResult!= null) {
				int maxResultGoogleSearch = googleSearchResult.size();
				try {
					SearchEngineResults.putGoolgeResultIntoHashTable(googleSearchResult, maxResultGoogleSearch);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Hashtable<String, String> googleHashTableResult = new Hashtable<String, String>();
				googleHashTableResult = SearchEngineResults.getGoogleResultHastable();
				Properties prop = new Properties();
				prop.putAll(googleHashTableResult);
			    FileOutputStream fileOS = new FileOutputStream(downloadDialog.getSelectedFile().getAbsolutePath() +"\\"+"google"+queryString+".txt");
			    try {
			       prop.store(fileOS,"-- Save Seach Data for String --"+queryString);
			    } finally {
			    	fileOS.close();
			    }	 
			}
			
			if(yahooSearchResult!= null) {
				int maxResultYahooSearch = googleSearchResult.size();
				try {
					SearchEngineResults.putYahooResultIntoHashTable(yahooSearchResult, maxResultYahooSearch);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Hashtable<String, String> yahooHashTableResult = new Hashtable<String, String>();
				yahooHashTableResult = SearchEngineResults.getYahooResultHastable();
				Properties prop = new Properties();
				prop.putAll(yahooHashTableResult);
			    FileOutputStream fileOS = new FileOutputStream(downloadDialog.getSelectedFile().getAbsolutePath() +"\\"+"yahoo"+queryString+".txt");
			    try {
			       prop.store(fileOS, "-- Save Seach Data for String --"+queryString);
			    } finally {
			    	fileOS.close();
			    }
			}
			JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("successful"));
		}
	
	}
}
				 
		

