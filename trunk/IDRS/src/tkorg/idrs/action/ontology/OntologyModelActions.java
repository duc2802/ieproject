package tkorg.idrs.action.ontology;

import java.io.File;
import java.net.URI;

import javax.swing.JOptionPane;

import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.gui.ontology.OpenOntologyDialog;
/**
 * 
 * @author DUC
 *
 */
public class OntologyModelActions {
	
	public OntologyModelActions() {
		
	}
	
	public void saveModelToOWLFileAction(){
		OpenOntologyDialog saveDialog = new OpenOntologyDialog(IDRSApplication.idrsJFrame);
		saveDialog.setDialogTitle(IDRSResourceBundle.res.getString("save.ontology"));
		int result = saveDialog.showSaveDialog(IDRSApplication.idrsJFrame);
		
		if(result == OpenOntologyDialog.APPROVE_OPTION){
			try {
				
				File file = saveDialog.getSelectedFile();
				if(file.exists()){
					int response = JOptionPane.showConfirmDialog (null,
				               "Overwrite existing file?","Confirm Overwrite",
				                JOptionPane.OK_CANCEL_OPTION,
				                JOptionPane.QUESTION_MESSAGE);
				    if(response == JOptionPane.OK_OPTION){
				    	OWLModel.owlModel.save(file.toURI());
				    	JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("successful"));
				    }
				}else {
					URI uri = new File(saveDialog.getSelectedFile().getAbsolutePath().concat(".owl")).toURI();
					OWLModel.owlModel.save(uri);
			    	JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("successful"));
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("error.save.ontology"));
			}
		}
	}

	public void openOntologyFromOWLFileAction(URI uri){
		try {
			boolean rs = OWLModel.loadOWLModelFromExistFile(uri);
			if(rs == false){
				JOptionPane.showMessageDialog(IDRSApplication.idrsJFrame, IDRSResourceBundle.res.getString("error.open.ontology"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}