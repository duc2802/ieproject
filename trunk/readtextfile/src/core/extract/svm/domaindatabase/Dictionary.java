/**
 * Dictionary.java
 * @Author : Huynh Minh Duc
 * 6:02:55 PM 
 */
package core.extract.svm.domaindatabase;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import core.extract.svm.*;
/**
 * @author DuyVo
 *
 */
public class Dictionary {
	ArrayList<String> dict = null;
	int name;
	
	public Dictionary() {
		dict = new ArrayList<String>();
		name = 0;
	}
	
	public Dictionary(int labelConst) {
		this.dict = new ArrayList<String>();
		this.name = labelConst;
	}
	
	public void addData(String pathFile) {
		dict = new ArrayList<String>();
		File file = new File(pathFile);
		
		try {
			BufferedReader bufferreader = new BufferedReader(new FileReader(file));
			String line = null;
			
			while((line = bufferreader.readLine().toLowerCase()) != null) {
				if(line.indexOf(" ") != -1) {
					String[] words = line.toString().split(" ");
					for(String word : words) {						 
						if(word.hashCode() != 0) // remove line empty
							dict.add(word);
					}
				} else {
					dict.add(line);
				}
			}	
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	public void printOut() {
		for(int i = 0; i < dict.size(); i++) {
			System.out.println(dict.get(i));
		}
	}
	
	public boolean search(Word word) {
		for(int i = 0; i < dict.size(); i++) {
			if(dict.get(i).equals(word.getContent())) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> getWords() {
		return dict;
	}

	public void setWords(ArrayList<String> dict) {
		this.dict = dict;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}
	
	
}
