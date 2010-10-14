/**
 * 
 */
package tkorg.idrs.core.searchengines;
import java.util.ArrayList;
import java.util.Hashtable;

import tkorg.idrs.core.searchengines.TextChunks;

/**
 * @author tiendv
 * 
 */
public class CandidateAnswer extends Token {
	public ArrayList<Token> candidate = new ArrayList<Token>();

	public CandidateAnswer() {
		// TODO Auto-generated constructor stub
	}

	public CandidateAnswer getCandidateAnswer(ArrayList<Token> topCandidateAnswersFromDocument) {
		CandidateAnswer result = new CandidateAnswer();
		for (int i = 0; i < topCandidateAnswersFromDocument.size(); i++)
			result.candidate.add(topCandidateAnswersFromDocument.get(i));
		return result;
	}

	public static  double averageRank(Token candidate,ArrayList<CandidateAnswer> arrayCandidate) {
		double result = candidate.getTotalAveageRank();
		for (int i = 0; i < arrayCandidate.size(); i++) {
			if (arrayCandidate.get(i).candidate.contains(candidate) == false) {
				Token temp = getTokenFromTextChunk(candidate, arrayCandidate.get(i));
				result += temp.getFrequency();
			} else
				result += 11;
		}
		result = (float) result / arrayCandidate.size();
		return result;
	}

	public static  int numberofDocumentContain(Token candidate,ArrayList<CandidateAnswer> arrayCandidate) {
		int result = candidate.getNumberofDocuments();
		for (int i = 0; i < arrayCandidate.size(); i++){
			for(int j =0 ; j<arrayCandidate.get(i).candidate.size();j++)
			if (candidate.compareTwoToken(arrayCandidate.get(i).candidate.get(j))== true){
				result++;
			}
		}
		return result;
	}

	public static  int totalFrequencyInDocumentOfCandidate(Token candidate,
			ArrayList<CandidateAnswer> arrayCandidate) {
		int result = candidate.getTotalFreQuency();
		for (int i = 0; i < arrayCandidate.size(); i++) {
			if (arrayCandidate.get(i).candidate.contains(candidate) == false) {
				Token temp = getTokenFromTextChunk(candidate, arrayCandidate.get(i));
				result += temp.getFrequency();
			}
		}
		return result;
	}

	public static  double totalNormalizedFrequencyOfCandidate(Token candidate,
			ArrayList<CandidateAnswer> arrayCandidate) {
		double result = candidate.getTotalNormalizedFrequency();
		for (int i = 0; i < arrayCandidate.size(); i++) {
			if (arrayCandidate.get(i).candidate.contains(candidate) == false) {
				Token temp = getTokenFromTextChunk(candidate, arrayCandidate.get(i));
				result += temp.getNormalizedFrequency();
			}
		}
		return result;
	}

	public static  double normalizedFrequencyWeightOfCandidate(Token candidate,
			ArrayList<CandidateAnswer> arrayCandidate) {
		double result = candidate.getNormalizedFrequencyWeight();
		for (int i = 0; i < arrayCandidate.size(); i++) {
			if (arrayCandidate.get(i).candidate.contains(candidate) == false) {
				Token temp = getTokenFromTextChunk(candidate, arrayCandidate.get(i));
				double nfw = temp.getNormalizedFrequency()* temp.getNumberofDocuments();
				result += nfw;
			}
		}
		return result;
	}

	public static Token getTokenFromTextChunk(Token tk, CandidateAnswer ca ){
		Token result = new Token();
		for (int i = 0; i < ca.candidate.size();i++) {
			if (tk.compareTwoToken(ca.candidate.get(i))== true){
				result.setFrequency( ca.candidate.get(i).getFrequency());
				result.setNormalizedWTF(ca.candidate.get(i).getNormalizedWTF());
				result.setWeightedTF(ca.candidate.get(i).getWeightedTF());
				result.setRank(ca.candidate.get(i).getRank());
				result.setNormalizedFrequency(ca.candidate.get(i).getNormalizedFrequency());
				break;
			}
		}
		return result;
	}
	/*public static void updateAtributeCandidateAnswer (ArrayList<CandidateAnswer> acd) {
		Hashtable<String ,Integer > visited = new Hashtable<String, Integer>();
		for (int i = 0 ; i< acd.size() ; i++ ) {
			 for (Token token : acd.get(i).candidate){
				 System.out.println();
				double averageRanks = averageRank(token,acd);
				int numberofDocumentContain = numberofDocumentContain(token,acd);
				int totalFrequencyInDocumentOfCandidate= totalFrequencyInDocumentOfCandidate(token,acd);
				double totalNormalizedFrequencyOfCandidate = totalNormalizedFrequencyOfCandidate(token,acd);
				double normalizedFrequencyWeightOfCandidate = normalizedFrequencyWeightOfCandidate(token,acd);
				System.out.println("av"+averageRanks+"number"+numberofDocumentContain+"total"+totalFrequencyInDocumentOfCandidate);
			  //  if (visited.containsKey(token.s)) {
			    	token.setTotalAveageRank(averageRanks);
			    	token.setToltalfrequency(totalFrequencyInDocumentOfCandidate);
			    	token.setNumberofDocuments(numberofDocumentContain);
			    	token.setNormalizedFrequency(totalNormalizedFrequencyOfCandidate);
			    	token.setNormalizedFrequencyWeight(normalizedFrequencyWeightOfCandidate);
			    //} 
			 }
		}
	}*/
	public static void updateAtributeNumberofDocCandidateAnswer (ArrayList<CandidateAnswer> acd) {
		Hashtable<String ,Integer > visited = new Hashtable<String, Integer>();
		for (int i = 0 ; i< acd.size() ; i++ ) {
			for(int j=0;j<acd.get(i).candidate.size();j++)
					    if (visited.containsKey(acd.get(i).candidate.get(j).s)) {
					    	acd.get(i).candidate.get(j).setNumberofDocuments(visited.get(acd.get(i).candidate.get(j).s));
					    } else {
					    	int numberofDocumentContain = numberofDocumentContain(acd.get(i).candidate.get(j),acd);
					        visited.put(acd.get(i).candidate.get(j).s, numberofDocumentContain);
					        acd.get(i).candidate.get(j).setNumberofDocuments(numberofDocumentContain);
					    }

			 }
		}
	
}
		
		
	

