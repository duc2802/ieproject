package core.extract.svm;

import java.util.ArrayList;

import core.extract.svm.cluster.WordOrthogrophic;
import core.extract.svm.domaindatabase.WordList;

public class Line {
	
	private String content;
	private int label; //1..15 : constrain in a other file.
	private ArrayList<Word> words;
	private LineSpecificFeature feature;
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public Line(){
		content = null;
		feature = new LineSpecificFeature();		
	}
	
	public void calculateWordSpecific(){
		for (Word w : words) {
			w.setOrthogrophicFeature(WordOrthogrophic.checkOrthographicOfWord(w));
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
		this.feature = new LineSpecificFeature();
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
		this.feature = new LineSpecificFeature();
		this.label = label;
		this.words = this.toWord(content);
	}
	
	public ArrayList<Word> toWord(String lineContent){
		ArrayList<Word> wordTemp = new ArrayList<Word>();		
		String[] splitSpace = lineContent.split(" ");
		for (String string1 : splitSpace) {
			if(string1.trim() != null){
				Word w = new Word();
				w.setContent(string1.trim());
				wordTemp.add(w);
			}
		}
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
		return feature;
	}
	public void setFeature(LineSpecificFeature feature) {
		this.feature = feature;
	}
	
	
}
