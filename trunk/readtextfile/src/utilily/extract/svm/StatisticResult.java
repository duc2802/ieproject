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
	
	public void returnResultBy (int label) {
		int totalLineByLabel = 0;
		int totalCorrectLineByLabel = 0;
		
		ArrayList<LabelMap> lableMapArrayList = this.getList();
		for (LabelMap lm : lableMapArrayList) {		
			if(lm.getHand() == label){
				totalLineByLabel++;
				if(lm.getMachine() == label){
					totalCorrectLineByLabel++;
				}
			}
		}
		
		String labelString;
		switch (label) {
		case 1:
			labelString = "Title";
			break;
		case 2:
			labelString = "Author";
			break;
		case 3:
			labelString = "Affiliation";
			break;
		case 4:
			labelString = "Address";
			break;
		case 5:
			labelString = "Note";
			break;
		case 6:
			labelString = "Email";
			break;
		case 7:
			labelString = "Date";
			break;
		case 8:
			labelString = "Abtract";
			break;
		case 9:
			labelString = "Introduction";
			break;
		case 10:
			labelString = "Phone";
			break;
		case 11:
			labelString = "Keyword";
			break;
		case 12:
			labelString = "Web";
			break;
		case 13:
			labelString = "Degree";
			break;
		case 14:
			labelString = "Pubnum";
			break;
		case 15:
			labelString = "Page";
			break;	
		default:
			labelString = null;
			break;
		}
		
		System.out.println("Summer "+ labelString +" line predict correct : " + totalCorrectLineByLabel + "/" + totalLineByLabel + " (" + (double)totalCorrectLineByLabel / totalLineByLabel * 100 + "%)");
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
		
		sr.returnResultBy(LabelConst.ABSTRACT);
		sr.returnResultBy(LabelConst.ADDRESS);
		sr.returnResultBy(LabelConst.AFFILIATION);
		sr.returnResultBy(LabelConst.AUTHOR);
		sr.returnResultBy(LabelConst.DATE);
		sr.returnResultBy(LabelConst.DEGREE);
		sr.returnResultBy(LabelConst.EMAIL);
		sr.returnResultBy(LabelConst.INTRODUCTION);
		sr.returnResultBy(LabelConst.KEYWORD);
		sr.returnResultBy(LabelConst.NOTE);
		sr.returnResultBy(LabelConst.PAGE);
		sr.returnResultBy(LabelConst.PHONE);
		sr.returnResultBy(LabelConst.PUBNUM);
		sr.returnResultBy(LabelConst.TITLE);
		sr.returnResultBy(LabelConst.WEB);
	}

}
