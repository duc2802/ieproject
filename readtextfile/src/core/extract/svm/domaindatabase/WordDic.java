package core.extract.svm.domaindatabase;

public class WordDic {
	private String content;
	private int dfValue;
	
	public WordDic(String content){
		this.content = content;
		dfValue = 1;
	}
	
	public void increaseDFValue(){
		dfValue++;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDfValue() {
		return dfValue;
	}

	public void setDfValue(int dfValue) {
		this.dfValue = dfValue;
	}
	
}
