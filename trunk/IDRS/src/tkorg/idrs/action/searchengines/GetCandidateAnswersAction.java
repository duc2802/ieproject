
package tkorg.idrs.action.searchengines;

import java.util.ArrayList;
import tkorg.idrs.core.searchengines.Token;
import tkorg.idrs.core.searchengines.TextChunks;
import tkorg.idrs.core.searchengines.CandidateAnswer;

/**
 * @author tiendv
 *
 */
public class GetCandidateAnswersAction {
	Token tempTK = new Token();
	TextChunks tempTC = new TextChunks();
	CandidateAnswer tempCd = new CandidateAnswer();
	public ArrayList<Token> getKeywordsFromUser(String queryString) {
		 ArrayList<Token> keywords = new ArrayList<Token>();
			if (queryString != null) {
				queryString = queryString.trim();
				String[] words = queryString.split(" ");
				for (int i = 0; i < words.length; i++) {
					keywords.add(new Token(words[i]));
				}
				keywords = tempTK.removeStopwordsFromArrayListToken(keywords);
			}
		return keywords;	
	}
	
	public ArrayList<TextChunks> getArrayListTexchunkFromSearchResult(ArrayList<Token> keywords, ArrayList<String> searchengineresult){
		ArrayList<TextChunks> result = new ArrayList<TextChunks>();
		TextChunks temp = new TextChunks();
		for(int i = 0; i< searchengineresult.size();i++) {
			temp = getTextchunksHaveMaxweightForASearchResult(keywords,searchengineresult.get(i) );
			result.add(temp);
		}
		return result;
	}
	
	public TextChunks getTextchunksHaveMaxweightForASearchResult (ArrayList<Token> keywords, String searchengineresult){
		TextChunks result = new TextChunks();
		ArrayList<Token> searchengineresultTK = new ArrayList<Token>();
		String[] temp = searchengineresult.split("<br>");
		String resultURL = temp[0];
		String resultPageContentFromURL=null;
		Thread getPageContentThead = new GetPageFromURLAction();
		((GetPageFromURLAction)getPageContentThead).setTargerURL(resultURL);
		try {
			getPageContentThead.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultPageContentFromURL = ((GetPageFromURLAction)getPageContentThead).getStringPageContent();
		getPageContentThead.stop();
		searchengineresultTK = getKeywordsFromUser(resultPageContentFromURL);
		ArrayList<TextChunks> arSubTextchunks = new ArrayList<TextChunks>();
		arSubTextchunks= tempTC.getsubTextChunksFromKeywords(keywords, searchengineresultTK);
		if(arSubTextchunks.isEmpty()==false) {
		ArrayList<TextChunks> arfinalTextchunks = new ArrayList<TextChunks>();
		arfinalTextchunks = tempTC.finalTextChunks(arSubTextchunks, searchengineresultTK);	
		result = tempTC.getTexChunkhaveMaxWeightInArraylistTextChunk(arfinalTextchunks, keywords);
		}
		return result;
	}
	public ArrayList<CandidateAnswer> getCandidateAnswer(ArrayList<TextChunks> arFinalTextChunks) {
		ArrayList<CandidateAnswer> result = new ArrayList<CandidateAnswer>();
		for(int i = 0; i<arFinalTextChunks.size(); i++ ) {
			CandidateAnswer temp = new CandidateAnswer();
			temp= tempCd.getCandidateAnswer(arFinalTextChunks.get(i).arrt);
			result.add(temp);
		}
		tempCd.updateAtributeNumberofDocCandidateAnswer(result);
		return result; 
	}
		
}
