package tkorg.idrs.utilities;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;

public class XMLUtility {
	public XMLUtility(){
		
	}
	
	/**
	 * 
	 * comment : 
	 * @param doc
	 * @param filename
	 * @author Huynh Minh Duc
	 */
	public static void writeXmlFile(Document doc, String filename) 
	{ 
		try {
            // Prepare the DOM document for writing
            Source source = new DOMSource(doc);
 
            // Prepare the output file
            File file = new File(filename);
            Result result = new StreamResult(file);
 
            // Write the DOM document to the file
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xformer.transform(source, result);
        } catch (TransformerConfigurationException e) {
        } catch (TransformerException e) {
        }
	} 
}
