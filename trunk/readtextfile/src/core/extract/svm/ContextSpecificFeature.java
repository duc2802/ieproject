package core.extract.svm;

import java.util.ArrayList;

public class ContextSpecificFeature {
	private ArrayList<Integer> previousLineLable;
	private ArrayList<Integer> nextLineLable;
	
	public ContextSpecificFeature(){
		if(previousLineLable != null){
			previousLineLable = new ArrayList<Integer>();
		}		
		if(nextLineLable != null) {
			nextLineLable = new ArrayList<Integer>();
		}		
	}
	
	public ArrayList<Integer> getPreviousLineLable() {
		return previousLineLable;
	}

	public void setPreviousLineLable(ArrayList<Integer> previousLineLable) {
		this.previousLineLable = previousLineLable;
	}

	public ArrayList<Integer> getNextLineLable() {
		return nextLineLable;
	}

	public void setNextLineLable(ArrayList<Integer> nextLineLable) {
		this.nextLineLable = nextLineLable;
	}
	
	
}
