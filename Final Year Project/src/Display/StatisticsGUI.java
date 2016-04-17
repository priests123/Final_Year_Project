package Display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class StatisticsGUI {

	class Names {
		public static final String IMPORT = "Import";
	}
	
	//private JLabel label = new JLabel();
	private JButton theBtImport = new JButton(Names.IMPORT);	
	private Actions theCB = new Actions();
	Container rootWindow;
	
	public StatisticsGUI(RootPaneContainer rpc) {
		
		
		Container cp = rpc.getContentPane();
		rootWindow = (Container) rpc;
		cp.setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		
		int H = height - (height / 5);
		int W = width - (width / 5);
		
		rootWindow.setSize(W, H);
		rootWindow.setLocation((width / 2 - W/2), (height / 2 - H/2));
		
		theBtImport.setBounds(W - 170, 30, 120, 40);
		theBtImport.addActionListener(theCB);
		cp.add(theBtImport);
		
		rootWindow.setVisible(true);
		
	}

	class Actions implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
	
			String actionIs = ae.getActionCommand();
			
			if(actionIs.equals(Names.IMPORT)){
				rootWindow.setVisible(false);
				Main.displayImportGUI();
			}
		}
	}
	
	
	
}
