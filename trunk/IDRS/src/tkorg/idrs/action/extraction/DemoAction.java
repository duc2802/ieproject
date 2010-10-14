/**
 * 
 */
package tkorg.idrs.action.extraction;

import java.net.MalformedURLException;
import java.net.URL;

import tkorg.idrs.core.extraction.FactoryUtil;
import tkorg.idrs.core.extraction.GateExtractionObject;
import tkorg.idrs.core.extraction.PaperCollection;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;

/**
 * @author DUC
 *
 */
public class DemoAction {

	/**
	 * @param args
	 * @author Huynh Minh Duc
	 * @throws ResourceInstantiationException 
	 * @throws MalformedURLException 
	 * @throws ExecutionException 
	 */
	public static void main(String[] args) throws ResourceInstantiationException, MalformedURLException, ExecutionException {
		/*URL url = new URL("file:///D:/Documents/b.pdf");
		
		GateExtractionObject gateExtractionObject = new GateExtractionObject();
		
		//gateExtractionObject.setANNIE(1);
		//gateExtractionObject.initAnniePlugIn();
		
		Document doc = FactoryUtil.createDocument(url);	
		Corpus corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");	
		
		System.out.println("new doc");
		corpus.add(doc);
		gateExtractionObject.setCorpus(corpus);
		
		System.out.println("exexute");
		PaperCollection paperCollection = gateExtractionObject.execute();
		System.out.println("end");
		for (int i = 0; i < paperCollection.getSize(); i++) {
			for(int j = 0; j < paperCollection.getPaperObject(i).getReferences().size(); j++) {
				System.out.println("_________________________: " + j);
				System.out.println(paperCollection.getPaperObject(i).getReferences().get(j).getContent());
			}
		}*/
		
		System.out.println("String trim example!");
	    String str = "     RoseIndia ldkfjldk" +
	    		"";
	    System.out.println("Given String :" + str);
	    System.out.println("After trim :" +str.trim());
		
	}

}
