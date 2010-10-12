package demo.input;

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
			for (int i = 0; i < header.length; i++) {
				StringBuilder headerXML = new StringBuilder();
				headerXML.append("<NEW_HEADER>");
				headerXML.append(header[0]);
				headerXML.append("</NEW_HEADER>");
				header[i] = headerXML.toString();
				countHeader++;
			}
			System.out.println("Count header : " + countHeader);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        InputSource is = new InputSource();
	        is.setCharacterStream(new StringReader(header[0]));

	        Document doc = db.parse(is);
	        NodeList nodes = doc.getElementsByTagName("title");
	        Element element = (Element) nodes.item(0);
	        NodeList fstNm = element.getChildNodes();
	        System.out.println("Title header 1 : "  + ((Node) fstNm.item(0)).getNodeValue());
	        
	        NodeList nodes1 = doc.getElementsByTagName("author");
	        Element element1 = (Element) nodes1.item(0);
	        NodeList fstNm1 = element1.getChildNodes();
	        System.out.println("Author header 1 : "  + ((Node) fstNm1.item(0)).getNodeValue());
	        
	        NodeList nodes2 = doc.getElementsByTagName("date");
	        Element element2 = (Element) nodes2.item(0);
	        NodeList fstNm2 = element2.getChildNodes();
	        System.out.println("Date Publidh header 1 : "  + ((Node) fstNm2.item(0)).getNodeValue());
	        
	        NodeList nodes3 = doc.getElementsByTagName("abstract");
	        Element element3 = (Element) nodes3.item(0);
	        NodeList fstNm3 = element3.getChildNodes();
	        System.out.println("Abstract header 1 : "  + ((Node) fstNm3.item(0)).getNodeValue());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("File not found");
		}
	}
}
