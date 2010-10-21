/**
 * WordLists.java
 * @Author : Huynh Minh Duc
 * 8:40:15 AM 
 */
package core.extract.svm.domaindatabase;

import java.util.ArrayList;

import utilily.extract.svm.LabelConst;

import core.extract.svm.Header;
import core.extract.svm.Word;

/**
 * @author DuyVo
 *
 */
public class WordLists {
	public static int NUMBER_WORD_IN_WORDLIST = 20;
	ArrayList<WordList> wordLists = null;
	
	public WordLists(Header[] headers) {
		wordLists = new ArrayList<WordList>();
		
		WordList wordListAffiliation = new WordList(headers, LabelConst.AFFILIATION, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListAffiliation);
		
		WordList wordListAddress = new WordList(headers, LabelConst.ADDRESS, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListAddress);
		
		WordList wordListPhone = new WordList(headers, LabelConst.PHONE, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListPhone);
		
		WordList wordListNote = new WordList(headers, LabelConst.NOTE, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListNote);		
	}
	
	public void printOut() {
		for (int i = 0; i < wordLists.size(); i++) {
			System.out.println(wordLists.get(i).getName());
			wordLists.get(i).printOut();
		}
	}
	public WordList getWordList(int labelConst) {
		for (int i = 0; i < wordLists.size(); i++) {
			if (wordLists.get(i).getName() == labelConst)
				return wordLists.get(i);
		}
		return null;
	}
	
	public ArrayList<Integer> search(Word word) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < wordLists.size(); i++) {
			if(wordLists.get(i).search(word.getContent())) {
				result.add(wordLists.get(i).getName());
			}
		}
		return result;
	}

	public ArrayList<WordList> getWordLists() {
		return wordLists;
	}

	public void setWordLists(ArrayList<WordList> wordLists) {
		this.wordLists = wordLists;
	}
	
	
}
