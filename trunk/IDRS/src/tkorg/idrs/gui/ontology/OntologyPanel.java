package tkorg.idrs.gui.ontology;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

import tkorg.idrs.gui.main.IDRSResourceBundle;

//VS4E -- DO NOT REMOVE THIS LINE!
public class OntologyPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static ClassPanel classPanel;
	private IndividualPanel individualPanel;
	private static JTabbedPane ontologyTabPanel;
	private PropertiesPanel propertiesPanel;
	private RelationshipsPanel relationshipPanel;

	public OntologyPanel() {
		initComponents();
	}
	
	/**
	 * 
	 * 
	 */
	private void initComponents() {
		setLayout(new GroupLayout());
		add(getOntologyTabPanel(), new Constraints(new Bilateral(0, -19, 68), new Bilateral(2, 0, 47)));
		setSize(412, 330);
	}
	
	/**
	 * 
	 * @return
	 */
	private RelationshipsPanel getRelationshipPanel() {
		if (relationshipPanel == null) {
			relationshipPanel = new RelationshipsPanel();
			relationshipPanel.setVisible(false);
			//relationshipPanel.setLayout(new GroupLayout());
		}
		return relationshipPanel;
	}

	/**
	 * 
	 * @return
	 */
	private PropertiesPanel getPropertiesPanel() {
		if (propertiesPanel == null) {
			propertiesPanel = new PropertiesPanel();
			//propertiesPanel.setLayout(new GroupLayout());
		}
		return propertiesPanel;
	}
	
	/**
	 * 
	 * @return
	 */
	private JTabbedPane getOntologyTabPanel() {
		if (ontologyTabPanel == null) {
			ontologyTabPanel = new JTabbedPane();
			ontologyTabPanel.setBackground(new Color(128, 255, 255));
			ontologyTabPanel.addTab(IDRSResourceBundle.res.getString("class"), getClassPanel());
			ontologyTabPanel.addTab(IDRSResourceBundle.res.getString("individual"), getIndividualPanel());
			ontologyTabPanel.addTab(IDRSResourceBundle.res.getString("properties"), getPropertiesPanel());
			ontologyTabPanel.addTab(IDRSResourceBundle.res.getString("relationship"), getRelationshipPanel());
			
			
		}
		return ontologyTabPanel;
	}
	
	/**
	 * 
	 */
	private IndividualPanel getIndividualPanel() {
		if (individualPanel == null) {
			individualPanel = new IndividualPanel();
			//individualPanel.setLayout(new GroupLayout());
		}
		return individualPanel;
	}
	
	/**
	 * 
	 * @return
	 */
	private ClassPanel getClassPanel() {
		if (classPanel == null) {
			classPanel = new ClassPanel();
			classPanel.setVisible(false);
		}
		return classPanel;
	}
	
	/**
	 * 
	 * 
	 *	@author Huynh Minh Duc
	 */
	public static void updateTextOfComponents(){
		ontologyTabPanel.setTitleAt(0, IDRSResourceBundle.res.getString("class"));
		ontologyTabPanel.setTitleAt(1, IDRSResourceBundle.res.getString("individual"));
		ontologyTabPanel.setTitleAt(2, IDRSResourceBundle.res.getString("properties"));
		ontologyTabPanel.setTitleAt(3, IDRSResourceBundle.res.getString("relationship"));
		
		classPanel.updateTextOfComponents();
	}
}
