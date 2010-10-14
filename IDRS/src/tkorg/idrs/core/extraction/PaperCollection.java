package tkorg.idrs.core.extraction;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PaperCollection {
	
	private List<PaperObject> paperCollection = null;
	
	public PaperCollection() {
		paperCollection = new ArrayList<PaperObject>();
	}
	
	public void add(PaperObject paperObject) {
		
		paperCollection.add(paperObject);
	}
	
	public static void tranferData(PaperCollection from, final PaperCollection to) {
		for(int i = 0; i < from.getSize(); i++) {
			to.getPaperObject(i).setTitle(from.getPaperObject(i).getTitle());
			to.getPaperObject(i).setAuthor(from.getPaperObject(i).getAuthor());
			to.getPaperObject(i).setEmail(from.getPaperObject(i).getEmail());
			to.getPaperObject(i).setAffiliation(from.getPaperObject(i).getAffiliation());
			to.getPaperObject(i).setPublisher(from.getPaperObject(i).getPublisher());
			to.getPaperObject(i).setYearPublish(from.getPaperObject(i).getYearPublish());
			to.getPaperObject(i).setAbstractPaper(from.getPaperObject(i).getAbstractPaper());
			to.getPaperObject(i).setReferences(from.getPaperObject(i).getReferences());
		}
	}
	
	public int getSize() {
		return paperCollection.size();
	}
	
	public PaperObject getPaperObject(){
		return null;
	}
	
	public PaperObject getPaperObject(int index){
		return paperCollection.get(index);
	}
	
	public void remove(int i){
		paperCollection.remove(i);
	}
	
	public Document toXML(){
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder domBuilder = domFactory.newDocumentBuilder();

		    Document newDoc = domBuilder.newDocument();
		    Element rootElement = newDoc.createElement("papers");
		    newDoc.appendChild(rootElement);
		    
		    for (int i=0; i<paperCollection.size(); i++){
		    	Element paperElement = newDoc.createElement("paper");
				    
				Element urlElement = newDoc.createElement("url");
				urlElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getUrl()));
				paperElement.appendChild(urlElement);
				
				Element titleElement = newDoc.createElement("title");
				titleElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getTitle()));				
				paperElement.appendChild(titleElement);
				
				Element authorsElement = newDoc.createElement("authors");
				if(paperCollection.get(i).getAuthor() != null) {
					for (int j=0; j<paperCollection.get(i).getAuthor().size(); j++){
						Element authorElement = newDoc.createElement("author");	
						
						Element authorNameElement = newDoc.createElement("name");	
						authorNameElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getAuthor().get(j).getName()));					
						authorElement.appendChild(authorNameElement);
						
						Element authorAffiliationElement = newDoc.createElement("affiliation");	
						authorAffiliationElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getAuthor().get(j).getAffiliation()));
						authorElement.appendChild(authorAffiliationElement);
						
						Element authorEmailElement = newDoc.createElement("email");
						authorEmailElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getAuthor().get(j).getEmail()));
						authorElement.appendChild(authorEmailElement);
						
						Element authorWebElement = newDoc.createElement("web");
						authorWebElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getAuthor().get(j).getWeb()));
						authorElement.appendChild(authorWebElement);
						
						authorsElement.appendChild(authorElement);
					}
				}
				
				paperElement.appendChild(authorsElement);
				
				Element abstractElement = newDoc.createElement("abstract");
				abstractElement.appendChild(newDoc.createTextNode(paperCollection.get(i).getAbstractPaper()));
				paperElement.appendChild(abstractElement);
				
				Element referencesElement = newDoc.createElement("references");
				if(paperCollection.get(i).getReferences() != null) {
					for (int k=0; k<paperCollection.get(i).getReferences().size(); k++){
						Element referenceSubElement = newDoc.createElement("reference");
						
						StringBuffer referenceStr = new StringBuffer();
						List<Author> refAuthorList = paperCollection.get(i).getReferences().get(k).getAuthor();
						for (Author author : refAuthorList) {
							referenceStr.append(author.getName());
							referenceStr.append(", ");
						}
						referenceStr.append(". ");
						referenceStr.append(paperCollection.get(i).getReferences().get(k).getTitle());
						referenceStr.append(". ");
						referenceStr.append(paperCollection.get(i).getReferences().get(k).getPublisher());
						referenceStr.append(". ");
						referenceStr.append(paperCollection.get(i).getReferences().get(k).getYearPublish());
						
						referenceSubElement.appendChild(newDoc.createTextNode(referenceStr.toString()));
						
						referencesElement.appendChild(referenceSubElement);
					}
					paperElement.appendChild(referencesElement);
				}				
				
				rootElement.appendChild(paperElement);	
				System.out.println("Complete return document DOM");
		    }
		    return newDoc;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
}
