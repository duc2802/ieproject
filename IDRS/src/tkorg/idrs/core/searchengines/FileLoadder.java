package tkorg.idrs.core.searchengines;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import tkorg.idrs.properties.files.*;

/**
 * @author tiendv
 * Read File 
 */
public class FileLoadder {
	//private static final String ListStopWords_TXT = "properties/files/ListStopWords.txt";
	/**
	 * return Arraylist Stopword From list File
	 */
	public ArrayList<String>  loadListStopwordsList(){
		ArrayList<String> lst = new ArrayList<String>();
		File file = new File("C:\\ListStopWords.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			while (dis.available() != 0) {
				String temp = dis.readLine();
				if (temp != " " && temp != null) {
					lst.add(temp);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				bis.close();
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lst;
	}
	
	/**
	 * 
	 * @param filename : URI file searchResult .
	 * @return : return a map  contain searchResult 
	 * @throws IOException
	 */
	
	public static Map<String, String> load(String filename) throws IOException {
	    FileReader reader = new FileReader(filename);
	    Properties props = new Properties();
	    try {
	    	props.load(reader);
	    } finally {
	        reader.close();
	    }
	    Map<String, String> myMap = new HashMap<String, String>();
	    for (Object key : props.keySet()) {
	        myMap.put(key.toString(), props.get(key).toString());
	    }
	    return myMap;
	}
	
}
