/**
 * Created by: HNTin & Date: Apr 15, 2010
 */
package tkorg.idrs.gui.main;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Huynh Ngoc Tin
 *
 */
public class IDRSResourceBundle {
	public static ResourceBundle res = null;

	private IDRSResourceBundle() {
	}

	/**
	 * Initialize resource at first time
	 * 
	 * @return resources.
	 */
	public static ResourceBundle getInstance() {
		if (res == null) {
			try {
				res = initResources();
			} catch (MissingResourceException ex) {
				ex.printStackTrace();
				System.exit(0);
			}
		}
		return res;
	}

	/**
	 * Initialize resource for main application
	 * 
	 * @return resource
	 */
	public static ResourceBundle initResources() {
		try {
			if (Locale.getDefault().equals(Locale.US)) 
				res = ResourceBundle.getBundle("tkorg/idrs/properties/files/IDRSResources_EN");
			else 	
				res = ResourceBundle.getBundle("tkorg/idrs/properties/files/IDRSResources_VN");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return res;
	}
}
