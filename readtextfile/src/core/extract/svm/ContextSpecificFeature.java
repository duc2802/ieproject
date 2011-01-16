package core.extract.svm;


public class ContextSpecificFeature {
	
	public static final int N = 5;
	public static final int L = 15;
	
	private float[][] previousLables;
	private float[][] nextLables;
	private int[] previousLineLable;
	private int[] nextLineLable;
	
	public ContextSpecificFeature(){
		if(previousLables == null) {
			previousLables = new float[N][L];
		}
		
		if(nextLables == null) {
			nextLables = new float[N][L];
		}
		
		if(previousLineLable == null){
			previousLineLable = new int[N];
		}
		if(nextLineLable == null) {
			nextLineLable = new int[L];
		}		
		for (int i = 0; i < N; i++) {
			previousLineLable[i] = 0;
			nextLineLable[i] = 0;
			for (int j = 0; j < L; j++) {
				previousLables[i][j] = 0;
				nextLables[i][j] = 0;
			}
		}
	}

	public void setPrevious(int index, int value){
		previousLineLable[index] = value;
		previousLables[index][value - 1] = (float) 0.5;
	}
	
	public void setNext(int index, int value){
		nextLineLable[index] = value;
		nextLables[index][value - 1] = (float) 0.5;
	}
	
	public float[][] getPreviousMetrix(){
		return previousLables;
	}
	
	public float[][] getNextMetrix(){
		return nextLables;
	}
	
	public int getNext(int index){
		return nextLineLable[index];
	}
	
	public int getPrevious(int index){
		return previousLineLable[index];
	}
}
