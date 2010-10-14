/**
 * 
 */
package tkorg.idrs.gui.extraction;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


import java.awt.Color;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import tkorg.idrs.gui.main.IDRSApplication;

/**
 * @author DUC
 *
 */
public class TooltipDialog extends JDialog {

	static TooltipDialog tip = new TooltipDialog();
	static JTextArea content = new JTextArea();
	public TooltipDialog() {
		super(IDRSApplication.idrsJFrame);
	}
	static
	{
		//tip.setUndecorated(true);
		
		tip.getContentPane().setBackground(Color.yellow);  // or whatever it is
		tip.getContentPane().setLayout(new BorderLayout());
		tip.getContentPane().add(content);
		tip.setModal(false);
		tip.setTitle("Detail...");
		
		content.setBorder(new LineBorder(Color.BLACK));
		content.setAutoscrolls(true);
		content.setLineWrap(true);
		content.setSize(400, 400);
		content.setBackground(new Color(238, 238, 238));  // just in case
	}
	public static void showToolTip(Point locationOnScreen, String htmlText)
	{
		if (htmlText == null)
			tip.setVisible(false);
		else
		{
			content.setText(htmlText);
			content.setEditable(false);
		
			tip.pack();  // resize around the new label
			tip.pack();  // resize around the new label
			tip.setLocation(locationOnScreen); 
			tip.setVisible(true);
		}
	}
	public static void hideToolTip()
	{
		tip.setVisible(false);
	}
}
