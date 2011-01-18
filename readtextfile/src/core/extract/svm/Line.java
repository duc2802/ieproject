package core.extract.svm;

import java.util.ArrayList;

import core.extract.svm.cluster.WordOrthogrophic;
import core.extract.svm.domaindatabase.DatabaseDic;
import core.extract.svm.domaindatabase.WordDic;

public class Line {
	
	private String content;
	private int label; //1..15 : constrain in a other file.
	private int independentLable;
	private ArrayList<Word> words;
	private LineSpecificFeature lineSpecificFeature;
	private ContextSpecificFeature contextSpecificFeature;
	
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
		int countAddress = 0;
		int countDate = 0;
		int countDegree = 0;
		int countPublish = 0;
		int countNote = 0;
		int countPhone = 0;
		
		for (Word word : this.words) {
			if(word.isDicWord()) countDictWordInLine++;	
			if(word.isNonDicWord()) countNonDictWordInLine++;
			if(word.isCap1DicWord()) countCap1DictWordInLine++;
			if(word.isCap1NonDicWord()) countCap1NonDictWordInLine++;
			if(word.isDig()) countDigit++;
			if(word.isAff()) countAffilation++;
			if(word.isAddr()) countAddress++;
			if(word.isMonth()) countDate++;
			if(word.isDegree()) countDegree++;
			if(word.isPubNum()) countPublish++;
			if(word.isNoteNum()) countNote++;
			if(word.isPhone()) countPhone++;
		}
		
		this.lineSpecificFeature.setCDictWordNumPer((double)countDictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCNonDictWordNumPer((double)countNonDictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCCap1DictWordNumPer((double)countCap1DictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCCap1NonDictWordNumPer((double)countCap1NonDictWordInLine / totalWordsInLine * 100);
		this.lineSpecificFeature.setCDigitNumPer((double)countDigit / totalWordsInLine * 100);
		this.lineSpecificFeature.setCAffiNumPer((double)countAffilation / totalWordsInLine * 100);
		this.lineSpecificFeature.setCAddNumPer((double) countAddress / totalWordsInLine * 100);
		this.lineSpecificFeature.setCDateNumPer((double) countDate / totalWordsInLine * 100);
		this.lineSpecificFeature.setCDegreeNumPer((double) countDegree / totalWordsInLine * 100);
		this.lineSpecificFeature.setCPubNumPer((double) countPublish / totalWordsInLine * 100);
		this.lineSpecificFeature.setCNoteNumPer((double) countNote / totalWordsInLine * 100);
		this.lineSpecificFeature.setCPhoneNumPer((double) countPhone / totalWordsInLine * 100);
		
	}
	
	public void normalizationVector(){
		double maxValue = this.lineSpecificFeature.maxValue();
		this.lineSpecificFeature.setCAddNumPer(this.lineSpecificFeature.getCAddNumPer() / maxValue);
		this.lineSpecificFeature.setCAffiNumPer(this.lineSpecificFeature.getCAffiNumPer() / maxValue);
		this.lineSpecificFeature.setCCap1DictWordNumPer(this.lineSpecificFeature.getCCap1DictWordNumPer() / maxValue);
		this.lineSpecificFeature.setCCap1NonDictWordNumPer(this.lineSpecificFeature.getCCap1NonDictWordNumPer() / maxValue);
		this.lineSpecificFeature.setCDateNumPer(this.lineSpecificFeature.getCDateNumPer() / maxValue);
		this.lineSpecificFeature.setCDegreeNumPer(this.lineSpecificFeature.getCDegreeNumPer() / maxValue);
		this.lineSpecificFeature.setCDictWordNumPer(this.lineSpecificFeature.getCDictWordNumPer() / maxValue);
		this.lineSpecificFeature.setCDigitNumPer(this.lineSpecificFeature.getCDigitNumPer() / maxValue);
		this.lineSpecificFeature.setCLinePosition(this.lineSpecificFeature.getCLinePosition() / maxValue);
		this.lineSpecificFeature.setCNonDictWordNumPer(this.lineSpecificFeature.getCNonDictWordNumPer() / maxValue);
		this.lineSpecificFeature.setCNoteNumPer(this.lineSpecificFeature.getCNoteNumPer() / maxValue);
		this.lineSpecificFeature.setCPageNumPer(this.lineSpecificFeature.getCPageNumPer() / maxValue);
		this.lineSpecificFeature.setCPhoneNumPer(this.lineSpecificFeature.getCPhoneNumPer() / maxValue);
		this.lineSpecificFeature.setCPubNumPer(this.lineSpecificFeature.getCPubNumPer() / maxValue);
		this.lineSpecificFeature.setCSentenceLength(this.lineSpecificFeature.getCSentenceLength() / maxValue);
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
	
	public void setContextSpecificFeature(ContextSpecificFeature c) {
		this.contextSpecificFeature = c;
	}
	
	public ContextSpecificFeature getContextSpecificFeature(){
		return this.contextSpecificFeature;
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
	public int getIndependentLabel() {
		return independentLable;
	}
	public void setIndependentLabel(int label) {
		this.independentLable = label;
	}
	
}
