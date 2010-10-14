package websphinx.workbench;

import websphinx.Crawler;

public class DownloadParameters {
	
	private DownloadParametersEditor downloadParametersEditor;
	private final String DEFAULT_VALUE = "-1";
	private final String DEFAULT_THREADS_VALUE = "4";
	
	private String maxPageSize;
	private String maxThreads;
	private String crawlTimeout;
	private String downloadTimeout;
	
	public DownloadParameters(){
		
		downloadParametersEditor = new DownloadParametersEditor();
		
		this.maxPageSize = DEFAULT_VALUE;
		this.maxThreads = DEFAULT_THREADS_VALUE;
		this.crawlTimeout = DEFAULT_VALUE;
		this.downloadTimeout = DEFAULT_VALUE;		
	}
	
	public void setDownloadParamters(Crawler crawler){
		downloadParametersEditor.maxPageSize.setText(this.getMaxPageSize());
		downloadParametersEditor.maxThreads.setText(this.getMaxThreads());
		downloadParametersEditor.crawlTimeout.setText(this.getCrawlTimeout());
		downloadParametersEditor.downloadTimeout.setText(this.getDownloadTimeout());		
		downloadParametersEditor.useCaches.setState(true);
		downloadParametersEditor.interactive.setState(true);
		downloadParametersEditor.obeyRobotExclusion.setState(false);
		
		crawler.setDownloadParameters(downloadParametersEditor.getDownloadParameters());
	}
	
	public void setMaxPageSize(String value){
		this.maxPageSize = value;
	}
	
	public String getMaxPageSize() {
		return this.maxPageSize;
	}
	
	public void setMaxThreads(String value){
		this.maxThreads = value;
	}
	
	public String getMaxThreads(){
		return this.maxThreads;
	}
	
	public void setCrawlTimeout(String value){
		this.crawlTimeout = value;
	}
	
	public String getCrawlTimeout(){
		return this.crawlTimeout;
	}
	
	public void setDownloadTimeout(String value){
		this.downloadTimeout = value;
	}
	
	public String getDownloadTimeout(){
		return this.downloadTimeout;
	}
}
