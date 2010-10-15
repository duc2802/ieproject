package core.extract.svm;

public class Line {
	
	private String content;
	private int label; //1..15 : constrain in a other file.
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
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public Line(String content){
		this.content = content;
		this.feature = new LineSpecificFeature();
	}
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public Line(String content, int label){
		this.content = content;
		this.feature = new LineSpecificFeature();
		this.label = label;		
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
