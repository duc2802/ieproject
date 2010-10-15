package gui.extract.svm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {
	
	/**
	 * @param args
	 */
	public static final String separateLine = "\n";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String pathFile = "text.txt";
		String pathFile = "tagged_headers.txt";		
		File file = new File(pathFile);	
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			StringBuilder dataset = new StringBuilder();
			String line = null;
			
			while((line = bufferedReader.readLine()) != null){
					dataset.append(line);
			}
			
			String[] header = dataset.toString().split("<NEW_HEADER>");
			int countHeader = 0;
			String startTitleTagString = "<title>";
			String endTitleTagString = "</title>";
			
			for (int j = 0; j < header.length; j++) {
				int startPointTitleTag = header[j].indexOf(startTitleTagString) + startTitleTagString.length();
				int endPointTitleTag = header[j].indexOf(endTitleTagString);	
				if(startPointTitleTag > 0 && endPointTitleTag > 0){
					System.out.println("Title header " + (j + 1)+ " : " + header[j].substring(startPointTitleTag, endPointTitleTag));
				}else {
					System.out.println("Title header " + (j + 1)+ " : Not found");
				}
				countHeader++;
			}
			System.out.println("Count header : " + countHeader);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
}
