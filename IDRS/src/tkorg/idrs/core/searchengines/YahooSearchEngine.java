package tkorg.idrs.core.searchengines;

import com.yahoo.search.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;

/**
 *  @author tiendv
 * This code is taken from YAHOO website, which describes how to use YAHOO
 * engine.
 */

public class YahooSearchEngine extends Thread {
	
	private String applicationID;
	private SearchClient client;
	WebSearchResults results;
	
	/**
	 * 
	 * @param applicationID : ID provide by Yahoo 
	 */
	
	public YahooSearchEngine(String applicationID) {
		this.applicationID = applicationID;
		init();
	}

	public void init() {
		client = new SearchClient(this.applicationID);
	}
	/**
	 * 
	 * @param query : String for query 
	 * @param limit : Max result from User
	 * @throws SearchException
	 * @throws IOException
	 */
	public void search(String query, int limit) throws SearchException,
			IOException {
		query = URLEncoder.encode(query, "UTF-8");
		WebSearchRequest request = new WebSearchRequest(query);
		request.setResults(limit);
		results = client.webSearch(request);
	}
	
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	//Get all result Available From Yahoo
	public int getTotalResultsAvailable() {
		int total = 0;
		if (results != null) {
			BigInteger count = results.getTotalResultsAvailable();
			total = count.intValue();
		}
		return total;
	}
	
	// Get tilets a result
	
	public String[] getTitles() {
		if (results == null)
			return null;
		WebSearchResult[] resultsArray = results.listResults();
		String[] titles = new String[resultsArray.length];
		for (int i = 0; i < titles.length; i++) {
			titles[i] = resultsArray[i].getTitle();
		}
		return titles;
	}
	
	//Get Url of SearchResult
	
	public String[] getUrls() {
		if (results == null)
			return null;
		WebSearchResult[] resultsArray = results.listResults();
		String[] urls = new String[resultsArray.length];
		for (int i = 0; i < urls.length; i++) {
			urls[i] = resultsArray[i].getUrl();
		}
		return urls;
	}
	// Get Summaries of SearchResult
	public String[] getSummaries() {
		if (results == null)
			return null;
		WebSearchResult[] resultsArray = results.listResults();
		String[] summaries = new String[resultsArray.length];
		for (int i = 0; i < summaries.length; i++) {
			summaries[i] = resultsArray[i].getSummary();
		}
		return summaries;
	}

	public WebSearchResult[] getResultsArray() {
		if (results == null)
			return null;
		return results.listResults();
	}
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	private String query;
	private int limit;

	@Override
	public void run() {
		try {
			this.search(query, limit);
		} catch (SearchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}