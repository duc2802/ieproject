package tkorg.idrs.core.extraction;

import java.net.URL;

import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.util.Out;

public class FactoryUtil {
	/**
	 * 
	 * comment : The purpose of this class is creating Document, Corpus become simple, easily. 
	 */
	public FactoryUtil(){
		
	}
	
	/**
	 * 
	 * comment : compile many action to create Document.
	 * @param url
	 * @return
	 * @author Huynh Minh Duc
	 */
	public static Document createDocument(URL url){
		try {
			FeatureMap params = Factory.newFeatureMap();
		    params.put(SOURCE_URL_FEATURE, url);	
		    params.put("preserveOriginalContent", new Boolean(true));
		    params.put("collectRepositioningInfo", new Boolean(true));		    
		    Document doc = (Document) Factory.createResource(PACKAGE_DOCUMENT_IMPL, params);		    
		    return doc;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static Document createDocument(String content){
		try {
			FeatureMap params = Factory.newFeatureMap();
		    params.put("stringContent", content);	
		    params.put("preserveOriginalContent", new Boolean(true));
		    params.put("collectRepositioningInfo", new Boolean(true));		    
		    Document doc = (Document) Factory.createResource(PACKAGE_DOCUMENT_IMPL, params);		    
		    return doc;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * comment : compile many action to create Document.
	 * @param params
	 * @return
	 * @author Huynh Minh Duc
	 */
	public static Document creDocument(FeatureMap params){
		try {
			Document doc = (Document)
		    Factory.createResource(PACKAGE_DOCUMENT_IMPL, params);	
			return doc;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static final String PACKAGE_DOCUMENT_IMPL = "gate.corpora.DocumentImpl";
	public static final String SOURCE_URL_FEATURE = "sourceUrl";
}
