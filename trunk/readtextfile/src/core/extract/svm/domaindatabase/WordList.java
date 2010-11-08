/**
 * WordList.java
 * @Author : Huynh Minh Duc
 * 9:23:56 PM 
 */
package core.extract.svm.domaindatabase;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import utilily.extract.svm.HeaderReader;
import utilily.extract.svm.LabelConst;

import core.extract.svm.Header;
import core.extract.svm.Line;
import core.extract.svm.Word;

/**
 * @author DuyVo
 *
 */
public class WordList {
	Hashtable<Integer, String> wordList = null;
	int name;
	
	WordList() {	
		wordList = new Hashtable<Integer, String>();
		name = 0;
	}
	
	WordList(Header[] headers, int nameOfWordList, int labelPattern, int numberOfWord) {
		wordList = new Hashtable<Integer, String>();
		name = nameOfWordList;
		StringBuilder wordListString = new StringBuilder();
				
		for (int i = 0; i < 500; i++) {
			ArrayList<Line> linesWordList = headers[i].getLineWithLabel(labelPattern);
			
			for (Line line : linesWordList) {
				wordListString.append(" ");
				wordListString.append(line.getContent());
			}			
		}
		// Statistic word list
		
		String wordListRemoved = wordListString.toString().replaceAll(",", "");
		String[] Tokens = wordListRemoved.split(" ");
		ArrayList<WordDic> tempWordList = new ArrayList<WordDic>();
		
		// Stop List Word
		String pathStopWordListFile = "StopWordList.txt";	
		ArrayList<String> stopWordList = HeaderReader.readTextFile(pathStopWordListFile);
		
		for (int i = 0; i < Tokens.length; i++) {
			if(!Tokens[i].trim().equals("")){
				
				//check stop word
				boolean isStopWord = false;
				for(int j = 0; j < stopWordList.size(); j++){
					if(Tokens[i].trim().toLowerCase().equals(stopWordList.get(j).trim())){
						isStopWord = true;
						j = stopWordList.size();
					}
				}
				
				if(isStopWord == false){
					WordDic word = new WordDic(Tokens[i].trim().toLowerCase());	
					boolean existedWord = false;
					for (WordDic wordDic : tempWordList) {
						if (word.getContent().equals(wordDic.getContent())) {
							existedWord = true;
							wordDic.increaseDFValue();
						}
					}
					if(existedWord == false){	
						tempWordList.add(word);
					}
				}
			}
		}
		
		// sort word in WordList
		for (int i = 0; i < tempWordList.size() - 1; i++) {			
			for (int j = i; j < tempWordList.size(); j++) {
				if(tempWordList.get(i).getDfValue() < tempWordList.get(j).getDfValue()){
					WordDic wordTemp = tempWordList.get(i);
					tempWordList.set(i, tempWordList.get(j));
					tempWordList.set(j, wordTemp);
				}
			}
		}
		
		// add number of word into WordList
		for (int i = 0; i < numberOfWord; i++) {
			wordList.put(tempWordList.get(i).getContent().hashCode(),tempWordList.get(i).getContent());
		}
	}
	
	public boolean search(String word) {
		return this.wordList.containsKey(word.toLowerCase().hashCode());
	}
	
	public void printOut() {
		Enumeration<String> e = wordList.elements();
        while( e. hasMoreElements() ){   
            System.out.println(e.nextElement());
        }
	}

	public Hashtable<Integer, String> getwordList() {
		return wordList;
	}

	public void setwordList(Hashtable<Integer, String> wordList) {
		this.wordList = wordList;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}	
}
