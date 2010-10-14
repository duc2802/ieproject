/**
 * Created by: HNTin & Date: May 5, 2010
 */
package tkorg.idrs.action.searchengines;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.AbstractAction;
import javax.swing.event.HyperlinkEvent;

/**
 * @author Huynh Ngoc Tin
 * modifier : tiendv
 *  Cath action For Hyperlink
 */
public class SelectHyperLinkAction  extends AbstractAction {
	
	public String urlString; 
	public SelectHyperLinkAction(String urlStr) {
		urlString = urlStr;
	}
	
	public void actionPerformed(ActionEvent e){ 
        HyperlinkEvent hle = (HyperlinkEvent)e.getSource();       
        Desktop desktop = Desktop.getDesktop();
		try {
			desktop.browse(new URI (urlString));
		}
		catch (URISyntaxException ex) {
			ex.printStackTrace();
		}
		catch (IOException exception) {
			
		}	
	}

}
