/**
 * Dictionary.java
 * @Author : Huynh Minh Duc
 * 6:02:55 PM 
 */
package core.extract.svm.domaindatabase;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import core.extract.svm.*;
/**
 * @author DuyVo
 *
 */
public class Dictionary {
	Hashtable<Integer, String> dict = null;
	int name;
	
	public Dictionary() {
		dict = new Hashtable<Integer, String>();
		name = 0;
	}
	
	public Dictionary(int labelConst) {
		this.dict = new Hashtable<Integer, String>();
		this.name = labelConst;
	}
	
	public void addData(String pathFile) {
		dict = new Hashtable<Integer, String>();
		File file = new File(pathFile);
		
		try {
			BufferedReader bufferreader = new BufferedReader(new FileReader(file));
			String line;
			int i = -1;
			
			while((line = bufferreader.readLine()) != null) {
				if(line.indexOf(" ") != -1) {					
					String[] words = line.split(" ");
					for(String word : words) {
						if(word.hashCode() != 0){ // remove line empty
							dict.put(word.toLowerCase().hashCode(), word.toLowerCase());
						}
					}
				} else {
					i++;
					dict.put(line.toLowerCase().hashCode(), line.toLowerCase());
				}
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public void printOut() {
		Enumeration<String> e = dict.elements();
        while( e. hasMoreElements() ){   
            System.out.println(e.nextElement());
        }
	}
	
	public boolean search(String word) {
		return this.dict.containsKey(word.toLowerCase().hashCode());		
	}
	
	public Hashtable<Integer, String> getWords() {
		return dict;
	}

	public void setWords(Hashtable<Integer, String> dict) {
		this.dict = dict;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}
	
	
}
