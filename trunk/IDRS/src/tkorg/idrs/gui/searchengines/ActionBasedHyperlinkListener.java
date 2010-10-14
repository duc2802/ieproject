package tkorg.idrs.gui.searchengines;
/**
 * author : tiendv
 * Action Listener for Hyperlink
 */
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class ActionBasedHyperlinkListener implements HyperlinkListener{ 
    ActionMap actionMap; 
 
    public ActionBasedHyperlinkListener(ActionMap actionMap){ 
        this.actionMap = actionMap; 
    } 
 
    public void hyperlinkUpdate(HyperlinkEvent e){ 
        if(e.getEventType()!=HyperlinkEvent.EventType.ACTIVATED) 
            return; 
        String href = e.getDescription(); 
        Action action = actionMap.get(href); 
        if(action!=null) 
            action.actionPerformed(new ActionEvent(e, ActionEvent.ACTION_PERFORMED, href)); 
    }    
} 