package Display;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;

import Display.ImportGUI.Actions;
import Display.StatisticsGUI.Names;


public class ImportSummaryGUI {

	Container rootWindow;
	
	private JTextArea resultsTextArea = new JTextArea();
	private JTextArea resultsTextArea2 = new JTextArea();
	private JButton theBtOk = new JButton(Names.OK);
	private Actions theCB = new Actions();
	
	class Names {
		public static final String OK = "OK";
	}
	
	public ImportSummaryGUI(RootPaneContainer rpc) {

		Container cp = rpc.getContentPane();
		rootWindow = (Container) rpc;
		cp.setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		

		
		int H = height/3 - 80;
		int W = (width/2) - (width/8);
		
		rootWindow.setSize(W, H);
		rootWindow.setLocation((width / 2 - W/2), (height / 2 - H/2));
		
		resultsTextArea.setBounds(30, 30, 320, 140);
		resultsTextArea2.setBounds(350, 30, 320, 140);
		resultsTextArea.setEditable(false);
		resultsTextArea2.setEditable(false);
		ArrayList<String> results = inputResponses.getInputResponses();
		for(int a = 0; a < results.size(); a+=2){
			resultsTextArea.append(results.get(a).toString()+ "\n\n" );
			resultsTextArea2.append(results.get(a+1).toString()+ "\n\n");
		}
		resultsTextArea.setFont(Global.font3);
		resultsTextArea2.setFont(Global.font3);
		cp.add(resultsTextArea);
		cp.add(resultsTextArea2);
		
		theBtOk.setBounds(300, 185, 100, 30);
		theBtOk.setFont(Global.font2);
		theBtOk.addActionListener(theCB);
		cp.add(theBtOk);
		
		rootWindow.setVisible(true);
		
	}
	
	class Actions implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			String actionIs = ae.getActionCommand();
			
			if(actionIs.equals(Names.OK)){
				rootWindow.setVisible(false);
				Main.displayStasticsGUI();
			}
			}
		}
	

	
	

}