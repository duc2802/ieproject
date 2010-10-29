package core.extract.svm.cluster;

import java.util.ArrayList;
import core.extract.svm.Word;

public class WordOrthogrophic {
	
	/**
	 * 
	 * @param word
	 * @return
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public static ArrayList<Integer> checkOrthographicOfWord(Word word){		
		ArrayList<Integer> orthogrophic = new ArrayList<Integer>();
		char[] charOfWord = word.getContent().toCharArray();
		if(charOfWord != null){
			if(!Character.isLowerCase(charOfWord[0])){
				orthogrophic.add(OrthographicPropertiesConst.START_WITH_A_UPPER_CASE);			
			}
			boolean isDigit = true;
		    for (int i = 0; i < charOfWord.length; i++) {	    	
				if(charOfWord[i] == '@') {				
					//i = charOfWord.length;
					orthogrophic.add(OrthographicPropertiesConst.CONTAIN_AT);
				}
				if ((charOfWord[i] >= 'A' && charOfWord[i] <= 'Z') || (charOfWord[i] >= 'a' && charOfWord[i] <= 'z')) {
					isDigit = false;
				}
			}
		    if (isDigit == true) {
		    	orthogrophic.add(OrthographicPropertiesConst.IS_DIGIT);
			}
		}		
		return orthogrophic;
	}	
}
	
