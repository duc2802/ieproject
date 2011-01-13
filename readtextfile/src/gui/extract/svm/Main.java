package gui.extract.svm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import core.extract.svm.ContextSpecificFeature;
import core.extract.svm.FeatureContextGenerator;
import core.extract.svm.FeatureIndependentGenerator;
import core.extract.svm.Header;
import core.extract.svm.Line;
import core.extract.svm.SVMScale;
import core.extract.svm.SVMTest;
import core.extract.svm.SVMTrain;
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
		
		Header[] headers;
		
		// Calculate feature independent. 
		
		FeatureIndependentGenerator featureIndependentGenerator = new FeatureIndependentGenerator();
		try {
			headers = featureIndependentGenerator.run();
			
			for (int i = 0; i < headers[0].getLine().size(); i++) {
				System.out.println(headers[0].getLine().get(i).getContent() + " : " + headers[0].getLine().get(i).getFeature().getCLinePosition());
			}
						
			FeatureContextGenerator featureContextGenerator = new FeatureContextGenerator(headers);
			System.out.println(headers[0].getLine().get(6).getContent());
			ContextSpecificFeature contextSpecificFeature = featureContextGenerator.calculateContextFeature(headers[0], headers[0].getLine().get(6));
			
			for (int i = 0; i < 5; i++) {
				System.out.println("next : " + contextSpecificFeature.getNext(i));
				System.out.println("previous : " + contextSpecificFeature.getPrevious(i));
			}			
			System.out.println();
			System.gc();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error calculate independent vector");
		}		
		
		//Scale value.
				
		try {
			SVMScale s = new SVMScale();
			String command = "-l 0 -u 1 -s range out//test.txt";
			s.run(command);
			System.gc();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error scale value");
		}		
		
		// Training SVM.		
		try {
			SVMTrain t = new SVMTrain();
			String inputFileName = "out//train.scale.txt";
			String modelFileName = "out//train.model";
			t.run(inputFileName, modelFileName);
			System.gc();			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error trainning SVM");
		}
		
		// Test SVM
		
		try {
			SVMTest testSVM = new SVMTest();
			String pathTestScaleFile = "out//test.scale.txt";
			String modelFileName = "out//train.model";
			String resultFileName = "out//result.txt";
			testSVM.run(pathTestScaleFile, modelFileName, resultFileName);
			System.gc();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error test SVM");
		}
		
		// Recalculate feature (add context feature)				
	}
}
