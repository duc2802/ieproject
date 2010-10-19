package core.extract.svm;

public class Word {
	private String content;
	private WordSpecificFeature wordSpecificFeature;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WordSpecificFeature getWordSpecificFeature() {
		return wordSpecificFeature;
	}

	public void setWordSpecificFeature(WordSpecificFeature wordSpecificFeature) {
		this.wordSpecificFeature = wordSpecificFeature;
	}
	
	
	
}
