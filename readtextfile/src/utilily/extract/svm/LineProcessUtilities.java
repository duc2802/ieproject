package utilily.extract.svm;

import java.util.ArrayList;

import core.extract.svm.Line;

public class LineProcessUtilities {
	
	public LineProcessUtilities(){
		
	}
	
	/**
	 * 
	 * @param contentTag
	 * @param label
	 * @return
	 * @Author : Huynh Minh Duc
	 * @Comment :
	 */
	public static ArrayList<Line> getLineFromContentTag(String contentTag, int label){	
			
		String[] linesUnknownLabel = contentTag.split(TagConst.LINE_SEPARATE_JUNCTION);
		ArrayList<Line> lines = new ArrayList<Line>();
		for (int i = 0; i < linesUnknownLabel.length; i++) {
			if(!linesUnknownLabel[i].trim().isEmpty()){
				Line line = new Line(linesUnknownLabel[i].trim(), label);
				lines.add(line);
			}			
		}
		return lines;
	}
	
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
