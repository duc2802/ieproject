package utilily.extract.svm;

import java.util.ArrayList;

public class StringProcess {

	public static String removeAllTagHeader(String headerText){
		String contentNonTag = headerText.replaceAll(TagConst.TITLE_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.TITLE_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.AUTHOR_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.AUTHOR_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.AFFILIATION_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.AFFILIATION_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.ADDRESS_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.ADDRESS_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.NOTE_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.NOTE_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.EMAIL_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.EMAIL_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.DATE_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.DATE_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.ABSTRACT_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.ABSTRACT_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.INTRO_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.INTRO_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.PHONE_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.PHONE_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.WEB_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.WEB_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.DEGREE_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.DEGREE_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.KEYWORD_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.KEYWORD_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.PUBNUM_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.PUBNUM_TAG_END, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.PAGE_TAG_START, "");
		contentNonTag = contentNonTag.replaceAll(TagConst.PAGE_TAG_END, "");
		return contentNonTag;
	}
	
	public static String[] convertHeaderTextToLines(String headerTextNonTag){				
		String[] lines = headerTextNonTag.split(TagConst.LINE_SEPARATE_JUNCTION);		
		return lines;
	}
}
