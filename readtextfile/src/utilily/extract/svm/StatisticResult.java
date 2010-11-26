package utilily.extract.svm;

import java.util.ArrayList;

public class StatisticResult {

	ArrayList<LabelMap> list;
	
	public StatisticResult(){
		list = new ArrayList<LabelMap>();
	}
	
	public void buildLableMap(String resultFile, String testFile) {
		ArrayList<String> listResultLine = new ArrayList<String>();
		listResultLine = HeaderReaderWriter.readTextFile(resultFile);
		
		ArrayList<String> listTestLine = new ArrayList<String>();
		listTestLine = HeaderReaderWriter.readTextFile(testFile);
		
		for (int i = 0; i < listResultLine.size(); i++) {
			LabelMap lm = new LabelMap();
			lm.setMachine((int)Double.parseDouble(listResultLine.get(i)));
			
			String numberFirstLine = listTestLine.get(i).substring(0, 1);		
			lm.setHand(Integer.parseInt(numberFirstLine));
			list.add(lm);
		}
	}
	
	public ArrayList<LabelMap> getList() {
		return list;
	}

	public void setList(ArrayList<LabelMap> list) {
		this.list = list;
	}

	/**
	 * @param args
	 * @Author : Huynh Minh Duc
	 * @Comment : 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String pathTestScaleFile = "out//test.scale.txt";		
		String resultFileName = "out//result.txt";
		
		StatisticResult sr = new StatisticResult();
		sr.buildLableMap(resultFileName, pathTestScaleFile);
		
		int totalCorrectLine = 0;		
		int totalTitleLine = 0;
		int totalCorrectTitleLine = 0;
		
		ArrayList<LabelMap> lableMapArrayList = sr.getList();
		int lengthLine = lableMapArrayList.size();
		for (LabelMap lm : lableMapArrayList) {
			if(lm.getMachine() == lm.getHand()){
				totalCorrectLine++;
			}
			if(lm.getHand() == 1){
				totalTitleLine++;
				if(lm.getMachine() == 1){
					totalCorrectTitleLine++;
				}
			}
		}
		
		System.out.println("Summer line predict correct : " + totalCorrectLine + "/" + lengthLine + " (" + (double) totalCorrectLine / lengthLine * 100 + "%)");
		System.out.println("Summer TITLE line predict correct : " + totalCorrectTitleLine + "/" + totalTitleLine + " (" + (double)totalCorrectTitleLine / totalTitleLine * 100 + "%)");
		
	}

}
