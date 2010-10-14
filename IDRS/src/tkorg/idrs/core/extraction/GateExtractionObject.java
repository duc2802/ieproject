/**
 * @author Huynh Minh Duc
 */
package tkorg.idrs.core.extraction;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.IDRSApplicationConst;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.Document;
import gate.DocumentContent;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.ProcessingResource;
import gate.creole.ANNIEConstants;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.creole.SerialController;
import gate.util.GateException;
import gate.util.InvalidOffsetException;
import gate.util.Out;
import gate.util.web.WebCrimeReportAnalyser.SortedAnnotationList;

/**
 * @author Huynh Minh Duc
 *
 */
public class GateExtractionObject {
		
	private Corpus corpus;	
	private SerialAnalyserController annieController;
	private AnnotationSet annotationSet;
	
	
	public GateExtractionObject() {
		try {
			initGateInfo();
			initAnniePlugIn();
		} catch (MalformedURLException e) {
			// TODO: handle exception
		}		
		
	}
	
	/**
	 * 
	 * comment : initialize Gate Object.
	 * @author Huynh Minh Duc
	 * @throws MalformedURLException 
	 */
	public void initGateInfo() throws MalformedURLException {
		try {
			Gate.init();
			Out.prln();			
			Out.prln("================================================================");
			Out.prln(IDRSResourceBundle.res.getString("initialising.gate"));
			//System.setProperty("gate.home", IDRSApplicationConst.GATE_HOME);
			File gateHome = Gate.getGateHome();
		    File pluginsHome = new File(gateHome, "plugins");
		    Gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "ANNIE1").toURL());
		    Out.prln(IDRSResourceBundle.res.getString("gate.home") + gateHome.toString() + pluginsHome.toString());
		    Out.prln(IDRSResourceBundle.res.getString("gate.initialised"));	   
		} catch (GateException e) {
			Out.prln(IDRSResourceBundle.res.getString("initialising.gate.unsuccessfull"));
		}
		
	}
	
	/**
	 * 
	 * comment : Initialize ANNIE Object Resource
	 * @author Huynh Minh Duc
	 */
	public void initAnniePlugIn(){		
		Out.prln("Initialising ANNIE...");
		try {			
			annieController =				
				(SerialAnalyserController) Factory.createResource(
				"gate.creole.SerialAnalyserController", Factory.newFeatureMap(),
				Factory.newFeatureMap(), "ANNIE_" + Gate.genSym());
			
			System.out.println("ANNIE_" + Gate.genSym());
			
			for(int i = 0; i < ANNIEConstants.PR_NAMES.length; i++) {
				FeatureMap params = Factory.newFeatureMap();
				ProcessingResource pr = (ProcessingResource) Factory.createResource(ANNIEConstants.PR_NAMES[i], params);				
				annieController.add(pr);
		    } 
		} catch (Exception e) {			
			Out.prln(IDRSResourceBundle.res.getString("annie.loaded.failed"));
		}
		Out.prln(IDRSResourceBundle.res.getString("annie.loaded"));		
	}
	
	/**
	 * 
	 * @author : Huynh Minh Duc
	 * Aug 18, 2010
	 * @comment :
	 */
	public void removeAnniePlugin(){
		if(annieController != null) {
			List<ProcessingResource> pr = (List)annieController.getPRs();
			for (int i = 0; i < pr.size(); i++) {
				ProcessingResource processingResource = pr.get(i);
				Out.println(processingResource.getName());
				Factory.deleteResource(processingResource);	
			}			
			Factory.deleteResource(annieController);
			Out.println("...ANNIE Plugin removed");
		}
	}
	
	public void setANNIE(int i) {
		try {	
			Out.prln();
			Out.prln("================================================================");
			File gateHome = Gate.getGateHome();
		    File pluginsHome = new File(gateHome, "plugins");
		    //Out.prln("GATE HOME : ...." + pluginsHome.toString());
		    //Gate.getCreoleRegister().clear();
		    Gate.getCreoleRegister().registerDirectories(new File(pluginsHome, "ANNIE" + i).toURL());		    
		   // Out.prln("...GATE initialised");		    
		} catch (GateException e) {
			Out.prln(IDRSResourceBundle.res.getString("initialising.gate.unsuccessfull"));
		} catch (MalformedURLException e) {
			Out.prln(IDRSResourceBundle.res.getString("initialising.gate.unsuccessfull"));
		}
		
	}
	
	/**
	 * 
	 * comment : perform processing of plug in 
	 * @param namePlugin
	 * @author Huynh Minh Duc
	 * @throws ExecutionException 
	 * @throws ResourceInstantiationException 
	 */
	public PaperCollection execute() throws ExecutionException, ResourceInstantiationException {
		IDRSApplication.idrsStatus.setMessage(IDRSResourceBundle.res.getString("process"));
		IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("run.annie"));
		
		PaperCollection paperCollection = new PaperCollection();		  
		
		Corpus contentFirstPageCorpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");	
		Corpus contentAboveAbstractKeyWordCorpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");	
		Corpus contentReferencesCorpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");	
		
		
		//Run ANNIE 1
		Out.prln("=========================");
	    Out.prln(IDRSResourceBundle.res.getString("set.new.annie1"));			
		Out.prln("idrs_AbstractWord.");
		Out.prln("idrs_AbstractEndWord.");
		Out.prln("idrs_ReferenceWord.");	
		Out.prln("idrs_Publish.");	
		Out.prln("=========================");
		IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.executing.full.content"));
		annieController.execute();  		
		IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.executed.full.content"));
		
	    try {
			Iterator iter = this.getCorpus().iterator();
			
			while(iter.hasNext()) {
				Document doc = (Document) iter.next();
				DocumentContent content = doc.getContent();
				PaperObject paperObject = new PaperObject();
				
				AnnotationPositon firstPagePosition = new AnnotationPositon();
				firstPagePosition.setStartAnnotationPosition(0);
				firstPagePosition.setEndAnnotationPosition(this.getEndPositionOfFirstPage(doc));
				
				AnnotationPositon lastPagePosition = new AnnotationPositon();
				lastPagePosition.setEndAnnotationPosition(this.getEndPositionOfLastPage(doc));
				
				AnnotationPositon abstractAnnotationPosition = this.getPositionOfAnnotationInFirstPage(IDRSApplicationConst.ABSTRACT_WORD, firstPagePosition, doc); 
				AnnotationPositon introductionKeyWord = this.getPositionOfAnnotationInFirstPage(IDRSApplicationConst.ABSTRACT_END_WORD,firstPagePosition , doc);
				AnnotationPositon titleAnnotationPositon = this.getTitlePosition(doc, abstractAnnotationPosition);
				
				AnnotationPositon referenceKeyWord = this.getAnnotationPosition(IDRSApplicationConst.REFERENCES_WORD, doc);
				
				// do some thing to extract reference 
				if(referenceKeyWord.getEndAnnotationPosition() != 0) {
					String contentReferences = content.getContent(referenceKeyWord.getEndAnnotationPosition(), 
																	lastPagePosition.getEndAnnotationPosition()).toString();
					Document contentReferencesDocument = (Document) FactoryUtil.createDocument(contentReferences);
					contentReferencesCorpus.add(contentReferencesDocument);
				}else {
					Document contentReferencesDocument = (Document) FactoryUtil.createDocument(IDRSApplicationConst.REFERENCES_WORD);
					contentReferencesCorpus.add(contentReferencesDocument);
				}
				
				//get Title of the specified paperObject
				paperObject.setTitle(content.getContent(titleAnnotationPositon.getStartAnnotationPosition(), 
															titleAnnotationPositon.getEndAnnotationPosition()).toString()); 
				
				//get Abstract of the specified paperObject
				paperObject.setAbstractPaper(this.getAbstractContentOfDocument(doc, abstractAnnotationPosition, introductionKeyWord));
				
				//Create corpus content first page.
				String contentFirstPage = content.getContent(firstPagePosition.getStartAnnotationPosition(), 
																firstPagePosition.getEndAnnotationPosition()).toString();
				Document contentFirstPageDocument = (Document) FactoryUtil.createDocument(contentFirstPage);
				contentFirstPageCorpus.add(contentFirstPageDocument);	
				
				//Create corpus content Above Abstract 
				String contentAboveAbstract = this.getContentAboveAbstractKeyWordExceptTitle(doc, abstractAnnotationPosition, titleAnnotationPositon, introductionKeyWord);
				Document contentFromAboveAbstractKeyWordDocument = (Document) FactoryUtil.createDocument(contentAboveAbstract);
				
				contentAboveAbstractKeyWordCorpus.add(contentFromAboveAbstractKeyWordDocument);		
				paperCollection.add(paperObject);
			}
			
			//unload persistent document from memory.
			for (int i = 0; i < this.corpus.size(); i++) {
				Factory.deleteResource((Document)this.corpus.get(i));
			}
			
			Factory.deleteResource(this.corpus);
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executed.abstract.title.full.content"));
			
			//get publisher, publish date and set publisher, publish date to paper collection
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.start.executing.first.page.content"));
			annieController.setCorpus(contentFirstPageCorpus);
			annieController.execute(); 
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executing.first.page.content"));
			
			for(int i = 0; i < contentFirstPageCorpus.size(); i++) {
				IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executing.first.page.content") + (i + 1) + "/" + (contentFirstPageCorpus.size()));
				Document firstPageDoc = (Document)contentFirstPageCorpus.get(i);
				paperCollection.getPaperObject(i).setPublisher(
						this.getContentOfPublisher(IDRSApplicationConst.PUBLISH_PLACE, firstPageDoc));
				paperCollection.getPaperObject(i).setYearPublish(
						this.getContentOfPublishDate(IDRSApplicationConst.PUBLISH_DATE, firstPageDoc));
			}
			
			//unload persistent document from memory.
			for (int i = 0; i < contentFirstPageCorpus.size(); i++) {
				Factory.deleteResource((Document) contentFirstPageCorpus.get(i));
			}
			
			contentFirstPageCorpus.clear();
			Factory.deleteResource(contentFirstPageCorpus);
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.executed.publisher.publish.date.full.content"));
			
			// get Authors and set Authors to paper collection
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.start.executing.abstract.content"));
			removeAnniePlugin();
			
			//run ANNIE 2
			setANNIE(2);
			initAnniePlugIn();
			Out.prln("=========================");
			Out.prln(IDRSResourceBundle.res.getString("set.new.annie2"));			
			Out.prln("idrs_Line.");
			Out.prln("idrs_LineEmailAnnotation.");
			Out.prln("idrs_LineAffiliationAnnotation.");
			Out.prln("idrs_Author.");
			Out.prln("=========================");
			
			annieController.setCorpus(contentAboveAbstractKeyWordCorpus);
			annieController.execute(); 
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executing.abstract.content"));
			
			for(int i = 0; i < contentAboveAbstractKeyWordCorpus.size(); i++) {
				IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executing.abstract.content") + (i + 1) + "/" + (contentAboveAbstractKeyWordCorpus.size()));
				
				paperCollection.getPaperObject(i).setEmail(
						this.getEmailContent((Document)contentAboveAbstractKeyWordCorpus.get(i)));
				paperCollection.getPaperObject(i).setAffiliation(
						this.getAffiliationContent((Document)contentAboveAbstractKeyWordCorpus.get(i)));
				paperCollection.getPaperObject(i).setAuthor(
						this.getAuthorListOfDocument(IDRSApplicationConst.AUTHOR,(Document)contentAboveAbstractKeyWordCorpus.get(i)));
			}
			
			//unload persistent document from memory.
			for (int i = 0; i < contentAboveAbstractKeyWordCorpus.size(); i++) {
				Factory.deleteResource((Document) contentAboveAbstractKeyWordCorpus.get(i));
			}

			contentAboveAbstractKeyWordCorpus.clear();
			Factory.deleteResource(contentAboveAbstractKeyWordCorpus);
			IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executed.author.abstract.content"));
			
			// Get references :
			if(contentReferencesCorpus.size() != 0) {
				IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.start.executing.references.content"));
				removeAnniePlugin();
				
				//Run ANNIE 3
				setANNIE(3);
				initAnniePlugIn();
				Out.prln("=========================");
				Out.prln(IDRSResourceBundle.res.getString("set.new.annie3"));				
				Out.prln("idrs_ReferenceBreak.");				
				Out.prln("=========================");
				
				annieController.setCorpus(contentReferencesCorpus);
				annieController.execute(); 
				IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executing.references.content"));
				
				for(int i = 0; i < contentReferencesCorpus.size(); i++) {
					IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("gate.end.executing.references.content") + " : " + (i + 1) + "/" + (contentReferencesCorpus.size()));
					paperCollection.getPaperObject(i).setReferences(this.getReferencesListOfDocument(IDRSApplicationConst.REFERENCES,(Document)contentReferencesCorpus.get(i)));				
				}
				
				//unload persistent document from memory.
				for (int i = 0; i < contentReferencesCorpus.size(); i++) {
					Factory.deleteResource((Document) contentReferencesCorpus.get(i));
				}
				
			}
			
			contentReferencesCorpus.clear();
			Factory.deleteResource(contentReferencesCorpus);
			Gate.getCreoleRegister().clear();	  
		    annieController.cleanup();
		    
		    removeAnniePlugin();
		    IDRSApplication.idrsStatus.setProcessMessage(IDRSResourceBundle.res.getString("annie.complete")); 
	    }
	    catch (Exception ex) {
	    	ex.printStackTrace();
	    } 
	    
	    return paperCollection;
	}
	
	
	
	/**
	 * 
	 * @param doc
	 * @return
	 * @author Huynh Minh Duc
	 */
	public String getAbstractContentOfDocument(Document doc, AnnotationPositon abstractAnnotationPositon, AnnotationPositon introKeyWord) {
		String abstractContent = null;
		try {			
			AnnotationPositon introductionKeyWordPosition = introKeyWord;
			DocumentContent content = doc.getContent();
			if(abstractAnnotationPositon.getEndAnnotationPosition() < introductionKeyWordPosition.getStartAnnotationPosition()) {
				abstractContent = content.getContent(abstractAnnotationPositon.getEndAnnotationPosition(),
							introductionKeyWordPosition.getStartAnnotationPosition()).toString();
			}
			return abstractContent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 
	 * comment : 
	 * @param authorLabel
	 * @param document
	 * @return
	 * @author Huynh Minh Duc
	 */
	public ArrayList<Author> getAuthorListOfDocument(String authorLabel, Document document) {
		
		ArrayList<Author> authorList = new ArrayList<Author>();
		DocumentContent content = document.getContent();
		
		// get Position (start, end) of lines which contain authors
		ArrayList<AnnotationPositon> annotationLinesContainAuthor = this.getLineAnnotationContainAuthor(document);
		try {
			
			// Create corpus which contains authorLines as documents
			Corpus authorLineCorpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
			if(annotationLinesContainAuthor.size() != 0) {
				for (AnnotationPositon authorLinePositon : annotationLinesContainAuthor) {
					Document authorLineDoc = FactoryUtil.createDocument(content.getContent(authorLinePositon.getStartAnnotationPosition(), 
							authorLinePositon.getEndAnnotationPosition()).toString());

					authorLineCorpus.add(authorLineDoc);
				}

				annieController.setCorpus(authorLineCorpus);
				annieController.execute(); 
				
				Iterator iter = authorLineCorpus.iterator();

				Set annotTypesRequired = new HashSet();
				annotTypesRequired.add(authorLabel);				
				while(iter.hasNext()) {
					Document lineDoc = (Document) iter.next();
					content = lineDoc.getContent();
					AnnotationSet annotationSet = lineDoc.getAnnotations();		
					Set<Annotation> authorAnnotations = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));

					Iterator iterAuthorAnnotations = authorAnnotations.iterator();
					Annotation currAnnot;
					while((iterAuthorAnnotations != null) && iterAuthorAnnotations.hasNext()) {
						currAnnot = (Annotation) iterAuthorAnnotations.next();
						Author author = new Author();
						long startAnnotationPosition;
						long endAnnotationPosition;

						startAnnotationPosition = currAnnot.getStartNode().getOffset().longValue();
						endAnnotationPosition = currAnnot.getEndNode().getOffset().longValue();
						
						// just get Author string that have the length > 2 
						if((endAnnotationPosition - startAnnotationPosition) > 2) {
							author.setName(content.getContent(startAnnotationPosition, endAnnotationPosition).toString());
							authorList.add(author);
						}
					}
				}
				
				//unload persistent document from memory.
				for (int i = 0; i < authorLineCorpus.size(); i++) {
					Factory.deleteResource((Document) authorLineCorpus.get(i));
				}
				
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return authorList;
	}
	
	/**
	 * comment : get content contain longest publish date string.
	 * @param publisherLabel
	 * @param doc
	 * @return
	 * @author Huynh Minh Duc
	 */
	public String getContentOfPublishDate(String publishDateLabel,Document doc) {
		String publishDateStr = null;
		try {
			DocumentContent contentFirstPage = doc.getContent();
			AnnotationSet annotationSet = doc.getAnnotations();
			
			Set annotTypesRequired = new HashSet();
			annotTypesRequired.add(publishDateLabel);
			Set<Annotation> annotationPublishDate = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
			
			if(annotationPublishDate != null) {
				Iterator iterAllPublishPlace = annotationPublishDate.iterator();
				AnnotationPositon publishDatePosition = new AnnotationPositon();
				long lengthPublishDate = 0;
				while((iterAllPublishPlace != null) && iterAllPublishPlace.hasNext()) {
					Annotation publishPlaceAnnotation = (Annotation) iterAllPublishPlace.next();
					
					long startPositionAnnotaion = publishPlaceAnnotation.getStartNode().getOffset().longValue();
					long endPositionAnnotaion = publishPlaceAnnotation.getEndNode().getOffset().longValue();
					//Find annotation is longest.
					if(lengthPublishDate < (endPositionAnnotaion - startPositionAnnotaion)) {
						lengthPublishDate = endPositionAnnotaion - startPositionAnnotaion;
						publishDatePosition.setStartAnnotationPosition(startPositionAnnotaion);
						publishDatePosition.setEndAnnotationPosition(endPositionAnnotaion);
					}
				}
				
				publishDateStr = contentFirstPage.getContent(publishDatePosition.getStartAnnotationPosition(), 
															publishDatePosition.getEndAnnotationPosition()).toString();
				
			}
		} catch (Exception e) {
			
		}
		return publishDateStr;
	}
	
	/**
	 * comment : get content contain longest publisher string.
	 * @param publisherLabel
	 * @param doc
	 * @return
	 * @author Huynh Minh Duc
	 */
	public String getContentOfPublisher(String publisherLabel,Document doc) {
		String publisherStr = null;
		try {
			DocumentContent contentFirstPage = doc.getContent();
			AnnotationSet annotationSet = doc.getAnnotations();
			
			Set annotTypesRequired = new HashSet();
			annotTypesRequired.add(publisherLabel);
			Set<Annotation> annotationPublishPlace = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
			
			if(annotationPublishPlace != null) {
				Iterator iterAllPublishPlace = annotationPublishPlace.iterator();
				AnnotationPositon publishPlacePosition = new AnnotationPositon();
				long lengthPublishPlace = 0;
				while((iterAllPublishPlace != null) && iterAllPublishPlace.hasNext()) {
					Annotation publishPlaceAnnotation = (Annotation) iterAllPublishPlace.next();
					
					long startPositionAnnotaion = publishPlaceAnnotation.getStartNode().getOffset().longValue();
					long endPositionAnnotaion = publishPlaceAnnotation.getEndNode().getOffset().longValue();
					//Find annotation is longest.
					if(lengthPublishPlace < (endPositionAnnotaion - startPositionAnnotaion)) {
						lengthPublishPlace = endPositionAnnotaion - startPositionAnnotaion;
						publishPlacePosition.setStartAnnotationPosition(startPositionAnnotaion);
						publishPlacePosition.setEndAnnotationPosition(endPositionAnnotaion);
					}
				}
				
				publisherStr = contentFirstPage.getContent(publishPlacePosition.getStartAnnotationPosition(), 
															publishPlacePosition.getEndAnnotationPosition()).toString();
				
			}
			
		} catch (Exception e) {
			
		}
		return publisherStr;
	}
	
	public ArrayList<PaperObject> getReferencesListOfDocument(String referenceLabel,Document document) {
		ArrayList<PaperObject> referencesList = new ArrayList<PaperObject>();
		try {
			DocumentContent content = document.getContent();
			
			// get all separate lines
			AnnotationSet annotationSet = document.getAnnotations();
			Set annotTypesRequired = new HashSet();
			annotTypesRequired.add(IDRSApplicationConst.REFERENCE_BREAK_1);
			Set<Annotation> annotationReferenceBreak_1 = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
			
			// get all Affiliation & Email Lines
			annotTypesRequired = new HashSet();
			annotTypesRequired.add(IDRSApplicationConst.REFERENCE_BREAK_2);		
			Set<Annotation> annotationReferenceBreak_2 = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
			
			// get all Affiliation & Email Lines
			annotTypesRequired = new HashSet();
			annotTypesRequired.add(IDRSApplicationConst.REFERENCE_BREAK_3);		
			Set<Annotation> annotationReferenceBreak_3 = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
			
			annotTypesRequired = new HashSet();
			annotTypesRequired.add(IDRSApplicationConst.REFERENCE_BREAK_4);		
			Set<Annotation> annotationReferenceBreak_4 = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
			
			Corpus referenceListCorpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
			
			if(annotationReferenceBreak_1.size() > annotationReferenceBreak_2.size()) {
				if(annotationReferenceBreak_1.size() > annotationReferenceBreak_3.size()){
					if(annotationReferenceBreak_1.size() > annotationReferenceBreak_4.size()){
						//do break 1.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_1.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}else {
						//do break 4.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_4.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}
				}else 
					if(annotationReferenceBreak_3.size() > annotationReferenceBreak_4.size()){
					//do break 3.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_3.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}else {
						//do break 4.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_4.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}
			}else 
				if(annotationReferenceBreak_2.size() > annotationReferenceBreak_3.size()){
					if(annotationReferenceBreak_2.size() > annotationReferenceBreak_4.size()){
						//do break 2.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_2.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}else {
						//do break 4.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_4.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}
				}else 
					if(annotationReferenceBreak_3.size() > annotationReferenceBreak_4.size()){
						// do break 3.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_3.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}
							//referencesList.add(paperObject);
						}
					}else {
						//do break 4.
						SortedAnnotationList sortedAnnotations = new SortedAnnotationList();
						Iterator it = annotationReferenceBreak_4.iterator();
						Annotation currAnnot;
						while(it.hasNext()) {
							currAnnot = (Annotation) it.next();
							sortedAnnotations.addSortedExclusive(currAnnot);
						}
						for(int i = 0; i < sortedAnnotations.size(); i++) {
							
							currAnnot = (Annotation) sortedAnnotations.get(i);
							Annotation nextAnnot = null;
							if ((i + 1) != sortedAnnotations.size()) {
								nextAnnot = (Annotation) sortedAnnotations.get(i + 1);
							}
							
							if(nextAnnot != null) {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										nextAnnot.getStartNode().getOffset().longValue()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}else {
								String contentReference = content.getContent(currAnnot.getEndNode().getOffset().longValue(), 
										content.size()).toString();
								Document refDoc = FactoryUtil.createDocument(contentReference);
								referenceListCorpus.add(refDoc);
							}							
						}
					}
			removeAnniePlugin();
			setANNIE(4);
			initAnniePlugIn();
			Out.prln("=========================");
			Out.prln("Set new ANNIE : Annie 4 ");			
			Out.prln("idrs_MetadataRef.");
			Out.prln("idrs_PublishYearRef.");
			Out.prln("idrs_AuthorRef.");			
			Out.prln("=========================");
			annieController.setCorpus(referenceListCorpus);
			annieController.execute(); 
			Iterator iter = referenceListCorpus.iterator();
			
			while(iter.hasNext() && iter != null) {
				PaperObject paperObject = new PaperObject();
				Document refDoc = (Document) iter.next();				
				content = refDoc.getContent();
				paperObject.setContent(content.toString());
				
				//title : 
				AnnotationPositon titleRefPosition = getAnnotationPosition("ti", refDoc);
				paperObject.setTitle(content.getContent(titleRefPosition.getStartAnnotationPosition(), titleRefPosition.getEndAnnotationPosition()).toString());
				
				//Author :
				ArrayList<Author> authorList = new ArrayList<Author>();
				AnnotationSet annotationSetAuthor = refDoc.getAnnotations();
				Set annotTypesRequiredAuthor = new HashSet();
				
				annotTypesRequiredAuthor.add(IDRSApplicationConst.AUTHOR2);		
				Set<Annotation> annotation =
					new HashSet<Annotation>(annotationSetAuthor.get(annotTypesRequiredAuthor));
				if(annotation.size() != 0) {
					Iterator it = annotation.iterator();
					Annotation currAnnot;
					
					while(it != null && it.hasNext()) {
						currAnnot = (Annotation) it.next();
						Author author = new Author();
						long startPosition = currAnnot.getStartNode().getOffset().longValue();
						long endPosition = currAnnot.getEndNode().getOffset().longValue();
						author.setName(content.getContent(startPosition, endPosition).toString());
						authorList.add(author);
					}
					paperObject.setAuthor(authorList);
				}
				
				//Publish number :
				AnnotationPositon publishNumberRefPosition = getAnnotationPosition(IDRSApplicationConst.PUBLISH_NUMBER, refDoc);
				paperObject.setYearPublish(content.getContent(publishNumberRefPosition.getStartAnnotationPosition(), publishNumberRefPosition.getEndAnnotationPosition()).toString());
				
				referencesList.add(paperObject);
			}
			
			//unload persistent document from memory.
			for (int i = 0; i < referenceListCorpus.size(); i++) {
				Factory.deleteResource((Document) referenceListCorpus.get(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return referencesList;
	}
	
	/**
	 * 
	 * @param document
	 * @return
	 * @author Huynh Minh Duc
	 */
	public ArrayList<AnnotationPositon> getLineAnnotationContainAuthor(Document document) {
		ArrayList<AnnotationPositon> authorLinesPosition = new ArrayList<AnnotationPositon>();
		
		// get all separate lines
		AnnotationSet annotationSet = document.getAnnotations();
		Set annotTypesRequired = new HashSet();
		annotTypesRequired.add(IDRSApplicationConst.LINE);
		Set<Annotation> annotationAllLines = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		
		// get all Affiliation & Email Lines
		annotTypesRequired = new HashSet();
		annotTypesRequired.add(IDRSApplicationConst.LINE_AFFILIATION);	
		annotTypesRequired.add(IDRSApplicationConst.LINE_EMAIL);
		Set<Annotation> annotationAffAndEmailLines = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		
		if (annotationAllLines != null) {
			Iterator iterAllLines = annotationAllLines.iterator();
			while((iterAllLines != null) && iterAllLines.hasNext()) {
				Annotation generalLineAnnotation = (Annotation) iterAllLines.next();
				boolean found = false;
				if (annotationAffAndEmailLines != null) {
					Iterator iterAffEmail = annotationAffAndEmailLines.iterator();
					while((iterAffEmail != null) && iterAffEmail.hasNext()) {
						Annotation affEmailLineAnnotation = (Annotation) iterAffEmail.next(); 
						if ((generalLineAnnotation.getStartNode().getOffset() == affEmailLineAnnotation.getStartNode().getOffset()) ||
						(generalLineAnnotation.getEndNode().getOffset() == affEmailLineAnnotation.getEndNode().getOffset())) {
							found = true;
							break;
						}
					}
					
					// found = false --> Line is the author line
					if (found == false) {
						AnnotationPositon authorLinePos = new AnnotationPositon();
						authorLinePos.setStartAnnotationPosition(generalLineAnnotation.getStartNode().getOffset());
						authorLinePos.setEndAnnotationPosition(generalLineAnnotation.getEndNode().getOffset());
						authorLinesPosition.add(authorLinePos);
					}
					
				}
				
			}
		}
		
		return authorLinesPosition;
	}
	
	/**
	 * 
	 * @author : Huynh Minh Duc
	 * Aug 16, 2010
	 * @param document
	 * @return
	 */
	public String getEmailContent(Document document) {
		StringBuffer emailContent = new StringBuffer();
		Set annotTypesRequired = new HashSet();
		
		DocumentContent content = document.getContent();
		AnnotationSet annotationSet = document.getAnnotations();
		annotTypesRequired.add(IDRSApplicationConst.EMAIL);
		
		Set<Annotation> annotation =
			new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		if(annotation.size() != 0) {
			Iterator it = annotation.iterator();
			Annotation currAnnot;
			try {
				
				while(it != null && it.hasNext()) {
					currAnnot = (Annotation) it.next();
					FeatureMap featureMap = currAnnot.getFeatures();
					emailContent.append(featureMap.get("string").toString());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		annotTypesRequired.clear();
		annotTypesRequired.add(IDRSApplicationConst.ADDRESS);
		annotation = new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		if(annotation.size() != 0) {
			if(emailContent.length()>0){
				emailContent.append("\n");
			}
			Iterator it = annotation.iterator();
			Annotation currAnnot;
			try {
				while(it != null && it.hasNext()) {
					currAnnot = (Annotation) it.next();
					FeatureMap featureMap = currAnnot.getFeatures();
					String kind = featureMap.get("kind").toString();
					if(kind.equals("email")){
						emailContent.append(content.getContent(currAnnot.getStartNode().getOffset(), currAnnot.getEndNode().getOffset()).toString());
						if(it.hasNext()){
							emailContent.append("\n");
						}
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return emailContent.toString();
	}
	
	/**
	 * 
	 * @author : Huynh Minh Duc
	 * Aug 16, 2010
	 * @param document
	 * @return
	 */
	public String getAffiliationContent(Document document) {
		StringBuffer affiliationContent = new StringBuffer();
		Set annotTypesRequired = new HashSet();
		
		DocumentContent content = document.getContent();
		AnnotationSet annotationSet = document.getAnnotations();
		annotTypesRequired.add(IDRSApplicationConst.LINE_AFFILIATION);		
		Set<Annotation> annotation =
			new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		if(annotation.size() != 0) {
			Iterator it = annotation.iterator();
			Annotation currAnnot;
			try {
				
				while(it != null && it.hasNext()) {
					currAnnot = (Annotation) it.next();
					long startPosition = currAnnot.getStartNode().getOffset().longValue();
					long endPosition = currAnnot.getEndNode().getOffset().longValue();
					affiliationContent.append(content.getContent(startPosition, endPosition));
					if(it.hasNext()){
						affiliationContent.append("\n");
					}					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		return affiliationContent.toString();
	}
	/**
	 * 
	 * @param executedDocument
	 * @return
	 * @throws InvalidOffsetException
	 * @author Huynh Minh Duc
	 */
	public String getContentAboveAbstractKeyWordExceptTitle(Document executedDocument, AnnotationPositon abstractAnnotationPosition, 
																AnnotationPositon titleAnnotationPositon, AnnotationPositon introPosition) {
		String contentString = null;
		try {
			DocumentContent content = executedDocument.getContent();
			if(abstractAnnotationPosition.getEndAnnotationPosition() != abstractAnnotationPosition.getStartAnnotationPosition()) {
				contentString = content.getContent(titleAnnotationPositon.getEndAnnotationPosition(), 
						abstractAnnotationPosition.getStartAnnotationPosition()).toString();
			}else {
				contentString = content.getContent(titleAnnotationPositon.getEndAnnotationPosition(), 
						titleAnnotationPositon.getEndAnnotationPosition() + 200).toString();
			}
		return contentString;
		} catch (Exception e) {
			// TODO: handle exception
			return contentString;
		}
		
	}
	
	
	
	/**
	 * 
	 * @param abstractLabel
	 * @param document
	 * @return
	 * @author Huynh Minh Duc
	 */
	public AnnotationPositon getAnnotationPosition(String annotationLabel, Document document) {
		AnnotationPositon annotaionPosition = new AnnotationPositon();
		annotaionPosition.setEndAnnotationPosition(0);
		annotaionPosition.setStartAnnotationPosition(0);
		
		AnnotationSet annotationSet = document.getAnnotations();
		Set annotTypesRequired = new HashSet();
		
		annotTypesRequired.add(annotationLabel);		
		Set<Annotation> annotation =
			new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		if(annotation.size() != 0) {
			Iterator it = annotation.iterator();
			Annotation currAnnot;
			
			while(it != null && it.hasNext()) {
				currAnnot = (Annotation) it.next();
				long startPosition = currAnnot.getStartNode().getOffset().longValue();
				long endPosition = currAnnot.getEndNode().getOffset().longValue();
				
				annotaionPosition.setEndAnnotationPosition(endPosition);
				annotaionPosition.setStartAnnotationPosition(startPosition);
			}
		}
		
		return annotaionPosition;
	}
	
	/**
	 * 
	 * comment : 
	 * @param abstractLabel
	 * @param document
	 * @return
	 * @author Huynh Minh Duc
	 */
	public AnnotationPositon getPositionOfAnnotationInFirstPage(String abstractLabel, AnnotationPositon firstPagePosition, Document document) {
		AnnotationPositon positionOfAbtractKeyWord = new AnnotationPositon();
		positionOfAbtractKeyWord.setStartAnnotationPosition(firstPagePosition.getEndAnnotationPosition());
		positionOfAbtractKeyWord.setEndAnnotationPosition(firstPagePosition.getEndAnnotationPosition());
		
		AnnotationSet annotationSet = document.getAnnotations();
		Set annotTypesRequired = new HashSet();
		
		annotTypesRequired.add(abstractLabel);		
		Set<Annotation> annotation =
			new HashSet<Annotation>(annotationSet.get(annotTypesRequired));
		if(annotation.size() != 0) {
			Iterator it = annotation.iterator();
			Annotation currAnnot;
			long endFirstPage = firstPagePosition.getEndAnnotationPosition();
			while(it != null && it.hasNext()) {
				currAnnot = (Annotation) it.next();
				long startPositionFromAbtract = currAnnot.getStartNode().getOffset().longValue();
				long endPositionFromAbtract = currAnnot.getEndNode().getOffset().longValue();
				if(startPositionFromAbtract < endFirstPage) {
					if(startPositionFromAbtract < positionOfAbtractKeyWord.getStartAnnotationPosition()) {
						positionOfAbtractKeyWord.setEndAnnotationPosition(endPositionFromAbtract);
						positionOfAbtractKeyWord.setStartAnnotationPosition(startPositionFromAbtract);
					}
				}
			}
		}
		
		return positionOfAbtractKeyWord;
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 * @author Huynh Minh Duc
	 */
	public long getEndPositionOfLastPage(Document doc) {
		
		long endAnnotationPosition = 0;
		int numberMax = 0;
		if(doc.getNamedAnnotationSets() != null) {
			Iterator namedAnnotSetsIter = doc.getNamedAnnotationSets().values().iterator();
			
			while(namedAnnotSetsIter.hasNext()){
				AnnotationSet annotSet = (AnnotationSet) namedAnnotSetsIter.next();			
		          
				Set annotTypesRequired = new HashSet();
				annotTypesRequired.add(IDRSApplicationConst.PAGE);
				Set<Annotation> headingAnnotation =
					new HashSet<Annotation>(annotSet.get(annotTypesRequired));

				Iterator it = headingAnnotation.iterator();
				Annotation currAnnot;
				while(it != null && it.hasNext()) {

					currAnnot = (Annotation) it.next();	        	  
					FeatureMap map = currAnnot.getFeatures();	        	  
					int number = Integer.parseInt(map.get(IDRSApplicationConst.NUMBER).toString());
					if(numberMax < number){
						endAnnotationPosition = currAnnot.getEndNode().getOffset().longValue();
						numberMax = number;
					} 
				}
			}
		}
		
		return endAnnotationPosition;
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 * @author Huynh Minh Duc
	 */
	public long getEndPositionOfFirstPage(Document doc) {
		
		long endAnnotationPosition = 0;
		
		if(doc.getNamedAnnotationSets() != null) {
			Iterator namedAnnotSetsIter = doc.getNamedAnnotationSets().values().iterator();
			
			while(namedAnnotSetsIter.hasNext()){
				AnnotationSet annotSet = (AnnotationSet) namedAnnotSetsIter.next();			
		          
				Set annotTypesRequired = new HashSet();
				annotTypesRequired.add(IDRSApplicationConst.PAGE);
				Set<Annotation> headingAnnotation =
					new HashSet<Annotation>(annotSet.get(annotTypesRequired));

				Iterator it = headingAnnotation.iterator();
				Annotation currAnnot;
				while(it != null && it.hasNext()) {

					currAnnot = (Annotation) it.next();	        	  
					FeatureMap map = currAnnot.getFeatures();	        	  
					int number = Integer.parseInt(map.get(IDRSApplicationConst.NUMBER).toString());
					if(number == 1){
						endAnnotationPosition = currAnnot.getEndNode().getOffset().longValue();
					} 
				}
			}
		}
		
		return endAnnotationPosition;
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 */
	public static boolean IsIncorectParser(Document doc) {
		Iterator namedAnnotSetsIter = doc.getNamedAnnotationSets().values().iterator();
		
		while(namedAnnotSetsIter.hasNext()){
			AnnotationSet annotSet = (AnnotationSet) namedAnnotSetsIter.next();
			DocumentContent content = doc.getContent();
			  
			Set annotTypesRequired = new HashSet();
			annotTypesRequired.add(IDRSApplicationConst.H);		
			annotTypesRequired.add(IDRSApplicationConst.FONT);	
			  
			Set<Annotation> headingAnnotation = new HashSet<Annotation>(annotSet.get(annotTypesRequired));
			Iterator headFontAnootations = headingAnnotation.iterator();
			Annotation currAnnot;
			if (headFontAnootations != null && headFontAnootations.hasNext()){
				while(headFontAnootations.hasNext()) {
					currAnnot = (Annotation) headFontAnootations.next();	 
					FeatureMap featureMap = currAnnot.getFeatures();
					float currFontSize = Float.parseFloat(featureMap.get(IDRSApplicationConst.FONT_SIZE).toString());						
					if(currFontSize < 1) return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * comment : 
	 * @return
	 * @author Huynh Minh Duc
	 */
	public AnnotationPositon getTitlePosition(Document doc, AnnotationPositon abstractAnnotationPosition){
		Iterator namedAnnotSetsIter = doc.getNamedAnnotationSets().values().iterator();
		float maxFontSize = 0;
		AnnotationPositon titlePos = new AnnotationPositon();
		
		while(namedAnnotSetsIter.hasNext()){
			AnnotationSet annotSet = (AnnotationSet) namedAnnotSetsIter.next();
			DocumentContent content = doc.getContent();
			  
			Set annotTypesRequired = new HashSet();
			annotTypesRequired.add(IDRSApplicationConst.H);
			annotTypesRequired.add(IDRSApplicationConst.FONT);
			  
			Set<Annotation> headingAnnotation = new HashSet<Annotation>(annotSet.get(annotTypesRequired));
			  
			Iterator headFontAnootations = headingAnnotation.iterator();
			Annotation currAnnot;
		
			if (headFontAnootations != null && headFontAnootations.hasNext()){
				currAnnot = (Annotation) headFontAnootations.next();
				FeatureMap featureMap = currAnnot.getFeatures();
				float currFontSize = Float.parseFloat(featureMap.get(IDRSApplicationConst.FONT_SIZE).toString());
				
				// check If the position of current annotation that have Maximum_Font_Size before the abstract position  
				if ((currFontSize > maxFontSize) && (currAnnot.getEndNode().getOffset() < abstractAnnotationPosition.getStartAnnotationPosition())) {
					maxFontSize = currFontSize;
					titlePos.setStartAnnotationPosition(currAnnot.getStartNode().getOffset());
					titlePos.setEndAnnotationPosition(currAnnot.getEndNode().getOffset());
				}
				
				while(headFontAnootations.hasNext()) {
					currAnnot = (Annotation) headFontAnootations.next();	 
					featureMap = currAnnot.getFeatures();
					currFontSize = Float.parseFloat(featureMap.get(IDRSApplicationConst.FONT_SIZE).toString());	
					// check If the position of current annotation that have Maximum_Font_Size before the abstract position  
					if ((currFontSize > maxFontSize) && (currAnnot.getEndNode().getOffset() < abstractAnnotationPosition.getStartAnnotationPosition())) {
						maxFontSize = currFontSize;
						titlePos.setStartAnnotationPosition(currAnnot.getStartNode().getOffset());
						titlePos.setEndAnnotationPosition(currAnnot.getEndNode().getOffset());
					}
				}
				return titlePos;
			}
		}

		return null;
	}
	
	
	
	/**
	 * 
	 * @return
	 * @author Huynh Minh Duc
	 */
	public AnnotationSet getAnnotationSet(){
		return annotationSet;
	}
	
	/**
	 * 
	 * comment : 
	 * @param corpus
	 * @author Huynh Minh Duc
	 */
	public void setCorpus(Corpus corpus){
		this.corpus = corpus;
		annieController.setCorpus(corpus);
	}
	/**
	 * 
	 * comment : 
	 * @return
	 * @author Huynh Minh Duc
	 */
	public Corpus getCorpus(){
		return corpus;
	}
}
