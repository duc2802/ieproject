package gui.extract.svm;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import core.extract.svm.Header;
import core.extract.svm.Line;
import core.extract.svm.Word;

import core.extract.svm.domaindatabase.DatabaseDic;
import utilily.extract.svm.HeaderReaderWriter;
import utilily.extract.svm.LabelConst;


public class Main {
	
	/**
	 * @param args
	 */
	public static final String separateLine = "\n";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String pathFile = "text.txt";
		String pathFile = "tagged_headers.txt";
				
		String[] headersText = HeaderReaderWriter.read(pathFile);
		Header[] headers = new Header[headersText.length];
		
		for (int i = 0; i < headersText.length; i++) {
			headers[i] = new Header(headersText[i]);		
		}	
		
		DatabaseDic data = new DatabaseDic(headers);
		
		StringBuffer trainContent = new StringBuffer();
		
		for(int i = 0; i < 500; i++){
			ArrayList<Line> line = headers[i].getLine();
			for(int j = 0; j < line.size(); j++){
				line.get(j).calculateWordSpecific(data);
				line.get(j).genarateVectorFeature();
				line.get(j).normalizationVector();
				StringBuffer aLine = new StringBuffer();
				aLine.append(line.get(j).getLabel());
				aLine.append(" ");
				aLine.append("1:" + line.get(j).getFeature().getCSentenceLength());
				aLine.append(" ");
				aLine.append("2:" + line.get(j).getFeature().getCLinePosition());
				aLine.append(" ");
				aLine.append("3:" + line.get(j).getFeature().getCDictWordNumPer());
				aLine.append(" ");
				aLine.append("4:" + line.get(j).getFeature().getCNonDictWordNumPer());
				aLine.append(" ");
				aLine.append("5:" + line.get(j).getFeature().getCCap1DictWordNumPer());
				aLine.append(" ");
				aLine.append("6:" + line.get(j).getFeature().getCCap1NonDictWordNumPer());
				aLine.append(" ");
				aLine.append("7:" + line.get(j).getFeature().getCDigitNumPer());
				aLine.append(" ");
				aLine.append("8:" + line.get(j).getFeature().getCAffiNumPer());
				aLine.append(" ");
				aLine.append("9:" + line.get(j).getFeature().getCAddNumPer());
				aLine.append(" ");
				aLine.append("10:" + line.get(j).getFeature().getCDateNumPer());
				aLine.append(" ");
				aLine.append("11:" + line.get(j).getFeature().getCDegreeNumPer());
				aLine.append(" ");
				aLine.append("12:" + line.get(j).getFeature().getCPubNumPer());
				aLine.append(" ");
				aLine.append("13:" + line.get(j).getFeature().getCNoteNumPer());
				aLine.append(" ");
				aLine.append("14:" + line.get(j).getFeature().getCPhoneNumPer());				
				aLine.append("\n");
				System.out.println(aLine.toString());
				trainContent.append(aLine.toString());				
			}
		}		
		File trainFile = new File("out//train.my_scale.txt");		
		try {
			HeaderReaderWriter.write(trainFile, trainContent.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error in write train file process");
		}
	}
}
