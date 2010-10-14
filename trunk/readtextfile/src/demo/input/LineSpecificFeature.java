package demo.input;

public class LineSpecificFeature {
	
	private double cSentenceLength;
	private double cLinePosition;
	private double cDictWordNumPer;
	private double cNonDictWordNumPer;
	private double cCap1DictWordNumPer;
	private double cCap1NonDictWordNumPer;
	private double cDigitNumPer;
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment : 
	 */
	public LineSpecificFeature(){
		
	}
	
	public double getCSentenceLength() {
		return cSentenceLength;
	}
	public void setCSentenceLength(double sentenceLength) {
		cSentenceLength = sentenceLength;
	}
	public double getCLinePosition() {
		return cLinePosition;
	}
	public void setCLinePosition(double linePosition) {
		cLinePosition = linePosition;
	}
	public double getCDictWordNumPer() {
		return cDictWordNumPer;
	}
	public void setCDictWordNumPer(double dictWordNumPer) {
		cDictWordNumPer = dictWordNumPer;
	}
	public double getCNonDictWordNumPer() {
		return cNonDictWordNumPer;
	}
	public void setCNonDictWordNumPer(double nonDictWordNumPer) {
		cNonDictWordNumPer = nonDictWordNumPer;
	}
	public double getCCap1DictWordNumPer() {
		return cCap1DictWordNumPer;
	}
	public void setCCap1DictWordNumPer(double cap1DictWordNumPer) {
		cCap1DictWordNumPer = cap1DictWordNumPer;
	}
	public double getCCap1NonDictWordNumPer() {
		return cCap1NonDictWordNumPer;
	}
	public void setCCap1NonDictWordNumPer(double cap1NonDictWordNumPer) {
		cCap1NonDictWordNumPer = cap1NonDictWordNumPer;
	}
	public double getCDigitNumPer() {
		return cDigitNumPer;
	}
	public void setCDigitNumPer(double digitNumPer) {
		cDigitNumPer = digitNumPer;
	}
}
