package tkorg.idrs.gui.ontology;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;



import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.action.ontology.IndividualsPanelAction;
import tkorg.idrs.action.ontology.OntologyModelActions;
import tkorg.idrs.action.ontology.PropertyPanelAction;
import tkorg.idrs.action.ontology.RelationshipsAction;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import tkorg.idrs.properties.files.GUIProperties;

/**
 * @author DUC
 *
 */
public class OntologyToolBar extends JToolBar {	
	
	private static final long serialVersionUID = 1L;
	
	private static JButton newButton = null;
	private static JButton openButton = null;
	private static JButton saveButton = null;
	private static JButton autoImportButton = null;
	private static JButton helpButton = null;
	
	public JFrame idrsJFrame = null;
	
	/**
	 * 
	 */
	public OntologyToolBar(JFrame idrsJFrame){
		super();
		this.idrsJFrame = idrsJFrame;		
		initComponents();
	};
	
	/**
	 * 
	 */
	private void initComponents(){
		
		add(getNewButton());
		add(getOpenButton());
		add(getSaveButton());
		add(getAutoImportButton());
		add(getHelpButton());
	};
	
	/**
	 * 
	 */
	private JButton getNewButton(){
		if(newButton == null) {
			newButton = new JButton();
			newButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.NEW_ONTOLOGY_BUTTON_TOOLBAR)));
			//newButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
			newButton.setBorder(null);
			newButton.setToolTipText(IDRSResourceBundle.res.getString("new.ontology"));
			newButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					newButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.NEW_PRESSED_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					newButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.NEW_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
			});
		}
		return newButton;
	};	
	
	/**
	 * 
	 */
	private JButton getOpenButton() {
		if(openButton == null) {
			openButton = new JButton();
			openButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.OPEN_ONTOLOGY_BUTTON_TOOLBAR)));
			openButton.setBorder(null);
			openButton.setToolTipText(IDRSResourceBundle.res.getString("open.ontology"));
			openButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){	
					
					OntologyModelActions modelAction = new OntologyModelActions();
					IndividualsPanelAction individualAction = new IndividualsPanelAction();
					ClassPanelAction classAction = new ClassPanelAction();
					PropertyPanelAction propertyPanelAction = new PropertyPanelAction();
					
					OpenOntologyDialog openDialog = new OpenOntologyDialog(idrsJFrame);
					
					int result = openDialog.showOpenDialog(idrsJFrame);
					
					if(result == OpenOntologyDialog.APPROVE_OPTION){
						
						URI uri = new File(openDialog.getSelectedFile().getPath()).toURI();								
						modelAction.openOntologyFromOWLFileAction(uri);
						
						ClassPanelAction.setAllMyClass();
						RelationshipsAction.viewRelationships();
						propertyPanelAction.viewPropertiesPanel();
						individualAction.viewIndividualModel(IndividualPanel.individualTree);
						IndividualPanel.resetValues();
					}
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					openButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.OPEN_PRESSED_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					openButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.OPEN_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
			});
		}
		return openButton;
	};
	
	/**
	 * 
	 */
	private JButton getSaveButton(){
		if(saveButton == null) {
			saveButton = new JButton();
			saveButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SAVE_ONTOLOGY_BUTTON_TOOLBAR)));
			//saveButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
			saveButton.setBorder(null);
			saveButton.setToolTipText(IDRSResourceBundle.res.getString("save.ontology"));
			saveButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					OntologyModelActions action = new OntologyModelActions();
					action.saveModelToOWLFileAction();
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					saveButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SAVE_PRESSED_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					saveButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.SAVE_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
			});
		}
		return saveButton;
	};
	
	/**
	 * 
	 */
	private JButton getAutoImportButton(){
		if(autoImportButton == null) {
			autoImportButton = new JButton();
			autoImportButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.AUTO_IMPORT_ONTOLOGY_BUTTON_TOOLBAR)));
			//autoImportButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
			autoImportButton.setBorder(null);
			autoImportButton.setToolTipText(IDRSResourceBundle.res.getString("import.ontology.automatically"));
			autoImportButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					autoImportButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.AUTO_IMPORT_PRESSED_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					autoImportButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.AUTO_IMPORT_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
			});
		}
		return autoImportButton;
	};
	
	/**
	 * 
	 * 
	 */
	private JButton getHelpButton(){
		if(helpButton == null) {
			helpButton = new JButton();
			helpButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_ONTOLOGY_BUTTON_TOOLBAR)));
			//autoImportButton.setPressedIcon(new ImageIcon(getClass().getResource("lkfl")));
			helpButton.setBorder(null);
			helpButton.setToolTipText(IDRSResourceBundle.res.getString("help"));
			helpButton.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					
				};
				
				public void mousePressed(MouseEvent e){
					
				};
				
				public void mouseEntered(MouseEvent e){
					helpButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_PRESSED_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
				public void mouseReleased(MouseEvent e){
					
				};
				
				public void mouseExited(MouseEvent e){
					helpButton.setIcon(new ImageIcon(getClass().getResource(GUIProperties.HELP_ONTOLOGY_BUTTON_TOOLBAR)));
				};
				
			});
		}
		return helpButton;
	};
	
	/**
	 * 
	 * 
	 */
	public static void updateTextOfComponents() { 
		newButton.setToolTipText(IDRSResourceBundle.res.getString("new.ontology"));
		openButton.setToolTipText(IDRSResourceBundle.res.getString("open.ontology"));
		saveButton.setToolTipText(IDRSResourceBundle.res.getString("save.ontology"));
		autoImportButton.setToolTipText(IDRSResourceBundle.res.getString("import.ontology.automatically"));
		helpButton.setToolTipText(IDRSResourceBundle.res.getString("help"));
	}
}
