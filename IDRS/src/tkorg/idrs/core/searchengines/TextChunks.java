
package tkorg.idrs.core.searchengines;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
/**
 * @author tiendv
 * Text chucks from array of Token
 */
public class TextChunks extends Token implements Comparable {
	public ArrayList<Token> arrt = new ArrayList<Token>();
	int startTextchunks;
	int endTextChunks;
	public int weigh;// number of keywords occurrences in text chunk
	public TextChunks() {
	}
	
	public int getWeigh() {
		return weigh;
	}

	public void setWeigh(int weigh) {
		this.weigh = weigh;
	}

	public TextChunks(String st) {
		this.arrt.add(new Token(st));
	}
	
	public TextChunks(Token tk) {
		this.arrt.add(tk);
	}
	public int getStartTextchunks() {
		return startTextchunks;
	}

	public void setStartTextchunks(int startTextchunks) {
		this.startTextchunks = startTextchunks;
	}

	public int getEndTextChunks() {
		return endTextChunks;
	}

	public void setEndTextChunks(int endTextChunks) {
		this.endTextChunks = endTextChunks;
	}
	
	/**
	 * 	Get subtextchunks from Keywords 
	 * ArrayList<Token> keyword : arraylist keyword after token question of user input 
	 * ArrayList<Token> resultSearchEngine : Result from seach engine
	 * return a arraylist textchunks with keyword inside
	 */
	
	public ArrayList<TextChunks>  getsubTextChunksFromKeywords (ArrayList<Token> keyword, ArrayList<Token> resultSearchEngine) {
		ArrayList<TextChunks> result = new ArrayList<TextChunks>();
		for(int j=0;j< keyword.size();j++){
			for(int i=0 ; i< resultSearchEngine.size();i++) {
				if (keyword.get(j).compareTwoToken(resultSearchEngine.get(i))== true) {
					TextChunks temp = new TextChunks();
					temp.setStartTextchunks(findMinIndex(i));
					temp.setEndTextChunks(findMaxIndex(i, resultSearchEngine.size()));
					temp= makeTextchuckForKeyWord(resultSearchEngine,temp.getStartTextchunks() ,temp.getEndTextChunks());
					temp.setWeigh(1);
					result.add(temp);
				}	
			}
		}
		 return result;
	}
	
	/**
	 *  Find min index for decide position Arraylist Token SearchResult to make a subTextChunk
	 * @param index : Index of Array Result String
	 * @param sizeofArrayList
	 * @return Min index of Arraylist Token SearchResult
	 */
	
	public static int findMinIndex (int index) {
		int min =index-9; 
		while(min<0)
		{
			min++;
		}
		return min ;	
	}
	
	/**
	 *  Find Max index for decide position Arraylist Token SearchResult to make a subTextChunk
	 * @param index : Index of Array Result String
	 * @param sizeofArrayList
	 * @return  Max index of Arraylist Token SearchResult
	 */
	
	public static int findMaxIndex (int index, int sizeofArrayList) {
		int max = index+9;
		while(max >sizeofArrayList )
		{
			max --;
		}
		return max;	
	}
	
	/**
	 * Make Textchucks for KeyWords
	 * @param resultSearchEngine : Arraylist Token From SearchEngineResult 
	 * @param min : Start index of Arraylist Token SearchResult for make TextChunk
	 * @param max : End index of Arraylist Token SearchResult for make TextChunk
	 * @return TextChunks
	 */
	
	public static  TextChunks makeTextchuckForKeyWord( ArrayList<Token> resultSearchEngine,int min,int max ) {
		TextChunks result = new TextChunks();
		result.setStartTextchunks(min);
		result.setEndTextChunks(max);
		for(int i=min ; i<max;i++){
			result.arrt.add(resultSearchEngine.get(i));
		}
		return result;	
	}
	
	/**
	 * Check OverLaping TextCheck
	 * @param tc : TextCheck for Check 
	 * @return true if two TextCheck overlaping
	 */
	
	public boolean checkOverlapingTwoTextchunks (TextChunks tc) {
		Boolean result = true;
		if (this.getStartTextchunks() > tc.getEndTextChunks() || this.getEndTextChunks() < tc.getStartTextchunks() ) 
			result = false;
		return result;	
	}
	
	/**
	 * Combine Two Textchunks overlaping 
	 * @param fistTC : Fist Textchunks 
	 * @param secondTC : Second TextChunks
	 * @param resultSearchEngine : Result From SearchEngine
	 * @return textchunks 
	 */
	
	public TextChunks handleOverlaping (TextChunks fistTC, TextChunks secondTC, ArrayList<Token> resultSearchEngine )
	{
		TextChunks result = new TextChunks();
		result.setStartTextchunks(Math.min(fistTC.getStartTextchunks(),secondTC.getStartTextchunks()));
		result.setEndTextChunks(Math.max(fistTC.getEndTextChunks(), secondTC.getEndTextChunks()));
		result = makeTextchuckForKeyWord (resultSearchEngine,result.getStartTextchunks(),result.getEndTextChunks());
		return result;	
	}
	
	/**
	 * Get finalTextchuck with keyword
	 * @param textchunkswithkeyword : Arraylist text chunk with keyword inside
	 * @param resultSearchEngine : Result from searchengine .
	 * @return : resturn Arraulist Textchunk don't have overlaping chunk
	 */	
	
	public ArrayList<TextChunks> finalTextChunks(ArrayList<TextChunks> textchunkswithkeyword, ArrayList<Token> resultSearchEngine ) {
		ArrayList<TextChunks > result = (ArrayList<TextChunks>) textchunkswithkeyword.clone();
			int j;
			int index;
			for(int i=0;i< result.size() ;i++) {
				index = i;
				if(i+1>=result.size()){
					break;
				}
				j=i+1;
				if(result.get(i).checkOverlapingTwoTextchunks(result.get(j))== true) {
					TextChunks temp = new TextChunks();
					temp = handleOverlaping(result.get(i),result.get(j),resultSearchEngine);
					temp.setWeigh(result.get(i).getWeigh()+1);
					result.set(i, temp);
					result.remove(j);
					i = index-1 ;
					continue;
				}	
		}
		for (int k=0 ; k < result.size() ; k ++ ) {
			updateAttibuteOfTokenInTextChunks(result.get(k));
			}	
		return result;	
	}
	
	/**
	 * Count token in TextChunks
	 * @param t : token to count
	 * @param tc : textchunks to count 
	 * @return 
	 */
	private static int count(Token t, TextChunks tc) {
		  int cont = 0;
		  for (Token token : tc.arrt) {
		      if ( t.compareTwoToken(token) ) {
		          cont++;
		      }
		  }
		  return cont;
	}
	
	/**
	 * Update Attibute Of Token In TextChunks 
	 * @param tc : TextChunks for Update 
	 */
	
	public static void updateAttibuteOfTokenInTextChunks (TextChunks tc) {
		  Hashtable<String,Integer> visited = new Hashtable<String,Integer>();
		  int weightTextchunk = tc.getWeigh();
		  int sizeOfTextchunk = tc.arrt.size();
		  for (Token token : tc.arrt){
		    if (visited.containsKey(token.s)) {
		        token.setFrequency( visited.get(token.s));
		        token.setWeightedTF(weightTextchunk*token.getFrequency());
		        token.setNormalizedWTF((double)token.getWeightedTF()/sizeOfTextchunk);
		    } else {
		        int n = count(token, tc);
		        visited.put(token.s, n);
		        token.setFrequency(n);
		        token.setWeightedTF(weightTextchunk*token.getFrequency());
		        token.setNormalizedWTF((float)token.getWeightedTF()/sizeOfTextchunk);
		    }
		  }
	}
	
	/**
	 * Get TexChunk have Max Weight In Arraylist TextChunks
	 * @param finalTextChunks : Arraylist Textchunks for get a Textchunks have max weight
	 * @return TexChunk
	 */
	
	public TextChunks getTexChunkhaveMaxWeightInArraylistTextChunk (ArrayList<TextChunks> finalTextChunks, ArrayList<Token> keywords)
	{
		Collections.sort(finalTextChunks);
		TextChunks result = new TextChunks();
		result = finalTextChunks.get(0);
		System.out.println("Truoc khi remove nay .................");
		System.out.println(result.arrt.size());
		removeKeyWordInTextChunks(result,keywords);
		System.out.println("Sau khi remove nay .................");
		System.out.println(result.arrt.size());
		updateAttributeForTokenInMaxWeightTexChunk(result,finalTextChunks);
		return result;
	}
	
	/**
	 *  Total Frequency OF Token In ArrayList TextChunk
	 * @param tc : Token to caculate
	 * @param finalTextChunks : ArrayList TextChunk do't have overlaping
	 * @return Total Frequency OF Token In ArrayList TextChunk
	 */
	
	public static int totalFrequencyofTokenInArrayListTextChunk (Token tc, ArrayList<TextChunks> finalTextChunks) {
		int result = tc.getFrequency();
		for (int i =0 ; i < finalTextChunks.size() ; i ++ ) {
			if(finalTextChunks.get(i).arrt.contains(tc)== false) {
				Token temp = getTokenFromTextChunk(tc,finalTextChunks.get(i));
				result+=temp.getFrequency();
			}
		}
		return result;
	}
	
	/**
	 * Total WeightedTF of Token In ArrayList TextChunk
	 * @param tc 
	 * @param finalTextChunks
	 * @return
	 */
	
	public static int totalWeightedTFofTokenInArrayListTextChunk (Token tc, ArrayList<TextChunks> finalTextChunks) {
		int result = tc.getWeightedTF();
		for (int i =0 ; i < finalTextChunks.size() ; i ++ ) {
			if(finalTextChunks.get(i).arrt.contains(tc)== false) {
				Token temp = getTokenFromTextChunk(tc,finalTextChunks.get(i));
				result+=temp.getWeightedTF();
			}
		}
		return result;
	}
	
	/**
	 * Total NormalizedWTF of Token InArrayList TextChunk
	 * @param tc
	 * @param finalTextChunks
	 * @return
	 */
	
	public static double totalNormalizedWTFofTokenInArrayListTextChunk (Token tc, ArrayList<TextChunks> finalTextChunks) {
		double result = tc.getNormalizedWTF();
		for (int i =0 ; i < finalTextChunks.size() ; i ++ ) {
			if(finalTextChunks.get(i).arrt.contains(tc)== false) {
				Token temp = getTokenFromTextChunk(tc,finalTextChunks.get(i));
				result+=temp.getNormalizedWTF();
			}
		}
		return result;
	}
	
	/**
	 * Get Token From Textchunks 
	 * @param tk : Token want to get From TextChunk
	 * @param tc : Textchunk 
	 * @return Token
	 */
	
	public static Token getTokenFromTextChunk(Token tk, TextChunks tc ){
		Token result = new Token();
		for (int i = 0; i < tc.arrt.size();i++) {
			if (tk.compareTwoToken(tc.arrt.get(i))== true){
				result.setFrequency( tc.arrt.get(i).getFrequency());
				result.setNormalizedWTF(tc.arrt.get(i).getNormalizedWTF());
				result.setWeightedTF(tc.arrt.get(i).getWeightedTF());
				break;
			}
		}
		return result;		
	}
	/**
	 * Update Attribute For Token InMaxWeightTexChunk 
	 * Total Frequency of Token
	 * Total WeightedTF of Token
	 * Total NormalizedWTF of Token
	 * @param tc : TextChunks have max weight
	 * @param finalTextChunks : ArrayList<TextChunks>
	 */
	
	public static void  updateAttributeForTokenInMaxWeightTexChunk (TextChunks tc , ArrayList<TextChunks> finalTextChunks) {
		for (int i = 0 ; i <tc.arrt.size(); i++ )
		{
			int toltalfrequency = totalFrequencyofTokenInArrayListTextChunk (tc.arrt.get(i),finalTextChunks);
			int toltalWeightedTF = totalWeightedTFofTokenInArrayListTextChunk (tc.arrt.get(i),finalTextChunks);
			double toltalNormalizedWTF = totalNormalizedWTFofTokenInArrayListTextChunk (tc.arrt.get(i),finalTextChunks);
			Token temp = tc.arrt.get(i);
			temp.setToltalfrequency(toltalfrequency);
			temp.setToltalWeightedTF(toltalWeightedTF);
			temp.setToltalNormalizedWTF(toltalNormalizedWTF);
			tc.arrt.set(i, temp);
		}
	}

	/**
	 * Remove Token KeyWord In TextChunks have max weight
	 * @param tc : TextChunks
	 * @param keyWords : ArrayList TokenKey word
	 */
	
	public void removeKeyWordInTextChunks (TextChunks tc,ArrayList<Token> keyWords){
		for (int i =0 ; i<tc.arrt.size(); i++ ) {
			if(i+1>=tc.arrt.size()){
				break;
			}
			for (int j = 0 ; j < keyWords.size() ; j++)
			{
				if (keyWords.get(j).compareTwoToken(tc.arrt.get(i))== true ){
					tc.arrt.remove(i);	
				}
				continue;
			}
		}
	}
	
	/**
	 * Get Top Candidate Answers From Document
	 * @param tc : Texchunks have max weight
	 * @return
	 */
	
	public ArrayList<Token> topCandidateAnswersFromDocument(TextChunks tc) {
		Collections.sort(tc.arrt);
		int maxFrequency = tc.arrt.get(0).getFrequency();
		ArrayList <Token> result = new ArrayList<Token>();
		for (int k = 0 ; k<tc.arrt.size(); k++){
			double tokenNormalized =(float) tc.arrt.get(k).getFrequency()/maxFrequency;
			tc.arrt.get(k).setNormalizedFrequency(tokenNormalized);
			tc.arrt.get(k).setRank(k);
			if (k>10)
				break;
			result.add(tc.arrt.get(k));
		}
		return result;
	}
	@Override
	public int compareTo(Object o) {
	    if (this.weigh == ((TextChunks) o).weigh)
	        return 0;
	    else if ((this.weigh) > ((TextChunks) o).weigh)
	        return -1;
	    else
	        return 1;
	}
	

}
