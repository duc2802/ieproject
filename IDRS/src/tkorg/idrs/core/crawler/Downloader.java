package tkorg.idrs.core.crawler;

import tkorg.idrs.action.crawler.DownloaderAction;
import tkorg.idrs.gui.crawler.CrawlerPanel;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Downloader {
	private ExecutorService threadPool;	
	
	private int numberThread = 1;
	
	public Downloader(){
		threadPool = Executors.newFixedThreadPool(numberThread);
				
	}
	
	public void download(String dir, ArrayList<String> urlList){
		
		for(int i = 0; i < urlList.size(); i++){
			threadPool.execute(new DownloaderAction(dir, urlList.get(i)));
		}
		threadPool.shutdown();
	}
}
