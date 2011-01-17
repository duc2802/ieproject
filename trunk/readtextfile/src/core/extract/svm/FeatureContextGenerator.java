package core.extract.svm;

import java.io.File;
import java.util.ArrayList;

import utilily.extract.svm.HeaderReaderWriter;

public class FeatureContextGenerator {
	
	Header[] headers;
	int n = 5;
	
	public FeatureContextGenerator(Header[] headers){
		this.headers = headers;
	}
	
	public void run(){
		
	}
	
	public void calculateContextForTrain(){
		String pathContextTrainFile = "out//trainContext.txt";
		
		StringBuffer content = new StringBuffer();
		
		for (int i = 0; i < 600; i++) {
			System.out.println("Header : " + i);
			ArrayList<Line> lines = headers[i].getLine();
			ContextSpecificFeature contextSpecificFeature = new ContextSpecificFeature();
			for (Line line : lines) {
				contextSpecificFeature = calculateContextFeature(headers[i], line);
				line.setContextSpecificFeature(contextSpecificFeature);
				
				StringBuffer aLine = new StringBuffer();
				aLine.append(line.getLabel());
				aLine.append(" ");
				aLine.append("1:" + line.getFeature().getCSentenceLength());
				aLine.append(" ");
				aLine.append("2:" + line.getFeature().getCLinePosition());
				aLine.append(" ");
				aLine.append("3:" + line.getFeature().getCDictWordNumPer());
				aLine.append(" ");
				aLine.append("4:" + line.getFeature().getCNonDictWordNumPer());
				aLine.append(" ");
				aLine.append("5:" + line.getFeature().getCCap1DictWordNumPer());
				aLine.append(" ");
				aLine.append("6:" + line.getFeature().getCCap1NonDictWordNumPer());
				aLine.append(" ");
				aLine.append("7:" + line.getFeature().getCDigitNumPer());
				aLine.append(" ");
				aLine.append("8:" + line.getFeature().getCAffiNumPer());
				aLine.append(" ");
				aLine.append("9:" + line.getFeature().getCAddNumPer());
				aLine.append(" ");
				aLine.append("10:" + line.getFeature().getCDateNumPer());
				aLine.append(" ");
				aLine.append("11:" + line.getFeature().getCDegreeNumPer());
				aLine.append(" ");
				aLine.append("12:" + line.getFeature().getCPubNumPer());
				aLine.append(" ");
				aLine.append("13:" + line.getFeature().getCNoteNumPer());
				aLine.append(" ");
				aLine.append("14:" + line.getFeature().getCPhoneNumPer());
				
				int temp = 15;
				float[][] previousMetrix = line.getContextSpecificFeature().getPreviousMetrix();
				for (int j = 0; j < ContextSpecificFeature.N; j++) {
					for (int t = 0; t < ContextSpecificFeature.L; t++) {
						aLine.append(" ");
						aLine.append(temp + ":" + previousMetrix[j][t]);
						temp++;
					}					
				}
				
				//temp = 15;
				float[][] nextMetrix = line.getContextSpecificFeature().getNextMetrix();
				for (int j = 0; j < ContextSpecificFeature.N; j++) {
					for (int t = 0; t < ContextSpecificFeature.L; t++) {
						aLine.append(" ");
						aLine.append(temp + ":" + nextMetrix[j][t]);
						temp++;
					}					
				}
				
				aLine.append("\n");
				content.append(aLine.toString());						
			}
		}
		
		File trainFile = new File(pathContextTrainFile);	
		
		try {
			HeaderReaderWriter.write(trainFile, content.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error in write context train file process");
		}
	}
	
	public ContextSpecificFeature calculateContextFeature(Header header, Line line){
		ContextSpecificFeature contextSpecificFeature = new ContextSpecificFeature();	
		int totalLineInHeader = header.getLine().size();
		int positionOfLine = (int) line.getFeature().getCLinePosition();
		for (int i = 0; i < 5; i++) {
			if((i + positionOfLine - n) > 0) {				
				Line lineTemp = header.getLineWithPosition(i + positionOfLine - n);
				if(lineTemp != null) {
					System.out.println(n - 1 - i + " : " + lineTemp.getFeature().getCLinePosition());
					contextSpecificFeature.setPrevious(n - 1 - i, lineTemp.getLabel());
				}				
			}
			
			if((positionOfLine + n - i) <= totalLineInHeader) {
				Line lineTemp = header.getLineWithPosition(positionOfLine + n - i);
				if(lineTemp != null) {
					System.out.println(n - 1 - i + " : " + lineTemp.getFeature().getCLinePosition());
					contextSpecificFeature.setNext(n - 1 - i, lineTemp.getLabel());
				}				
			}
		}			
		return contextSpecificFeature;
	}
}
