package core.extract.svm;

import java.util.ArrayList;

import core.extract.svm.cluster.DomainDatabase;

public class Word {
	
	private String content;
	private int wordSpecificFeature;	
	private ArrayList<Integer> orthogrophicFeature;
	private DomainDatabase domainDatabaseFeature;
	
	public Word(){
		
	}
	
	public ArrayList<Integer> getOrthogrophicFeature() {
		return orthogrophicFeature;
	}

	public void setOrthogrophicFeature(ArrayList<Integer> orthogrophicFeature) {
		this.orthogrophicFeature = orthogrophicFeature;
	}

	public DomainDatabase getDomainDatabaseFeature() {
		return domainDatabaseFeature;
	}

	public void setDomainDatabaseFeature(DomainDatabase domainDatabaseFeature) {
		this.domainDatabaseFeature = domainDatabaseFeature;
	}
	
	public void considerPriorityWordSpecificFeature(){
		
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWordSpecificFeature() {
		return wordSpecificFeature;
	}

	public void setWordSpecificFeature(int wordSpecificFeature) {
		this.wordSpecificFeature = wordSpecificFeature;
	}
	
}
