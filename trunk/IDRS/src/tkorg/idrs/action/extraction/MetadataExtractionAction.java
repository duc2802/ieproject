package tkorg.idrs.action.extraction;

import java.io.File;
import java.net.URI;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import ch.randelshofer.quaqua.JSheet;

import tkorg.idrs.core.extraction.FactoryUtil;
import tkorg.idrs.core.extraction.GateExtractionObject;
import tkorg.idrs.core.extraction.PaperCollection;
import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.gui.extraction.BuildCorpusLeftPanel;
import tkorg.idrs.gui.extraction.InformationExtractionPanel;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.gui.ontology.OpenOntologyDialog;
import tkorg.idrs.utilities.XMLUtility;
import gate.Corpus;
import gate.Document;
import gate.Factory;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.util.Out;


public class MetadataExtractionAction {
	
	public static Corpus corpus = null;
	
	public MetadataExtractionAction() {
		try {
			corpus = (Corpus) Factory.createResource("gate.corpora.CorpusImpl");
			if (BuildCorpusLeftPanel.corpus != null){				
				for (int i=0;i<BuildCorpusLeftPanel.corpus.size(); i++) {
					File file = BuildCorpusLeftPanel.corpus.get(i);
					Document document = FactoryUtil.createDocument(file.toURI().toURL());
					IDRSApplication.idrsStatus.setProcessMessage(file.getName());
					corpus.add(document);
				}				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	
	
	public PaperCollection extractMetadata() throws ExecutionException, ResourceInstantiationException{		
		InformationExtractionPanel.gateExtractionObject.setCorpus(corpus);
		PaperCollection paperCollection = InformationExtractionPanel.gateExtractionObject.execute();
		System.gc();
		return paperCollection;
	}
	
	public static void exportMetadataToXMLFile(PaperCollection paperCollection) {
		OpenOntologyDialog saveDialog = new OpenOntologyDialog(IDRSApplication.idrsJFrame);
		saveDialog.setDialogTitle(IDRSResourceBundle.res.getString("save.xml"));
		int result = saveDialog.showSaveDialog(IDRSApplication.idrsJFrame);
		
		if(result == OpenOntologyDialog.APPROVE_OPTION){
			try {
				
				File file = saveDialog.getSelectedFile();
				//JSheet.showMessageSheet(null, saveDialog.getSelectedFile().getAbsolutePath().concat(".xml"));
				if(file.exists()){
					int response = JOptionPane.showConfirmDialog (null,
				               "Overwrite existing file?","Confirm Overwrite",
				                JOptionPane.OK_CANCEL_OPTION,
				                JOptionPane.QUESTION_MESSAGE);
				    if(response == JOptionPane.OK_OPTION){
				    	org.w3c.dom.Document doc = paperCollection.toXML();
				    	XMLUtility.writeXmlFile(doc, file.getAbsolutePath());
				    	JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("successful"));
				    }
				}else {
					//URI uri = new File(saveDialog.getSelectedFile().getAbsolutePath().concat(".xml")).toURI();
					org.w3c.dom.Document doc = paperCollection.toXML();
			    	XMLUtility.writeXmlFile(doc, saveDialog.getSelectedFile().getAbsolutePath().concat(".xml"));
			    	JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("successful"));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("error.save.ontology"));
			}
		}
	}
}
