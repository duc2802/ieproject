package core.extract.svm;

public class LineSpecificFeature {
	
	private double cSentenceLength;
	private double cLinePosition;
	
	private double cDictWordNumPer;
	private double cNonDictWordNumPer;
	private double cCap1DictWordNumPer;
	private double cCap1NonDictWordNumPer;
	
	private double cDigitNumPer;
	private double cAffiNumPer;
	private double cAddNumPer;
	private double cDateNumPer;
	private double cDegreeNumPer;
	private double cPhoneNumPer;
	private double cPubNumPer;
	private double cNoteNumPer;
	private double cPageNumPer;
	
	/**
	 * 
	 * @Author : Huynh Minh Duc
	 * @Comment : 
	 */
	public LineSpecificFeature(){
		
	}
	
	public double maxValue(){
		double max = this.cSentenceLength;
		if(max < this.cAddNumPer) max = this.cAddNumPer;
		if(max < this.cAffiNumPer) max = this.cAffiNumPer;
		if(max < this.cCap1DictWordNumPer) max = this.cCap1DictWordNumPer;
		if(max < this.cCap1NonDictWordNumPer) max = this.cCap1NonDictWordNumPer;
		if(max < this.cDateNumPer) max = this.cDateNumPer;
		if(max < this.cDegreeNumPer) max = this.cDegreeNumPer;
		if(max < this.cDictWordNumPer) max = this.cDictWordNumPer;
		if(max < this.cDigitNumPer) max = this.cDigitNumPer;
		if(max < this.cLinePosition) max = this.cLinePosition;
		if(max < this.cNonDictWordNumPer) max = this.cNonDictWordNumPer;
		if(max < this.cNoteNumPer) max = this.cNoteNumPer;
		if(max < this.cPageNumPer) max = this.cPageNumPer;
		if(max < this.cPhoneNumPer) max = this.cPhoneNumPer;
		if(max < this.cPubNumPer) max = this.cPubNumPer;
		return max;
	}
	
	public double getCAffiNumPer() {
		return cAffiNumPer;
	}


	public void setCAffiNumPer(double affiNumPer) {
		cAffiNumPer = affiNumPer;
	}


	public double getCAddNumPer() {
		return cAddNumPer;
	}


	public void setCAddNumPer(double addNumPer) {
		cAddNumPer = addNumPer;
	}


	public double getCDateNumPer() {
		return cDateNumPer;
	}


	public void setCDateNumPer(double dateNumPer) {
		cDateNumPer = dateNumPer;
	}


	public double getCDegreeNumPer() {
		return cDegreeNumPer;
	}


	public void setCDegreeNumPer(double degreeNumPer) {
		cDegreeNumPer = degreeNumPer;
	}


	public double getCPhoneNumPer() {
		return cPhoneNumPer;
	}


	public void setCPhoneNumPer(double phoneNumPer) {
		cPhoneNumPer = phoneNumPer;
	}


	public double getCPubNumPer() {
		return cPubNumPer;
	}


	public void setCPubNumPer(double pubNumPer) {
		cPubNumPer = pubNumPer;
	}


	public double getCNoteNumPer() {
		return cNoteNumPer;
	}


	public void setCNoteNumPer(double noteNumPer) {
		cNoteNumPer = noteNumPer;
	}


	public double getCPageNumPer() {
		return cPageNumPer;
	}


	public void setCPageNumPer(double pageNumPer) {
		cPageNumPer = pageNumPer;
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
