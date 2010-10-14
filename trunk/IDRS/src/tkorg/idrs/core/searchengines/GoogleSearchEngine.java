/**
 * Created by: HNTin & Date: Apr 27, 2010
 * modify :tiendv
 * return a arraylist , extens Thread for mutithreadseach
 *  chage result object
 */
package tkorg.idrs.core.searchengines;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Simple Search using Google ajax Web Services
 * 
 * @author Daniel Jones Copyright 2006 Daniel Jones Licensed under BSD open
 *         source license http://www.opensource.org/licenses/bsd-license.php
 */

public class GoogleSearchEngine extends Thread {
	


	private String queryString;
	private int maxResult;
	private ArrayList<String> resultGoogleArrayList = null;
	
	// Put your website here
	public final static String HTTP_REFERER = "http://www.example.com/";

	public static ArrayList<String> makeQuery(String query, int maxResult) {
		ArrayList<String> finalArray = new ArrayList<String>();
		ArrayList<String> returnArray = new ArrayList<String>();
		try {		
			query = URLEncoder.encode(query, "UTF-8");
			int i = 0;
			String line = "";
			StringBuilder builder = new StringBuilder();
			while (true) {
				
				// Call GoogleAjaxAPI to submit the query
				URL url = new URL("http://ajax.googleapis.com/ajax/services/search/web?start=" + i + "&rsz=large&v=1.0&q=" + query);

				URLConnection connection = url.openConnection();
				if (connection == null) {
					break;
				}
				
				// Value i to stop while or Max result
				if (i >= maxResult) {
					break;
				}

				connection.addRequestProperty("Referer", HTTP_REFERER);

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}

				String response = builder.toString();
				JSONObject json = new JSONObject(response);
				JSONArray ja = json.getJSONObject("responseData").getJSONArray("results");

				for (int j = 0; j < ja.length(); j++) {
					try {
						JSONObject k = ja.getJSONObject(j);		
						// Break string into 2 parts: URL and Title by <br>
						
						returnArray.add(k.getString("url") + "<br>" + k.getString("titleNoFormatting"));
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				i += 8;
			}
			
			// Remove objects that is over the max number result required
			if (returnArray.size() > maxResult) {
				for (int k=0; k<maxResult; k++){
					finalArray.add(returnArray.get(k));
				}
			}
			else 
				return returnArray;
			
			return finalArray;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	@Override
	public void run() {
		this.resultGoogleArrayList = GoogleSearchEngine.makeQuery(queryString, maxResult);
	}
	public ArrayList<String> getResultGoogleArrayList() {
		return resultGoogleArrayList;
	}

	public void setResultGoogleArrayList(ArrayList<String> resultGoogleArrayList) {
		this.resultGoogleArrayList = resultGoogleArrayList;
	}

	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

}
