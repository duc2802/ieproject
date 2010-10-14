/**
 * 
 */
package tkorg.idrs.utilities;

/**
 * @author DUC
 *
 */
public class StringUtility {
	public StringUtility() {
		
	}
	
	public static String showOverViewString(String inputStr) {
		if(inputStr.length() > NUMBER_CHARACTER_SHOW) {
			StringBuffer outputStr = new StringBuffer();
			outputStr.append(inputStr.substring(0, NUMBER_CHARACTER_SHOW));
			outputStr.append("...");
			return outputStr.toString();
		}
		return inputStr;
	}
	
	public static String removeNewLineCharacter(String s) {
		if(s != null) {
			StringBuffer sfr = new StringBuffer(s);
			return sfr.toString().replaceAll("\n","");
		}else return s;
		
	}

	public static final int NUMBER_CHARACTER_SHOW = 70;
}
