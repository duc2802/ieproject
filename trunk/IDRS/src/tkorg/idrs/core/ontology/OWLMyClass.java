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

import tkorg.idrs.action.ontology.ClassPanelAction;
import tkorg.idrs.properties.files.*;

/**
 * 
 * @author Tran Cong Danh.
 */

public class OWLMyClass {

	private String name = "";
	private OWLNamedClass owlNamedClass = null;
	private ArrayList < OWLNamedClass > owlSuperClassesArrayList = null;
	private ArrayList < OWLNamedClass > owlDisjontClassesArrayList = null;
	private int rank = 0;
	
	public OWLMyClass(){
		owlSuperClassesArrayList = new ArrayList < OWLNamedClass >();
		owlDisjontClassesArrayList = new ArrayList < OWLNamedClass >();
	}
	
	public void setAllValuesByClass(OWLNamedClass owlClass, int rank) {
		name = owlClass.getBrowserText().substring(owlClass.getBrowserText().indexOf(":") + 1);
		this.owlNamedClass = owlClass;
		this.rank = rank;
	
		if (owlClass.getSuperclasses(true).size() != 0) {
			Collection superClasses = owlClass.getSuperclasses(true);
			for (Iterator it = superClasses.iterator(); it.hasNext();) {
				OWLNamedClass nodeClass = (OWLNamedClass) it.next();
				owlSuperClassesArrayList.add(nodeClass);
			}
		}
		
		if (owlClass.getDisjointClasses().size() != 0) {
			Collection disjontClasses = owlClass.getDisjointClasses();
			for (Iterator it = disjontClasses.iterator(); it.hasNext();) {
				OWLNamedClass nodeClass = (OWLNamedClass) it.next();
				owlDisjontClassesArrayList.add(nodeClass);
			}
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OWLNamedClass getOwlNamedClass() {
		return owlNamedClass;
	}

	public void setOwlNamedClass(OWLNamedClass owlNamedClass) {
		this.owlNamedClass = owlNamedClass;
	}

	public ArrayList<OWLNamedClass> getOwlSuperClassesArrayList() {
		return owlSuperClassesArrayList;
	}

	public void setOwlSuperClassesArrayList(
			ArrayList<OWLNamedClass> owlSuperClassesArrayList) {
		this.owlSuperClassesArrayList = owlSuperClassesArrayList;
	}

	public ArrayList<OWLNamedClass> getOwlDisjontClassesArrayList() {
		return owlDisjontClassesArrayList;
	}

	public void setOwlDisjontClassesArrayList(
			ArrayList<OWLNamedClass> owlDisjontClassesArrayList) {
		this.owlDisjontClassesArrayList = owlDisjontClassesArrayList;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public void addSuperClassIntoArrayList(OWLNamedClass superClass) {
		owlSuperClassesArrayList.add(superClass);
	}
	
	public void addDisjontClassIntoArrayList(OWLNamedClass disjontClass) {
		owlDisjontClassesArrayList.add(disjontClass);
	}
}
