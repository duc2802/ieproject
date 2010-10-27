package core.extract.svm;

import java.util.ArrayList;

import core.extract.svm.cluster.DomainDatabase;
import core.extract.svm.domaindatabase.DatabaseDic;

public class Word {
	
	private String content;
	private int finalWordSpecificFeature;	
	private ArrayList<Integer> orthogrophicFeature;
	
	private boolean isEmail;
	private boolean isURL;
	private boolean isSingleCap;
	private boolean isPostCode;
	private boolean isAbstract;
	private boolean isKeyword;
	private boolean isIntro;
	private boolean isPhone;
	private boolean isMonth;
	private boolean isPrep;
	private boolean isDegree;
	private boolean isPubNum;
	private boolean isNoteNum;
	private boolean isAff;
	private boolean isAddr;
	private boolean isCity;
	private boolean isState;
	private boolean isCountry;
	private boolean isName;
	private boolean isCap1DicWord;
	private boolean isDicWord;
	private boolean isNonDicWord;
	private boolean isDig;
	
	public Word(){
		isEmail = false;
		isURL = false;
		isSingleCap = false;
		isPostCode = false;
		isAbstract = false;
		isKeyword = false;
		isIntro = false;
		isPhone = false;
		isMonth = false;
		isPrep = false;
		isDegree = false;
		isPubNum = false;
		isNoteNum = false;
		isAff = false;
		isAddr = false;
		isCity = false;
		isState = false;
		isCountry = false;
		isName = false;
		isCap1DicWord = false;
		isDicWord = false;
		isNonDicWord = false;
		isDig = false;
	}
	
	public void calculateWordSpecificFeature(DatabaseDic databaseDic){
		ArrayList<Integer> result = databaseDic.search(this.content.toLowerCase());
		if(result.size() == 0){
			this.setNonDicWord(true);
		}
		else {
			for (Integer integer : result) {
				this.setDicWord(true);
				switch (integer) {
				case 1:
					this.setPostCode(true);
					break;
				case 2:
					this.setAbstract(true);
					break;
				case 3:
					this.setKeyword(true);
					break;
				case 4:
					this.setPhone(true);
					break;
				case 5:
					this.setMonth(true);
					break;
				case 6:
					this.setAddr(true);
					break;
				case 7:
					this.setCity(true);
					break;
				case 8:
					this.setState(true);
					break;
				case 9:
					this.setCountry(true);
					break;
				case 10:
					this.setName(true);
					break;
				case 11:
					this.setAff(true);
					break;
				case 12:
					this.setNoteNum(true);
					break;
				default:
					break;
				}
			}			
		}
	}	
	
	
	
	public ArrayList<Integer> getOrthogrophicFeature() {
		return orthogrophicFeature;
	}

	public void setOrthogrophicFeature(ArrayList<Integer> orthogrophicFeature) {
		this.orthogrophicFeature = orthogrophicFeature;
	}
	
	public void considerPriorityWordSpecificFeature(){
		
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFinalWordSpecificFeature() {
		return finalWordSpecificFeature;
	}

	public void setFinalWordSpecificFeature(int wordSpecificFeature) {
		this.finalWordSpecificFeature = wordSpecificFeature;
	}

	public boolean isEmail() {
		return isEmail;
	}

	public void setEmail(boolean isEmail) {
		this.isEmail = isEmail;
	}

	public boolean isURL() {
		return isURL;
	}

	public void setURL(boolean isURL) {
		this.isURL = isURL;
	}

	public boolean isSingleCap() {
		return isSingleCap;
	}

	public void setSingleCap(boolean isSingleCap) {
		this.isSingleCap = isSingleCap;
	}

	public boolean isPostCode() {
		return isPostCode;
	}

	public void setPostCode(boolean isPostCode) {
		this.isPostCode = isPostCode;
	}

	public boolean isAbtract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public boolean isKeyword() {
		return isKeyword;
	}

	public void setKeyword(boolean isKeyword) {
		this.isKeyword = isKeyword;
	}

	public boolean isIntro() {
		return isIntro;
	}

	public void setIntro(boolean isIntro) {
		this.isIntro = isIntro;
	}

	public boolean isPhone() {
		return isPhone;
	}

	public void setPhone(boolean isPhone) {
		this.isPhone = isPhone;
	}

	public boolean isMonth() {
		return isMonth;
	}

	public void setMonth(boolean isMonth) {
		this.isMonth = isMonth;
	}

	public boolean isPrep() {
		return isPrep;
	}

	public void setPrep(boolean isPrep) {
		this.isPrep = isPrep;
	}

	public boolean isDegree() {
		return isDegree;
	}

	public void setDegree(boolean isDegree) {
		this.isDegree = isDegree;
	}

	public boolean isPubNum() {
		return isPubNum;
	}

	public void setPubNum(boolean isPubNum) {
		this.isPubNum = isPubNum;
	}

	public boolean isNoteNum() {
		return isNoteNum;
	}

	public void setNoteNum(boolean isNoteNum) {
		this.isNoteNum = isNoteNum;
	}

	public boolean isAff() {
		return isAff;
	}

	public void setAff(boolean isAff) {
		this.isAff = isAff;
	}

	public boolean isAddr() {
		return isAddr;
	}

	public void setAddr(boolean isAddr) {
		this.isAddr = isAddr;
	}

	public boolean isCity() {
		return isCity;
	}

	public void setCity(boolean isCity) {
		this.isCity = isCity;
	}

	public boolean isState() {
		return isState;
	}

	public void setState(boolean isState) {
		this.isState = isState;
	}

	public boolean isCountry() {
		return isCountry;
	}

	public void setCountry(boolean isCountry) {
		this.isCountry = isCountry;
	}

	public boolean isName() {
		return isName;
	}

	public void setName(boolean isName) {
		this.isName = isName;
	}

	public boolean isCap1DicWord() {
		return isCap1DicWord;
	}

	public void setCap1DicWord(boolean isCap1DicWord) {
		this.isCap1DicWord = isCap1DicWord;
	}

	public boolean isDicWord() {
		return isDicWord;
	}

	public void setDicWord(boolean isDicWord) {
		this.isDicWord = isDicWord;
	}

	public boolean isNonDicWord() {
		return isNonDicWord;
	}

	public void setNonDicWord(boolean isNonDicWord) {
		this.isNonDicWord = isNonDicWord;
	}

	public boolean isDig() {
		return isDig;
	}

	public void setDig(boolean isDig) {
		this.isDig = isDig;
	}
	
	
}
