package utilily.extract.svm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class HeaderReader {
	
	/**
	 * 
	 * @param url
	 * @return
	 * @Author : Huynh Minh Duc
	 * @Comment : Read tag header file and return list string content header separately
	 */
	public static String[] read(String urlTagHeaderFile){
		File file = new File(urlTagHeaderFile);	
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			StringBuilder dataset = new StringBuilder();
			String line = null;
			
			while((line = bufferedReader.readLine()) != null){
					dataset.append(line);
			}			
			String[] header = dataset.toString().split(TagConst.HEADER_TAG_START);
			return header;
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}		
	}
	
	/**
	 * 
	 * @param urlFile
	 * @return
	 * @Author : Huynh Minh Duc
	 * @Comment : Read a file content 
	 */
	public static ArrayList<String> readTextFile(String urlFile){
		ArrayList<String> listWords = new ArrayList<String>();
		File file = new File(urlFile);	
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;
			
			while((line = bufferedReader.readLine()) != null){
				listWords.add(line);
			}	
			return listWords;
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return null;
		}	
	}
		
}
