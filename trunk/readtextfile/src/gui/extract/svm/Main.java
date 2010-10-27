package gui.extract.svm;

import java.util.ArrayList;

import core.extract.svm.Header;
import core.extract.svm.Word;

import core.extract.svm.domaindatabase.DatabaseDic;
import utilily.extract.svm.HeaderReader;
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
				
		String[] headersText = HeaderReader.read(pathFile);
		Header[] headers = new Header[headersText.length];
		
		for (int i = 0; i < headersText.length; i++) {
			headers[i] = new Header(headersText[i]);		
		}	
		
		DatabaseDic data = new DatabaseDic(headers);
		
		int i = 501;
		
		headers[i].getLineWithLabel(LabelConst.AUTHOR).get(0).calculateWordSpecific(data);
		System.out.println("Line : " + headers[i].getLineWithLabel(LabelConst.AUTHOR).get(0).getContent());
		System.out.println("      CLengthSence : " + headers[i].getLineWithLabel(LabelConst.AUTHOR).get(0).getFeature().getCSentenceLength());
		System.out.println("      CLinePosition : " + headers[i].getLineWithLabel(LabelConst.AUTHOR).get(0).getFeature().getCLinePosition());
		ArrayList<Word> words = headers[i].getLineWithLabel(LabelConst.AUTHOR).get(0).getWords();
		for (Word word : words) {
			System.out.println("=======================================");
			System.out.println("Content of : " + word.getContent());
			System.out.println("IsEmail : " + word.isEmail());
			System.out.println("IsSingleCap : " + word.isSingleCap());
			System.out.println("IsPostcode : " + word.isPostCode());
			System.out.println("IsAbstract : " + word.isAbtract());
			System.out.println("IsKeyword : " + word.isKeyword());
			System.out.println("IsIntro	: " + word.isIntro());
			System.out.println("IsAff : " + word.isAff());
			System.out.println("IsName : " + word.isName());
			System.out.println("IsDicWord : " + word.isDicWord());
			System.out.println("IsState : " + word.isState());
		}
		
	}
}
