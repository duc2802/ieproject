package tkorg.idrs.gui.crawler;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import tkorg.idrs.action.crawler.DownloaderAction;
import tkorg.idrs.core.crawler.Downloader;
import tkorg.idrs.gui.main.IDRSApplication;
import tkorg.idrs.gui.main.IDRSResourceBundle;
import websphinx.*;
import websphinx.workbench.Crawling;

//VS4E -- DO NOT REMOVE THIS LINE!
public class CrawlerPanel extends JPanel implements ListSelectionListener, ActionListener, MouseListener{

	private static final long serialVersionUID = 1L;
	
	private JLabel urlJLabel;
	private JTextField uRLJTextField;
	private JButton startJButton;
	private JButton filterJButton;
	private JPanel crawlBorderTitle;
	private JPanel resultBorderTitle;

	private JList resultCrawlJList;

	private JButton exitJButton;

	private JButton downloadJButton;

	private JLabel numberLinkJLabel;

	private JLabel numberDocJLabel;
	
	private ListSelectionModel selectionModel = new DefaultListSelectionModel();
	
	private int hotspot = new  JCheckBox().getPreferredSize().width;
	
	private Crawler crawler;

	private Crawling crawling;
	
	private ArrayList<String> urls = new ArrayList<String>();
	
	public CrawlerPanel() {
		initComponents();
	}
	
	private void initComponents() {
		crawler = new Crawler();
		
		setLayout(new GroupLayout());
		add(getCrawlBorderTitle(), new Constraints(new Bilateral(6, 6, 614), new Leading(6, 73, 10, 10)));
		add(getExitJButton(), new Constraints(new Trailing(12, 110, 10, 10), new Trailing(12, 34, 263, 328)));
		add(getDownloadJButton(), new Constraints(new Trailing(134, 114, 10, 10), new Trailing(12, 34, 263, 328)));
		add(getResultBorderTitle(), new Constraints(new Bilateral(6, 6, 133), new Bilateral(79, 64, 172)));
		add(getNumberLinkJLabel(), new Constraints(new Bilateral(16, 456, 83), new Trailing(12, 114, 263)));
		add(getNumberDocJLabel(), new Constraints(new Leading(227, 178, 10, 10), new Trailing(12, 114, 263)));
		setSize(669, 370);
		
	}

	private JLabel getNumberDocJLabel() {
		if (numberDocJLabel == null) {
			numberDocJLabel = new JLabel();
			numberDocJLabel.setText(IDRSResourceBundle.res.getString("document.number") + " : ");
		}
		return numberDocJLabel;
	}

	private JLabel getNumberLinkJLabel() {
		if (numberLinkJLabel == null) {
			numberLinkJLabel = new JLabel();
			numberLinkJLabel.setText(IDRSResourceBundle.res.getString("links.number") + " : ");
		}
		return numberLinkJLabel;
	}

	private JButton getDownloadJButton() {
		if (downloadJButton == null) {
			downloadJButton = new JButton();
			downloadJButton.setText(IDRSResourceBundle.res.getString("download"));
			downloadJButton.setToolTipText(IDRSResourceBundle.res.getString("download"));
			downloadJButton.setEnabled(false);
			downloadJButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					tkorg.idrs.action.crawler.DownloaderAction dl = new DownloaderAction();
					
					String dir  = dl.DownloadDocument();	
					
					if(crawling.getURL() != null){
						setURL(crawling.getURL());
					
						Downloader down = new Downloader();
						down.download(dir, getURL());
					}
					else
						System.out.println(IDRSResourceBundle.res.getString("empty.list"));
					
				}
			});
		}
		return downloadJButton;
	}

	private JButton getExitJButton() {
		if (exitJButton == null) {
			exitJButton = new JButton();
			exitJButton.setText(IDRSResourceBundle.res.getString("exit"));
			exitJButton.setToolTipText(IDRSResourceBundle.res.getString("exit"));
			exitJButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					IDRSApplication.idrsJFrame.dispose();
				}
			});
		}
		return exitJButton;
	}

	private JPanel getResultBorderTitle() {
		if (resultBorderTitle == null) {
			resultBorderTitle = new JPanel();
			resultBorderTitle.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("result"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			resultBorderTitle.setLayout(new GroupLayout());
			
		}
		return resultBorderTitle;
	}

	private JPanel getCrawlBorderTitle() {
		if (crawlBorderTitle == null) {
			crawlBorderTitle = new JPanel();
			crawlBorderTitle.setBorder(BorderFactory.createTitledBorder(null, IDRSResourceBundle.res.getString("crawling"), TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, new Font("Dialog",
					Font.BOLD, 12), new Color(51, 51, 51)));
			crawlBorderTitle.setLayout(new GroupLayout());
			crawlBorderTitle.add(getFilterJButton(), new Constraints(new Trailing(12, 118, 474, 474), new Leading(0, 12, 12)));
			crawlBorderTitle.add(getStartJButton(), new Constraints(new Trailing(140, 117, 285, 285), new Leading(0, 12, 12)));
			crawlBorderTitle.add(getURLJTextField(), new Constraints(new Bilateral(150, 275, 4), new Leading(3, 28, 10, 10)));
			crawlBorderTitle.add(getUrlJLabel(), new Constraints(new Leading(5, 150, 10, 10), new Leading(12, 12, 12)));
		}
		return crawlBorderTitle;
	}

	private JButton getFilterJButton() {
		if (filterJButton == null) {
			filterJButton = new JButton();
			filterJButton.setText(IDRSResourceBundle.res.getString("filter"));
			filterJButton.setToolTipText(IDRSResourceBundle.res.getString("filter"));
			filterJButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					if(filterJButton.getText() == IDRSResourceBundle.res.getString("filter")){					
						CrawlingFilterDialog filterDialog = new CrawlingFilterDialog(IDRSApplication.idrsJFrame);
						filterDialog.setVisible(true);
					}else{
						crawling.clear();
						numberLinkJLabel.setText(IDRSResourceBundle.res.getString("links.number") + " : 0");
						numberDocJLabel.setText(IDRSResourceBundle.res.getString("document.number") + " : 0");
						startJButton.setText(IDRSResourceBundle.res.getString("start"));
						startJButton.setToolTipText(IDRSResourceBundle.res.getString("start"));
						
						filterJButton.setText(IDRSResourceBundle.res.getString("filter"));
						filterJButton.setToolTipText(IDRSResourceBundle.res.getString("filter"));
						
						uRLJTextField.setText("");
						
						startJButton.setEnabled(true);
					}					
				}
			});
		}
		return filterJButton;
	}

	private JButton getStartJButton() {
		if (startJButton == null) {
			startJButton = new JButton();
			startJButton.setText(IDRSResourceBundle.res.getString("start"));
			startJButton.setToolTipText(IDRSResourceBundle.res.getString("start"));
			startJButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					/**
					 * input data from filter dialog...
					 */
					//crawler = new Crawler();
					if(startJButton.getToolTipText() == IDRSResourceBundle.res.getString("start"))
					{
						crawling = new Crawling(crawler);
						
						if(uRLJTextField.getText().equalsIgnoreCase("")){
							JOptionPane.showMessageDialog(null, IDRSResourceBundle.res.getString("input.url.to.field"));
						}
						else{
							URL urlTempt = null;
							try {
								
								resultBorderTitle.add(crawling.getOutline(), new Constraints(new Bilateral(6, 0, 22), new Bilateral(-4, 0, 22)));
								
								urlTempt = new URL(uRLJTextField.getText());
																	
								startJButton.setText(IDRSResourceBundle.res.getString("stop"));
								startJButton.setToolTipText(IDRSResourceBundle.res.getString("stop"));
								
								filterJButton.setText(IDRSResourceBundle.res.getString("clear"));
								filterJButton.setToolTipText(IDRSResourceBundle.res.getString("clear"));
								
								crawling.setLinksToCrawl(uRLJTextField.getText());
								
								crawling.start(crawler);	
							} catch (MalformedURLException e1) {
							
								JOptionPane.showMessageDialog(null, IDRSResourceBundle.res.getString("url.is.failed") + urlTempt);
							}						
						}
					}
					else{
						crawling.printHashtable();
						downloadJButton.setEnabled(true);	
						
						numberLinkJLabel.setText(IDRSResourceBundle.res.getString("links.number") + " : " + crawling.getLinkNumber());
						numberDocJLabel.setText(IDRSResourceBundle.res.getString("document.number") + " : " + crawling.getDocNumber());
												
						crawling.stop();
						startJButton.setText(IDRSResourceBundle.res.getString("start"));
						startJButton.setToolTipText(IDRSResourceBundle.res.getString("start"));
						startJButton.setEnabled(false);
					}
				}
			});
		}
		return startJButton;
	}

	private JTextField getURLJTextField() {
		if (uRLJTextField == null) {
			uRLJTextField = new JTextField();
		}
		return uRLJTextField;
	}

	private JLabel getUrlJLabel() {
		if (urlJLabel == null) {
			urlJLabel = new JLabel();
			urlJLabel.setText(IDRSResourceBundle.res.getString("url.to.crawl") + ": ");
		}
		return urlJLabel;
	}
	
	public ListSelectionModel getSelectionModel(){
		return selectionModel;
	}

	private void toggleSelectionModel(int index){
		if(index  < 0)
			return ;
		
		if(selectionModel.isSelectedIndex(index))
			selectionModel.removeSelectionInterval(index, index);
		else
			selectionModel.addSelectionInterval(index, index);
		
	}
	
	//Set and get url list
	public void setURL(ArrayList<String> urls){
		this.urls = urls;
	}
	
	public ArrayList<String> getURL() {
		return this.urls;		
	}
	
	//..........MouseListener..............
	public void mouseClicked(MouseEvent me){
		int index = resultCrawlJList.locationToIndex(me.getPoint());
		if(index < 0)
			return;
		if(me.getX() > resultCrawlJList.getCellBounds(index, index).x + hotspot)
			return;
		toggleSelectionModel(index);
	}
	
	public static void updateTextsOfComponents(){
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		resultCrawlJList.repaint(resultCrawlJList.getCellBounds(e.getFirstIndex(), e.getLastIndex()));	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toggleSelectionModel(resultCrawlJList.getSelectedIndex());
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
