package tkorg.idrs.action.ontology;


import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeModel;

import org.apache.tools.ant.taskdefs.optional.jlink.JlinkTask;

import edu.stanford.smi.protegex.owl.model.OWLNamedClass;

import tkorg.idrs.core.ontology.OWLMyClass;
import tkorg.idrs.core.ontology.OWLModel;
import tkorg.idrs.core.ontology.OWLProperty;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.ontology.ClassPanel;
import tkorg.idrs.gui.ontology.LinePanel;
import tkorg.idrs.properties.files.GUIProperties;
/**
 * 
 * @author DUC
 *
 */
public class ClassPanelAction {
	
	public static ArrayList < OWLMyClass > classesArrayList = null;
	public static String namespacePrefix = "";
	public static int maxRank = 0;
	public static String nameClass = "";
	public static ArrayList < String > checkedSuperClassesArrayList;
	public static ArrayList < String > checkedDisjontClassesArrayList;
	public static ArrayList < String > unCheckedSuperClassesArrayList;
	public static ArrayList < String > unCheckedDisjontClassesArrayList;
	
	public ClassPanelAction(){
		classesArrayList = new ArrayList < OWLMyClass >();
		checkedSuperClassesArrayList = new ArrayList < String >();
		checkedDisjontClassesArrayList = new ArrayList < String >();
		unCheckedDisjontClassesArrayList = new ArrayList < String >();
		unCheckedSuperClassesArrayList = new ArrayList < String >();
	}
	
	public static OWLMyClass getMyClassByName(String nameClass) {
		OWLMyClass myClass = new OWLMyClass();
		
		for (int i = 0; i < classesArrayList.size(); i++) {
			String name = nameClass;
			if (nameClass.equals(classesArrayList.get(i).getName())) {
				myClass = classesArrayList.get(i);
			}
		}
		
		return myClass;
	}
	
	public static ArrayList < String > getNameSuperClassesByClass(String nameClass) {
		ArrayList < String > classes = new ArrayList < String >();
		
		OWLMyClass myClass = getMyClassByName(nameClass);
		for (int i = 0; i < myClass.getOwlSuperClassesArrayList().size(); i++) {
			String name = convertName(myClass.getOwlSuperClassesArrayList().get(i).getBrowserText());
			classes.add(name);
		}
		
		return classes;
	}
	
	public static ArrayList < String > getNameDisjontClassesByClass(String nameClass) {
		ArrayList < String > classes = new ArrayList < String >();
		
		OWLMyClass myClass = getMyClassByName(nameClass);
		for (int i = 0; i < myClass.getOwlDisjontClassesArrayList().size(); i++) {
			String name = convertName(myClass.getOwlDisjontClassesArrayList().get(i).getBrowserText());
			classes.add(name);
		}
		
		return classes;
	}
	
	public static String convertName(String owlName) {
		String name = "";
		name = owlName.substring(owlName.indexOf(":") + 1);
		
		return name;
	}
	
	public boolean deleteSuperClass(String cls, String superCls){
		try {
			OWLNamedClass selectedClass = OWLModel.owlModel.getOWLNamedClass(cls);
			OWLNamedClass superClass = OWLModel.owlModel.getOWLNamedClass(superCls);			
			selectedClass.removeSuperclass(superClass);
			return true;
		} catch (Exception e) {
			return false;	
		}
			
	}	
	
	/**
	 * 
	 * @param cls
	 * @param superCls
	 * @return
	 * @author Huynh Minh Duc
	 */
	public boolean addSuperClass(String cls, String superCls){
		try {
			OWLNamedClass selectedClass = OWLModel.owlModel.getOWLNamedClass(cls);
			OWLNamedClass superClass = OWLModel.owlModel.getOWLNamedClass(superCls);			
			selectedClass.addSuperclass(superClass);			
			return true;			
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 
	 * @param nameClass
	 * @return
	 * @author Huynh Minh Duc
	 */
	public boolean deleteClass(String nameClass){
		try {
			OWLNamedClass deleteClass = OWLModel.owlModel.getOWLNamedClass(nameClass);
			OWLModel.owlModel.deleteCls(deleteClass);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	/**
	 * 
	 * @param nameSuperClass
	 * @param nameNewClass
	 */
	public void createNewClass(String nameSuperClass, String nameNewClass){
		try {
			OWLNamedClass superClass = OWLModel.owlModel.getOWLNamedClass(nameSuperClass);
			OWLModel.owlModel.createOWLNamedSubclass(nameNewClass, superClass);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}
	
	/**
	 * 
	 * 
	 * @param classJTree
	 */
	public DefaultListModel viewDisjointClassToListModel(String nameClass){
		
		OWLNamedClass cls = OWLModel.owlModel.getOWLNamedClass(nameClass);

		DefaultListModel listModel = new DefaultListModel();
		if(cls != null){
			Collection disjointClasses = cls.getDisjointClasses();
	
			for (Iterator it = disjointClasses.iterator(); it.hasNext();) {
		        OWLNamedClass classes = (OWLNamedClass) it.next();
		        
		        LinePanel panel = new LinePanel(classes.getBrowserText());
		      
		        listModel.addElement(panel);
			}
		}
		return listModel;
	}
//	
//	/**
//	 * 
//	 * @param classJTree
//	 * @author Huynh Minh Duc
//	 */
//	public void viewTreeClassModelAction(final JTree classJTree){
//		
//		OWLNamedClass nodeThingClass = OWLModel.owlModel.getOWLThingClass();
//
////		Collection classes = OWLModel.owlModel.getUserDefinedOWLNamedClasses();
////		for (Iterator it = classes.iterator(); it.hasNext();) {
////			OWLNamedClass owlClass = (OWLNamedClass) it.next();
////			namespacePrefix = owlClass.getNamespacePrefix();
////			OWLMyClass thingClass = new OWLMyClass();
////			thingClass.setAllValuesByClass(owlClass);
////			classesArrayList.add(thingClass);
////		}
//		
//		DefaultMutableTreeNode nodeThing = new DefaultMutableTreeNode(nodeThingClass.getBrowserText());
//		TreeModel treeModel = null;
//		
//		OWLMyClass owlClass = new OWLMyClass();
//		Collection collection = owlClass.getSubClsOfSelectedCls(nodeThingClass.getBrowserText());
//		convertOWLCollectionClassToTreeNode(collection, nodeThing);
//		
//		//THU NGHIEM.
//		System.out.println(classesArrayList.size());
//		for (int i = 0; i < classesArrayList.size(); i++) {
//			System.out.println(classesArrayList.get(i).getName());
//		}
//		
//		treeModel = new DefaultTreeModel(nodeThing);
//		classJTree.setModel(treeModel);
//		classJTree.setEnabled(true);
//		classJTree.putClientProperty("JTree.lineStyle", "None");
//		classJTree.addTreeSelectionListener(new TreeSelectionListener(){
//
//			@Override
//			public void valueChanged(TreeSelectionEvent arg0) {
//				DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode)classJTree.getLastSelectedPathComponent();
//				if(nodeSelected != null){
//					if(nodeSelected.getChildCount() > 0){						
//						nodeSelected.removeAllChildren();
//						OWLMyClass owlClass = new OWLMyClass();
//						Collection collection = owlClass.getSubClsOfSelectedCls(nodeSelected.toString());
//						convertOWLCollectionClassToTreeNode(collection, nodeSelected);
//					}
//					ClassPanel.superClassList.setModel(viewSuperClassToListModel(nodeSelected.toString()));
//					ClassPanel.disjointClassList.setModel(viewDisjointClassToListModel(nodeSelected.toString()));
//				}
//			}
//		});
//		classJTree.addTreeWillExpandListener(new TreeWillExpandListener(){
//
//			@Override
//			public void treeWillCollapse(TreeExpansionEvent arg0)
//					throws ExpandVetoException {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void treeWillExpand(TreeExpansionEvent arg0)
//					throws ExpandVetoException {
//				DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode)arg0.getPath().getLastPathComponent();
//				if(nodeSelected.getChildCount() > 0){						
//					nodeSelected.removeAllChildren();
//					OWLMyClass owlClass = new OWLMyClass();
//					Collection collection = owlClass.getSubClsOfSelectedCls(nodeSelected.toString());
//					convertOWLCollectionClassToTreeNode(collection, nodeSelected);
//				}
//			}
//			
//		});
//	}
	
	public void convertOWLCollectionClassToTreeNode(Collection collection, DefaultMutableTreeNode nodeSelected){
		
		for (Iterator it = collection.iterator(); it.hasNext();){	
			try {
				OWLNamedClass cls = (OWLNamedClass) it.next();
				if(!cls.isSystem()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(cls.getBrowserText());
					if(cls.getSubclassCount() != 0){
						DefaultMutableTreeNode subNode = new DefaultMutableTreeNode();
						node.add(subNode);
					}
					nodeSelected.add(node);
				}
			} catch (Exception e) {}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public TreeModel viewClassModel(){
		
		OWLNamedClass nodeThingClass = OWLModel.owlModel.getOWLThingClass();
		DefaultMutableTreeNode nodeThing = new DefaultMutableTreeNode(nodeThingClass.getBrowserText());
		TreeModel treeModel = null;
		getSubClass(nodeThingClass, nodeThing);
		treeModel = new DefaultTreeModel(nodeThing);
		return treeModel;

	}
	
	/**
	 * 
	 * @param classJTree
	 */
	public void viewClassModel(final JTree classJTree){
		
		OWLNamedClass nodeThingClass = OWLModel.owlModel.getOWLThingClass();
		DefaultMutableTreeNode nodeThing = new DefaultMutableTreeNode(nodeThingClass.getBrowserText());
		TreeModel treeModel = null;
		
		getSubClass(nodeThingClass, nodeThing);
		//if(nodeThingClass.getSubclassCount() == 0) JOptionPane.showMessageDialog(null, "kdflsk");
		
	    treeModel = new DefaultTreeModel(nodeThing);
		classJTree.setModel(treeModel);
		classJTree.setEnabled(true);
		classJTree.putClientProperty("JTree.lineStyle", "None");
		
		classJTree.addTreeSelectionListener(new TreeSelectionListener(){

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				
				if(classJTree.getLastSelectedPathComponent() != null){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)classJTree.getLastSelectedPathComponent();
					
					Font displayFont = new Font("Serif", Font.ITALIC, 20);
					DefaultListCellRenderer render = new DefaultListCellRenderer();
					render.setFont(displayFont);
//					ClassPanel.superClassList.setModel(viewSuperClassToListModel(node.toString()));
					ClassPanel.disjointClassList.setModel(viewDisjointClassToListModel(node.toString()));
				}
			}
			
		});
	}
	
	/**
	 * 
	 * @param cls
	 * @param nodeThing
	 */
	public void getSubClass(OWLNamedClass nodeThingClass, DefaultMutableTreeNode nodeThing){
		//
		
		Collection classes = nodeThingClass.getSubclasses(false);
		
		for (Iterator it = classes.iterator(); it.hasNext();){
			try {
				OWLNamedClass cls = (OWLNamedClass) it.next();
				if(!cls.isSystem()){
					DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(cls.getBrowserText());
					getSubClass(cls, newNode);
					nodeThing.add(newNode);
				}
			} catch (Exception e) {}
		}
		
	}  
	
	public static void addClassesIntoArrayListByClass(OWLMyClass myClass){
		Collection classes = myClass.getOwlNamedClass().getSubclasses(false);
		int rank = myClass.getRank();
		for (Iterator it = classes.iterator(); it.hasNext();){	
			try {
				OWLNamedClass cls = (OWLNamedClass) it.next();
				if(!cls.isSystem()) {
					OWLMyClass nodeClass = new OWLMyClass();
					nodeClass.setAllValuesByClass(cls, rank + 1);
					classesArrayList.add(nodeClass);
					if(cls.getSubclassCount() != 0) {
						addClassesIntoArrayListByClass(nodeClass);
					}
				}
			} catch (Exception e) {}
		}
	}
	
	public static void addNodesByRank(DefaultMutableTreeNode node, int currentRank, OWLMyClass myClass) {
		
		for (int i = 0; i < classesArrayList.size(); i++) {
			if (currentRank == classesArrayList.get(i).getRank() &&
					classesArrayList.get(i).getOwlNamedClass().isSubclassOf(myClass.getOwlNamedClass())) {
				String nodeString = classesArrayList.get(i).getName();
				DefaultMutableTreeNode subNode = new DefaultMutableTreeNode(nodeString);
				if (currentRank < maxRank) {
					addNodesByRank(subNode, currentRank + 1, classesArrayList.get(i));
				}
				node.add(subNode);
			}
		}
	}
	
	public static void convertClassesIntoTree(JTree classTree) {
		DefaultTreeModel treeModel = null;
		DefaultMutableTreeNode nodeThing = null;
		
		for (int i = 0; i < classesArrayList.size(); i++) {
			if (classesArrayList.get(i).getRank() == 0) {
				nodeThing = new DefaultMutableTreeNode(classesArrayList.get(i).getName());
				addNodesByRank(nodeThing, 1, classesArrayList.get(i));
				break;
			}
		}
		
		treeModel = new DefaultTreeModel(nodeThing);
		classTree.setModel(treeModel);
		classTree.setEnabled(true);
	}
	
	public static void setMaxRank() {
		for (int i = 0; i < classesArrayList.size(); i++) {
			if (maxRank < classesArrayList.get(i).getRank()) {
				maxRank = classesArrayList.get(i).getRank();
			}
		}
	}
	
	public static void setAllMyClass() {
		ClassPanel.reset();
		
		//Add Thing into classesArrayList.
		OWLMyClass thingClass = new OWLMyClass();
		int thingRank = 0;
		
		OWLNamedClass owlThingClass = OWLModel.owlModel.getOWLThingClass();
		thingClass.setAllValuesByClass(owlThingClass, thingRank);
		classesArrayList.add(thingClass);
		
		//Add all classes into classesArrayList.
		addClassesIntoArrayListByClass(thingClass);
		namespacePrefix = classesArrayList.get(1).getOwlNamedClass().getNamespacePrefix();
		setMaxRank();
	
		//Convert all classes into Tree.
		convertClassesIntoTree(ClassPanel.classTree);
	}
	
	public static void addOWLMyClassIntoArrayList(String nameMyClass, String nameSubClass) {
		OWLMyClass mySubClass = new OWLMyClass();
		
		OWLNamedClass namedClass = OWLModel.owlModel.createOWLNamedClass(namespacePrefix + ":" + nameSubClass);
		for (int i = 0; i < classesArrayList.size(); i++) {
			if (nameMyClass.equals(classesArrayList.get(i).getName())) {
				mySubClass.addSuperClassIntoArrayList(classesArrayList.get(i).getOwlNamedClass());
				mySubClass.setName(nameSubClass);
				mySubClass.setRank(classesArrayList.get(i).getRank() + 1);
				namedClass.addSuperclass(classesArrayList.get(i).getOwlNamedClass());
				mySubClass.setOwlNamedClass(namedClass);
				setMaxRank();
				classesArrayList.add(mySubClass);
			}
		}
	}
}
























