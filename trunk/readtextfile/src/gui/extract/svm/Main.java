package gui.extract.svm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import core.extract.svm.Header;
import core.extract.svm.Line;

import utilily.extract.svm.HeaderReader;
import utilily.extract.svm.LabelConst;

public class Main {
	
	/**
	 * @param args
	 */
	public static final String separateLine = "\n";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pathFile = "text.txt";
		//String pathFile = "tagged_headers.txt";					
		String[] headersText = HeaderReader.read(pathFile);
		Header[] headers = new Header[headersText.length];
		int countHeader = 0;
		for (int i = 0; i < headersText.length; i++) {
			headers[i] = new Header(headersText[i]);
			countHeader++;
		}
		System.out.println("Counted Header : " + countHeader);
		for (int i = 0; i < headers.length; i++) {
			System.out.println("Header " + (i + 1));
			System.out.println("===================================");
			System.out.println("Title : " );
			ArrayList<Line> lines = headers[i].getLineWithLabel(LabelConst.TITLE);
			for (Line line : lines) {
				System.out.println(line.getContent());
			}
			
			System.out.println("Author : " );
			lines = headers[i].getLineWithLabel(LabelConst.AUTHOR);
			for (Line line : lines) {
				System.out.println(line.getContent());
			}
			
			System.out.println("Affiliation : " );
			lines = headers[i].getLineWithLabel(LabelConst.AFFILIATION);
			for (Line line : lines) {
				System.out.println(line.getContent());
			}
			
			System.out.println("Abstract : " );
			lines = headers[i].getLineWithLabel(LabelConst.ABSTRACT);
			for (Line line : lines) {
				System.out.println(line.getContent());
			}
		}
		
	}
}
