package tkorg.idrs.action.crawler;

import java.io.*;

import tkorg.idrs.gui.main.IDRSApplication;
import websphinx.workbench.DownloadDocDialog;

public class DownloaderAction implements Runnable{
	private String url;
	private String splitChar = "/"; 
	private String dir;
	public DownloaderAction(){
		
	}
	
	public DownloaderAction(String dir,String url){
		super();
		this.url = url;
		this.dir = dir;
	}
	
	public void download(String dir, String urls) throws IOException {	
		//open connection		
		BufferedInputStream in = new java.io.BufferedInputStream(
				new	java.net.URL(urls).openStream());
		//split file name	
			
		
		String [] a = urls.split(splitChar);		
		int i = a.length - 1;		
			
		FileOutputStream fos = new FileOutputStream(dir + a[i].toString());
		
		BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
		
		byte[] data = new byte[1024];
		int x = 0;
		while ((x = in.read(data, 0, 1024)) >= 0)
		{
			bout.write(data, 0, x);
		}		
		bout.close();
		in.close();
	}

	public String DownloadDocument(){
		String dir = "C:/";
		DownloadDocDialog downloadDocDialog = new DownloadDocDialog();
		int result = downloadDocDialog.showSaveDialog(IDRSApplication.idrsJFrame);
		if(result == downloadDocDialog.APPROVE_OPTION){
			 dir = downloadDocDialog.getSelectedFile().getAbsolutePath() + "\\";
			System.out.println(dir);
		}
		return dir;
	}
	
	@Override
	public void run() {
		try {
			download(dir, url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
