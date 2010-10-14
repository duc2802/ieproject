package tkorg.idrs.core.ontology;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import javax.swing.JTree;
import javax.swing.ImageIcon;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

import tkorg.idrs.properties.files.*;


public class OWLClasses {

	public OWLClasses(){
		
	}
	
	/**
	 * 
	 * @param nameSelectedClass
	 * @return
	 * @author Huynh Minh Duc
	 */
	public Collection viewSubClsOfSelectedCls(String nameSelectedClass){
			
			OWLNamedClass selectedClass = OWLModel.owlModel.getOWLNamedClass(nameSelectedClass);
			Collection subClasses = selectedClass.getSubclasses(false);
			
			return subClasses;
		}
	}
