package tkorg.idrs.core.searchengines;
import java.util.ArrayList;
/**
 * @author tiendv 
 * Class Token of ArrayString
 */
public class Token implements Comparable{
	public String s;
	int frequency = 0 ;// Tern frequency in TextChunk
	public int weightedTF =0;
	double normalizedWTF =0;
	// attribute for Token
	int toltalfrequency=0;
	int toltalWeightedTF=0;
	double toltalNormalizedWTF=0;
	double normalizedFrequency = 0;
	int rank =0;
	// attribute for CandidateAnswers
	
	int totalFreQuency = 0;
	double totalAveageRank =0;
	int numberofDocuments = 0;
	double totalNormalizedFrequency =0;
	double normalizedFrequencyWeight =0;
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public double getNormalizedFrequency() {
		return normalizedFrequency;
	}


	public void setNormalizedFrequency(double normalizedFrequency) {
		this.normalizedFrequency = normalizedFrequency;
	}	
	public int getTotalFreQuency() {
		return totalFreQuency;
	}


	public void setTotalFreQuency(int totalFreQuency) {
		this.totalFreQuency = totalFreQuency;
	}


	public double getTotalAveageRank() {
		return totalAveageRank;
	}


	public void setTotalAveageRank(double totalAveageRank) {
		this.totalAveageRank = totalAveageRank;
	}


	public int getNumberofDocuments() {
		return numberofDocuments;
	}


	public void setNumberofDocuments(int numberofDocuments) {
		this.numberofDocuments = numberofDocuments;
	}


	public double getTotalNormalizedFrequency() {
		return totalNormalizedFrequency;
	}


	public void setTotalNormalizedFrequency(double totalNormalizedFrequency) {
		this.totalNormalizedFrequency = totalNormalizedFrequency;
	}


	public double getNormalizedFrequencyWeight() {
		return normalizedFrequencyWeight;
	}


	public void setNormalizedFrequencyWeight(double normalizedFrequencyWeightOfCandidate) {
		this.normalizedFrequencyWeight = normalizedFrequencyWeightOfCandidate;
	}

	public int getToltalfrequency() {
		return toltalfrequency;
	}
	

	public void setToltalfrequency(int toltalfrequency) {
		this.toltalfrequency = toltalfrequency;
	}

	public int getToltalWeightedTF() {
		return toltalWeightedTF;
	}

	public void setToltalWeightedTF(int toltalWeightedTF) {
		this.toltalWeightedTF = toltalWeightedTF;
	}

	public double getToltalNormalizedWTF() {
		return toltalNormalizedWTF;
	}

	public void setToltalNormalizedWTF(double toltalNormalizedWTF) {
		this.toltalNormalizedWTF = toltalNormalizedWTF;
	}

	
	public double getNormalizedWTF() {
		return normalizedWTF;
	}

	public void setNormalizedWTF(double normalizedWTF) {
		this.normalizedWTF = normalizedWTF;
	}

	public int getWeightedTF() {
		return weightedTF;
	}

	public void setWeightedTF(int weightedTF) {
		this.weightedTF = weightedTF;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Token() {
		this.s = null;
	}

	public Token(String s) {
		this.s = s;
	}	
	/**
	 * Compare Token with StopWord
	 * @param stopWord : Stop Word
	 * @return : true if String is stop word
	 */
	
	public boolean compareStopWord(String stopWord){
		if(this.s.toLowerCase().trim().equals(stopWord))
			return true;
		return false;
	}
	
	/**
	 * Compare two token
	 * @param tk : token for compare
	 * @return true if two token similar
	 */
	
	public boolean compareTwoToken(Token tk){
		if(this.s.toLowerCase().trim().equals(tk.s.toLowerCase()))
			return true;
		return false;
	}
	
	/**
	 * Remove Stop Words From ArrayList
	 * @param arrt : Arraylist Token
	 * @return  Arraylist Token without Stopword
	 */
	
	public  ArrayList<Token> removeStopwordsFromArrayListToken(final ArrayList<Token> arrt) {
		ArrayList<Token> result = (ArrayList<Token>) arrt.clone();
		ArrayList<String > listStopWord = new FileLoadder().loadListStopwordsList();
		for(int i=0;i<arrt.size();i++) {
			for(int j=0;j<listStopWord.size();j++) {
				if (arrt.get(i).compareStopWord(listStopWord.get(j))) {	
					result.remove(arrt.get(i));					
				}
			}				
		}
		return result;
	}


	@Override
	public int compareTo(Object o) {
	    if (this.toltalfrequency == ((Token) o).toltalfrequency)
	        return 0;
	    else if ((this.toltalfrequency) > ((Token) o).toltalfrequency)
	        return -1;
	    else
	        return 1;
	}
	
}