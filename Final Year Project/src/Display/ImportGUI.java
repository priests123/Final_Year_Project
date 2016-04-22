package Display;

import importToDB.FormatFile;
import importToDB.ReadInFile;
import importToDB.SQLDatabase;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Display.StatisticsGUI.Actions;
import Display.StatisticsGUI.Names;

public class ImportGUI {
	
	private static String fileToImportLocation = null;

	class Names {
		public static final String IMPORT = "Import";
		public static final String CANCEL = "Cancel";
		public static final String BROWSE = "Browse";
	}
	
	private JButton theBtBrowse = new JButton(Names.BROWSE);
	private JButton theBtImport = new JButton(Names.IMPORT);
	private JButton theBtCancel = new JButton(Names.CANCEL);
	private JLabel browseLabel = new JLabel();
	private Actions theCB = new Actions();
	private JLabel errorMsg = new JLabel();
	private JLabel introMsg = new JLabel();
	private JLabel introMsg2 = new JLabel();
	//private Font font1 = new Font("Arial", Font.PLAIN, 18);
	//private Font font2 = new Font("Arial", Font.PLAIN, 14);
	Container rootWindow;
	
	public ImportGUI(RootPaneContainer rpc) {
		
		Container cp = rpc.getContentPane();
		rootWindow = (Container) rpc;
		cp.setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		
		int H = height/3;
		int W = (width/2) - (width/8);
		
		//System.out.println(H);
		//System.out.println(W);
		
		rootWindow.setSize(W, H);
		rootWindow.setLocation((width / 2 - W/2), (height / 2 - H/2));
		
		
		//browseTextArea.setEditable(false);
		browseLabel.setBounds(280, 160, 289, 30);
		browseLabel.setText("No file selected");
		browseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		browseLabel.setVerticalAlignment(SwingConstants.CENTER);
		browseLabel.setBackground(Color.WHITE);
		browseLabel.setOpaque(true);
		browseLabel.setFont(Global.font1);
		cp.add(browseLabel);
		
		theBtBrowse.setBounds(150, 160, 100, 30);
		theBtBrowse.addActionListener(theCB);
		theBtBrowse.setFont(Global.font2);
		cp.add(theBtBrowse);
		
		theBtImport.setBounds(173, 250, 100, 30);
		theBtImport.addActionListener(theCB);
		theBtImport.setFont(Global.font2);
		cp.add(theBtImport);
		
		theBtCancel.setBounds(446, 250, 100, 30);
		theBtCancel.addActionListener(theCB);
		theBtCancel.setFont(Global.font2);
		cp.add(theBtCancel);
		
		errorMsg.setBounds(60, 95, 580, 30);
		errorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		errorMsg.setVerticalAlignment(SwingConstants.CENTER);
		errorMsg.setForeground(Color.RED);
		errorMsg.setFont(Global.font1);
		cp.add(errorMsg);
		
		//introMsg.setFont(new Font("Arial", Font.PLAIN, 30));
		introMsg.setBounds(60, 20, 580, 30);
		introMsg.setText("Please select a file to upload to the system by clicking the browse button below. Please ");
		//introMsg.setBackground(Color.WHITE);
		introMsg.setHorizontalAlignment(SwingConstants.CENTER);
		//introMsg.setOpaque(true);
		introMsg.setFont(Global.font2);
		cp.add(introMsg);
		introMsg2.setBounds(60, 40, 580, 30);
		introMsg2.setText("ensure that it's a CSV file, it's the correct format and it's not been uploaded before.");
		introMsg2.setHorizontalAlignment(SwingConstants.CENTER);
		//introMsg2.setBackground(Color.WHITE);
		//introMsg2.setOpaque(true);
		introMsg2.setFont(Global.font2);
		cp.add(introMsg2);
		
		
		
		
		rootWindow.setVisible( true );    
		
	}
	
	class Actions implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			String actionIs = ae.getActionCommand();
			errorMsg.setText("");
			if(actionIs.equals(Names.BROWSE)){
				OpenFile of = new OpenFile();
				of.filePicked();
				int fileNameIndex = of.sb.toString().lastIndexOf("\\");
				browseLabel.setText(of.sb.substring(fileNameIndex + 1));
				fileToImportLocation = of.sb.toString();
			}
			
			if(actionIs.equals(Names.IMPORT)){
				if(!(fileToImportLocation == null)){
					if(checkFile.checkCSVType(fileToImportLocation)){
						if(checkFile.checkCSVFormat()){
							if(checkFile.checkNotDuplicate()){						
								String[] results = FormatFile.FormatCSV(checkFile.getFormattedFileLocation());
								rootWindow.setVisible(false);
								Main.displayImportSummaryGUI();
							}else{
								errorMsg.setText("This file has already been uploaded, please select another");
							}
						}else{
							errorMsg.setText("Please ensure the file is correctly formatted");
						}
					}else{
						errorMsg.setText("This file isn't a CSV file, please select one");
					}
				}else{
					errorMsg.setText("Please select a file to import");
				}
			}
			
			if(actionIs.equals(Names.CANCEL)){
				rootWindow.setVisible(false);
				StatisticsGUI.setStatisticsGUIVisible();
			}
			
		}
	}

	
	
	
	
}