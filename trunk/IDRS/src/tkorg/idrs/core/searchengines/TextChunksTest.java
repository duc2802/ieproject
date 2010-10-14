package tkorg.idrs.core.searchengines;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TextChunksTest extends TestCase {

	public TextChunksTest(String name) {
		super(name);
	}

	public void testTextChunks() {
	
	}

	public void testTextChunksString() {
		
	}

	public void testTextChunksToken() {
	
	}

	public void testGetsubTextChunksFromKeywords() {
		
	}


	public void testMakeTextchuckForKeyWord() {
	/*	Token t = new Token();
		String keyword = "test";
		String keywords = "b";
		String result = "dien a b c  test d is a base test on database test he she die vn dh ah  ";
		ArrayList<Token> tresult = t.creatArrayTokenFromString(result);
		Token tkeyword = new Token(keyword);
		Token tkeyword2 = new Token(keywords);
		ArrayList<Token> test = new ArrayList<Token>();
		test.add(tkeyword);
		test.add(tkeyword2);
		TextChunks test2 = new TextChunks();
		ArrayList<TextChunks> tcresult = new  ArrayList<TextChunks>();
		tcresult = test2.getsubTextChunksFromKeywords(test, tresult);
		for (int i =0;i<tcresult.size();i++)
		{
			TextChunks resultn = new TextChunks(); 
			System.out.println("---------------------------------------"+ i);
			resultn = tcresult.get(i);
			for(int j=0; j< resultn.arrt.size();j++)
			{
				System.out.println(resultn.arrt.get(j).s);
			}
		}*/
		
	}

	public void testCheckOverlapingTwoTexcheck() {
	
	}

	public void testHandleOverlaping() {
		
	}

	/*public void testFinalTextChunks() {
		Token t = new Token();
		String keyword = "test";
		String keywords = "b";
		String resultSearchEngine = "dien a b test d is a base  on  test database b  he she die test vn dh ah ";
		ArrayList<Token> arraylistSearchResult = t.creatArrayTokenFromString(resultSearchEngine);
		Token tkeyword = new Token(keyword);
		Token tkeyword2 = new Token(keywords);
		ArrayList<Token> arraylistKeyword = new ArrayList<Token>();// arraylist keyword
		arraylistKeyword.add(tkeyword);
		//arraylistKeyword.add(tkeyword2);
		TextChunks test2 = new TextChunks();
		ArrayList<TextChunks> subTextChunkincludeKey = new  ArrayList<TextChunks>();
		subTextChunkincludeKey = test2.getsubTextChunksFromKeywords(arraylistKeyword, arraylistSearchResult);
		// get subTextChunk
		for (int i =0;i<subTextChunkincludeKey.size();i++)
		{
			TextChunks temp = subTextChunkincludeKey.get(i);
			System.out.println("---------------------------------------"+ i);
			System.out.println(temp.getStartTextchunks());
			System.out.println(temp.getEndTextChunks());
			for(int j=0; j< temp.arrt.size();j++)
			{
					System.out.println(temp.arrt.get(j).s);
			}
		}
		
		System.out.println("After handerOverlap ");
		ArrayList<TextChunks> tcresultfinal = new  ArrayList<TextChunks>();
		tcresultfinal = test2.finalTextChunks(subTextChunkincludeKey, arraylistSearchResult);
		for (int i =0;i<tcresultfinal.size();i++)
		{
			TextChunks temp = tcresultfinal.get(i);
			System.out.println("---------------------------------------"+ i);
			System.out.println(temp.getStartTextchunks());
			System.out.println(temp.getEndTextChunks());
			for(int j=0; j< temp.arrt.size();j++)
			{	
			System.out.println(temp.arrt.get(j).s);
			}
		}
		
		
	}*/

}
