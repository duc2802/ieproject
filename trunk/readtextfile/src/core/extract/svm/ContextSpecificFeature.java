package core.extract.svm;


public class ContextSpecificFeature {
	private int[] previousLineLable;
	private int[] nextLineLable;
	
	public ContextSpecificFeature(){
		if(previousLineLable == null){
			previousLineLable = new int[5];
		}
		if(nextLineLable == null) {
			nextLineLable = new int[5];
		}		
		for (int i = 0; i < 5; i++) {
			previousLineLable[i] = 0;
			nextLineLable[i] = 0;
		}
	}

	public void setPrevious(int index, int value){
		previousLineLable[index] = value;
	}
	
	public void setNext(int index, int value){
		nextLineLable[index] = value;
	}
	
	public int getNext(int index){
		return nextLineLable[index];
	}
	
	public int getPrevious(int index){
		return previousLineLable[index];
	}
}
