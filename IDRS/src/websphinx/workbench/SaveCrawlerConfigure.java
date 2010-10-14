package websphinx.workbench;

import java.awt.Panel;
import java.awt.TextField;

import rcm.awt.PopupDialog;
import tkorg.idrs.gui.main.IDRSResourceBundle;

import websphinx.Action;
import websphinx.Crawler;

public class SaveCrawlerConfigure extends Panel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8971283726655051099L;
	private TextField dirTextField ;
	private Action act =  null;
	private ActionFeatureArgs args;
	
	public SaveCrawlerConfigure(){
		
		dirTextField = new TextField();				
		args = new ActionFeatureArgs();			
	}
	
	public void getDirectoryToSave(Crawler crawler){		
		browse(IDRSResourceBundle.res.getString("save.documents.in.directory"), dirTextField);			
		args.setMirrorDirectory(dirTextField.getText());		
		act = new MirrorAction(args.getMirrorDirectory(), args.getMirrorUseBrowser());		
		crawler.setAction(act);		
		args.setMirrorUseBrowser(false);
	}
	
	public void browse (String title, TextField target) {
        String fn = PopupDialog.askFilename(this, title, target.getText(), false);
        if (fn != null)
            target.setText (fn);
    }  
}
