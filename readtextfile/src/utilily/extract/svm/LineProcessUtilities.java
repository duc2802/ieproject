package utilily.extract.svm;

public class LineProcessUtilities {
	
	/**
	 * 
	 * @param source
	 * @param startTag
	 * @param endTag
	 * @return
	 * @Author : Huynh Minh Duc
	 * @Comment : Get content between startTag with endTag.
	 */
	public static String getSubString(String source, String startTag, String endTag){
		
		int startPointTitleTag = source.indexOf(startTag) + startTag.length();
		int endPointTitleTag = source.indexOf(endTag);	
		if(startPointTitleTag > 0 && endPointTitleTag > 0){
			return source.substring(startPointTitleTag, endPointTitleTag);
		}else {
			return null;
		}
	}
}
