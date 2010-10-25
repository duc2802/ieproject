package gui.extract.svm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import core.extract.svm.Header;
import core.extract.svm.Line;
import core.extract.svm.Word;
import core.extract.svm.domaindatabase.DatabaseDic;
import core.extract.svm.domaindatabase.WordList;

import utilily.extract.svm.HeaderReader;
import utilily.extract.svm.LabelConst;

public class Main {
	
	/**
	 * @param args
	 */
	public static final String separateLine = "\n";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String pathFile = "text.txt";
		String pathFile = "tagged_headers.txt";
		String pathStopWordFile = "StopWordList.txt";
				
		String[] headersText = HeaderReader.read(pathFile);
		Header[] headers = new Header[headersText.length];
		ArrayList<String> stopWordList = HeaderReader.readTextFile(pathStopWordFile );
		for (int i = 0; i < headersText.length; i++) {
			headers[i] = new Header(headersText[i]);		
		}	
		
		for (Header header : headers){
			ArrayList<Line> ls = header.getLineWithLabel(LabelConst.AUTHOR);			
			for (Line line : ls) {
				System.out.println(line.getContent());
				System.out.println(" CsenLen : " + line.getFeature().getCSentenceLength());
				line.calculateWordSpecific();
				ArrayList<Word> ws = line.getWords();
				for (Word word : ws){ 
					System.out.print(word.getContent() + "\t : ");
					ArrayList<Integer> orthographic = word.getOrthogrophicFeature();	
					if(orthographic != null){
						for (Integer integer : orthographic) {
							System.out.print(integer);
						}
						System.out.println();
					}else {
						System.out.println();
					}
				}
			}
		}		
		
		//String[] headersText = HeaderReader.read(pathFile);
		//Header[] headers = new Header[headersText.length];
		int countHeader = 0;
		for (int i = 0; i < headersText.length; i++) {
			headers[i] = new Header(headersText[i]);
			countHeader++;
		}
		
		StringBuilder affiliationString = new StringBuilder();
		StringBuilder addressString = new StringBuilder();
		
		System.out.println("Counted Header : " + countHeader);
		
		//test database
		DatabaseDic data = new DatabaseDic(headers);
		Word word = new Word();
		word.setContent("al");
		ArrayList<Integer> result = data.search(word);
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + "    ");
		}
		/*for (int i = 0; i < 500; i++) {
			//Line Affiliation
			ArrayList<Line> linesAffiliation = headers[i].getLineWithLabel(LabelConst.AFFILIATION);
			
			for (Line line : linesAffiliation) {
				affiliationString.append(" ");
				affiliationString.append(line.getContent());
			}
			
			ArrayList<Line> linesAddress = headers[i].getLineWithLabel(LabelConst.ADDRESS);
			for (Line line : linesAddress) {
				addressString.append(line.getContent());
			}
		}
		
		System.out.println(affiliationString.toString());
		
		// Statistic Affiliation word list
		
		String affiliationRemoved = affiliationString.toString().replaceAll(",", "");		
		String[] affiTokens = affiliationRemoved.split(" ");
		ArrayList<WordList> affiliationListWord = new ArrayList<WordList>();
		for (int i = 0; i < affiTokens.length; i++) {
			if(!affiTokens[i].trim().equals("")){
				
				//check stop word
				boolean isStopWord = false;
				for(int j = 0; j < stopWordList.size(); j++){
					if(affiTokens[i].trim().toLowerCase().equals(stopWordList.get(j).trim())){
						isStopWord = true;
						j = stopWordList.size();
					}
				}
				
				if(isStopWord == false){
					WordList word = new WordList(affiTokens[i].trim().toLowerCase());	
					boolean existedWord = false;
					for (WordList wordlist : affiliationListWord) {
						if (word.getContent().equals(wordlist.getContent())) {
							existedWord = true;
							wordlist.increaseDFValue();
						}
					}
					if(existedWord == false){	
						affiliationListWord.add(word);
					}
				}
			}
		}
		
		String addressRemoved = addressString.toString().replaceAll(",", "");		
		String[] addressTokens = addressRemoved.split(" ");
		ArrayList<WordList> addressListWord = new ArrayList<WordList>();
		for (int i = 0; i < addressTokens.length; i++) {
			if(!addressTokens[i].trim().equals("")){
				
				//check stop word
				boolean isStopWord = false;
				for(int j = 0; j < stopWordList.size(); j++){
					if(addressTokens[i].trim().toLowerCase().equals(stopWordList.get(j).trim())){
						isStopWord = true;
						j = stopWordList.size();
					}
				}
				
				if(isStopWord == false){
					WordList word = new WordList(addressTokens[i].trim().toLowerCase());	
					boolean existedWord = false;
					for (WordList wordlist : addressListWord) {
						if (word.getContent().equals(wordlist.getContent())) {
							existedWord = true;
							wordlist.increaseDFValue();
						}
					}
					if(existedWord == false){	
						addressListWord.add(word);
					}
				}
			}
		}
		
		
		//sort affiliation word list data largest to 10		
		for (int i = 0; i < affiliationListWord.size() - 1; i++) {			
			for (int j = i; j < affiliationListWord.size(); j++) {
				if(affiliationListWord.get(i).getDfValue() < affiliationListWord.get(j).getDfValue()){
					WordList wordTemp = affiliationListWord.get(i);
					affiliationListWord.set(i, affiliationListWord.get(j));
					affiliationListWord.set(j, wordTemp);
				}
			}
		}
		
		for (int i = 0; i < addressListWord.size() - 1; i++) {			
			for (int j = i; j < addressListWord.size(); j++) {
				if(addressListWord.get(i).getDfValue() < addressListWord.get(j).getDfValue()){
					WordList wordTemp = addressListWord.get(i);
					addressListWord.set(i, addressListWord.get(j));
					addressListWord.set(j, wordTemp);
				}
			}
		}
		
		for (int i = 0; i < 20; i++) {			
			System.out.println(affiliationListWord.get(i).getContent() + " : " + affiliationListWord.get(i).getDfValue());
		}*/
	
	}
}
