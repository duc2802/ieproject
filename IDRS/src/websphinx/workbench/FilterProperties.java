package websphinx.workbench;

public class FilterProperties {
	private int depth;
	private int maxpages;
	private String threads;
	
	private final int DEPTH = 4;
	private final int MAXPAGES = 30;
	private final String THREAD = "4";
	
	private boolean pdfType = true;
	private boolean docType = true;
	private boolean docxType;
	private boolean htmlType = true;
	private boolean xmlType;
	private boolean textType;
	
	public FilterProperties(){
		depth = DEPTH;
		maxpages = MAXPAGES;
		this.threads = THREAD;
	}
	
	public void setCrawlerProperties(){
		
	}
		
	public void setDepth(int depth){
		this.depth = depth;
	}
	
	public int getDepth(){
		return this.depth;
	}
	
	public void setMaxPages(int maxpages){
		this.maxpages = maxpages;
	}
	
	public int getMaxPages(){
		return this.maxpages;
	}
	
	public void setThreads(String threads){
		this.threads = threads;
	}
	
	public String getThreads(){
		return this.threads;
	}
	
	public void setPDFType(boolean pdf){
		this.pdfType = pdf;
	}
	
	public boolean getPDFType(){
		return this.pdfType;
	}
	
	public void setDocType(boolean doc){
		this.docType = doc;
	}
	
	public boolean getDocType(){
		return this.docType;
	}
	
	public void setDocxType(boolean docx){
		this.docxType = docx;
	}
	
	public boolean getDocxType(){
		return this.docxType;
	}
	
	public void setHtmlType(boolean html){
		this.htmlType = html;
	}
	
	public boolean getHtmlType(){
		return this.htmlType;
	}
	
	public void setXmlType(boolean xml){
		this.xmlType = xml;
	}
	
	public boolean getXmlType(){
		return this.xmlType;
	}
	
	public void setTextType(boolean text){
		this.textType = text;
	}
	
	public boolean getTextType(){
		return this.textType;
	}
}
