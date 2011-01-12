package core.extract.svm;

public class FeatureContextGenerator {
	
	Header[] headers;
	int n = 5;
	
	public FeatureContextGenerator(Header[] headers){
		this.headers = headers;
	}
	
	public void run(){
		
	}
	
	public void ForTrain(){
		
	}
	
	public ContextSpecificFeature calculateContextFeature(Header header, Line line){
		ContextSpecificFeature contextSpecificFeature = new ContextSpecificFeature();
		
		int totalLineInHeader = header.getLine().size();
		int positionOfLine = (int) line.getFeature().getCLinePosition();
		for (int i = 0; i < 5; i++) {
			if((i + positionOfLine - n) >= 0) {
				contextSpecificFeature.getPreviousLineLable().set(n - 1 - i, header.get(i + positionOfLine - n).getLabel());
			}else {
				contextSpecificFeature.getPreviousLineLable().set(n - 1 - i, -1);
			}
			
			if((positionOfLine + n - i) <= totalLineInHeader) {
				contextSpecificFeature.getNextLineLable().set(n - 1 - i, header.get(positionOfLine + n - i).getLabel());
			}else {
				contextSpecificFeature.getNextLineLable().set(n - 1 - i, -1);
			}
		}
		
		return contextSpecificFeature;
	}
}
