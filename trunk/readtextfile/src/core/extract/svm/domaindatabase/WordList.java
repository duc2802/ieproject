/**
 * WordList.java
 * @Author : Huynh Minh Duc
 * 9:23:56 PM 
 */
package core.extract.svm.domaindatabase;

import java.util.ArrayList;

import utilily.extract.svm.HeaderReader;
import utilily.extract.svm.LabelConst;

import core.extract.svm.Header;
import core.extract.svm.Line;

/**
 * @author DuyVo
 *
 */
public class WordList {
	ArrayList<WordDic> wordList = null;
	int name;
	
	WordList() {	
		wordList = new ArrayList<WordDic>();
		name = 0;
	}
	
	WordList(Header[] headers, int labelConst, int numberOfWord) {
		wordList = new ArrayList<WordDic>();
		name = labelConst;
		StringBuilder wordListString = new StringBuilder();
				
		for (int i = 0; i < 500; i++) {
			ArrayList<Line> linesWordList = headers[i].getLineWithLabel(labelConst);
			
			for (Line line : linesWordList) {
				wordListString.append(" ");
				wordListString.append(line.getContent());
			}			
		}
		System.out.println(headers.length);
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
			wordList.add(tempWordList.get(i));
		}
	}
	
	public boolean search(String word) {
		for (int i = 0; i < wordList.size(); i++) {
			if(wordList.get(i).getContent().equals(word)) {
				return true;
			}					
		}
		return false;
	}
	
	public void printOut() {
		for (int i = 0; i < wordList.size(); i++) {
			System.out.println(wordList.get(i).getContent().toString() + ": " + wordList.get(i).getDfValue());
		}
	}

	public ArrayList<WordDic> getwordList() {
		return wordList;
	}

	public void setwordList(ArrayList<WordDic> wordList) {
		this.wordList = wordList;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}	
}
