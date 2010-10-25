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
public class DatabaseDic {
	public static int NUMBER_WORD_IN_WORDLIST = 10;
	ArrayList<WordList> wordLists = null;
	ArrayList<Dictionary> dics = null;
	
	public DatabaseDic(Header[] headers) {
		wordLists = new ArrayList<WordList>();
		
		WordList wordListAffiliation = new WordList(headers, LabelWord.AFFILIATION, LabelConst.AFFILIATION, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListAffiliation);
		
		WordList wordListAddress = new WordList(headers, LabelWord.ADDRESS, LabelConst.ADDRESS, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListAddress);
		
		WordList wordListPhone = new WordList(headers, LabelWord.PHONE, LabelConst.PHONE, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListPhone);
		
		WordList wordListNote = new WordList(headers, LabelWord.NOTE, LabelConst.NOTE, NUMBER_WORD_IN_WORDLIST);
		wordLists.add(wordListNote);
		
		dics = new ArrayList<Dictionary>();
		
		Dictionary nameDic = new Dictionary(LabelWord.NAME);
		nameDic.addData("female_name.txt");
		nameDic.addData("male_name.txt");
		nameDic.addData("firstnames.txt");
		nameDic.addData("lastname.txt");
		dics.add(nameDic);
		
		Dictionary postCodeDic = new Dictionary(LabelWord.POSTCODE);
		postCodeDic.addData("postcode.txt");
		dics.add(postCodeDic);
		
		Dictionary monthDic = new Dictionary(LabelWord.MONTH);
		monthDic.addData("month.txt");
		dics.add(monthDic);
		
		Dictionary cityDic = new Dictionary(LabelWord.CITY);
		cityDic.addData("usa_city.txt");
		dics.add(cityDic);
		
		Dictionary stateDic = new Dictionary(LabelWord.STATE);
		stateDic.addData("usa_state.txt");
		stateDic.addData("canada_provinces.txt");
		dics.add(stateDic);
		
		Dictionary countryDic = new Dictionary(LabelWord.COUNTRY);
		countryDic.addData("country_name.txt");
		dics.add(countryDic);
	}
	
	public void printOut() {
		for (int i = 0; i < wordLists.size(); i++) {
			System.out.println(wordLists.get(i).getName());
			wordLists.get(i).printOut();
		}
		
		for (int i = 0; i < dics.size(); i++) {
			System.out.println(dics.get(i).getName());
			dics.get(i).printOut();
		}
	}
	public Object getWordList(int labelWord) {
		for (int i = 0; i < wordLists.size(); i++) {
			if (wordLists.get(i).getName() == labelWord)
				return wordLists.get(i);
		}
		
		for (int i = 0; i < dics.size(); i++) {
			if (dics.get(i).getName() == labelWord)
				return dics.get(i);
		}
		return null;
	}
	
	public ArrayList<Integer> search(String word) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < wordLists.size(); i++) {
			if(wordLists.get(i).search(word)) {
				if((wordLists.get(i).getName() == LabelWord.AFFILIATION) 
						|| (wordLists.get(i).getName() == LabelWord.NOTE)) {
					result.clear();
					result.add(wordLists.get(i).getName());
					return result;
				}
				result.add(wordLists.get(i).getName());
			}
		}
		
		for(int i = 0; i < dics.size(); i++) {
			if(dics.get(i).search(word)) {
				result.add(dics.get(i).getName());
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
