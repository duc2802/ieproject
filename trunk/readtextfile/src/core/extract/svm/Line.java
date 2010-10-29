package core.extract.svm;

import java.util.ArrayList;

import core.extract.svm.cluster.WordOrthogrophic;
import core.extract.svm.domaindatabase.DatabaseDic;
import core.extract.svm.domaindatabase.WordDic;

public class Line {
	
	private String content;
	private int label; //1..15 : constrain in a other file.
	private ArrayList<Word> words;
	private LineSpecificFeature lineSpecificFeature;
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public Line(){
		content = null;
		lineSpecificFeature = new LineSpecificFeature();		
	}
	
	/**
	 * 
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public void genarateVectorFeature(){
		double totalWordsInLine = this.getFeature().getCSentenceLength();
		int countDictWordInLine = 0;
		int countNonDictWordInLine = 0;
		int countCap1DictWordInLine = 0;
		int countCap1NonDictWordInLine = 0;
		int countDigit = 0;
		int countAffilation = 0;
		for (Word word : this.words) {
			if(word.isDicWord()) countDictWordInLine++;	
			if(word.isNonDicWord()) countNonDictWordInLine++;
			if(word.isCap1DicWord()) countCap1DictWordInLine++;
			if(word.isCap1NonDicWord()) countCap1NonDictWordInLine++;
			if(word.isDig()) countDigit++;
			if(word.isAff()) countAffilation++;
		}
		this.lineSpecificFeature.setCDictWordNumPer((double)countDictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCNonDictWordNumPer((double)countNonDictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCCap1DictWordNumPer((double)countCap1DictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCCap1NonDictWordNumPer((double)countCap1NonDictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCDigitNumPer((double)countDigit / totalWordsInLine * 100);
		this.lineSpecificFeature.setCAffiNumPer((double)countAffilation / totalWordsInLine * 100);
	}
	
	/**
	 * 
	 * @param data
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public void calculateWordSpecific(DatabaseDic data){
		for (Word w : words) {
			w.calculateWordSpecificFeature(data);
		}
	}
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public Line(String content){
		words = new ArrayList<Word>();
		this.content = content;
		this.lineSpecificFeature = new LineSpecificFeature();
		this.words = this.toWord(content);
	}
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public Line(String content, int label){
		words = new ArrayList<Word>();
		this.content = content;
		this.lineSpecificFeature = new LineSpecificFeature();
		this.label = label;
		this.words = this.toWord(content);
	}
	
	public ArrayList<Word> toWord(String lineContent){
		ArrayList<Word> wordTemp = new ArrayList<Word>();
		lineContent = lineContent.replaceAll("\\-", " ");
		lineContent = lineContent.replaceAll("\\.", "");
		String[] splitSpace = lineContent.replaceAll(",", "").split(" ");				
		int countWordInLine = 0;
		for (String string1 : splitSpace) {
			if(!string1.equals("")){
				Word w = new Word();
				w.setContent(string1.trim());
				wordTemp.add(w);
				countWordInLine++;
			}
		}
		lineSpecificFeature.setCSentenceLength(countWordInLine);
		return wordTemp;
	}
	
	
	
	public ArrayList<Word> getWords() {
		return words;
	}

	public void setWords(ArrayList<Word> words) {
		this.words = words;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLabel() {
		return label;
	}
	public void setLabel(int label) {
		this.label = label;
	}
	public LineSpecificFeature getFeature() {
		return lineSpecificFeature;
	}
	public void setFeature(LineSpecificFeature feature) {
		this.lineSpecificFeature = feature;
	}
	
	
}