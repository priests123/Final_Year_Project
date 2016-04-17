package Display;

import importToDB.ReadInFile;
import importToDB.SQLDatabase;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
	private JTextArea browseTextArea = new JTextArea();
	private Actions theCB = new Actions();
	private JLabel errorMsg = new JLabel();
	
	public ImportGUI(RootPaneContainer rpc) {
		
		Container cp = rpc.getContentPane();
		Container rootWindow = (Container) rpc;
		cp.setLayout(null);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int height = screenSize.height;
		int width = screenSize.width;
		
		int H = height/2;
		int W = (width/2) - (width/8);
		
		System.out.println(H);
		System.out.println(W);
		
		rootWindow.setSize(W, H);
		rootWindow.setLocation((width / 2 - W/2), (height / 2 - H/2));
		
		
		browseTextArea.setEditable(false);
		browseTextArea.setBounds(220, 100, 420, 30);
		browseTextArea.setText("No file selected");
		cp.add(browseTextArea);
		
		theBtBrowse.setBounds(60, 100, 100, 30);
		theBtBrowse.addActionListener(theCB);
		cp.add(theBtBrowse);
		
		theBtImport.setBounds(150, 410, 100, 30);
		theBtImport.addActionListener(theCB);
		cp.add(theBtImport);
		
		errorMsg.setBounds(150, 300, 300, 30);
		
		cp.add(errorMsg);
		
		
		
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
				browseTextArea.setText(of.sb.substring(fileNameIndex + 1));
				fileToImportLocation = of.sb.toString();
			}
			
			if(actionIs.equals(Names.IMPORT)){
				if(checkCSVType()){
					if(checkNotDuplicate()){
						System.out.println("import Data");
					}else{
						errorMsg.setText("This file has already been uploaded, please select another");
					}
				}else{
					errorMsg.setText("This file isn't a CSV file, please select one");
				}
			}
			
		}
	}

	public static Boolean checkCSVType(){
		
		String fileType = fileToImportLocation.substring(fileToImportLocation.lastIndexOf(".")+1);
		if(fileType.equals("csv")){
			return true;
		}
		return false;
	}
	
	public static Boolean checkNotDuplicate(){
		ArrayList<Character> fileName = new ArrayList<Character>();
		for(int a = 0; a < fileToImportLocation.length(); a++){
			fileName.add(fileToImportLocation.charAt(a));
			//System.out.println(fileToImportLocation.charAt(a));
		}
		for(int b = 0; b < fileName.size(); b++){
			if(fileName.get(b).equals('\\')){
				fileName.add(b+1, '\\');
				b++;
			}
		}
		String fileLocation = "";
		for(Character s : fileName){
			fileLocation += s;
		}
		//System.out.println(fileLocation);
		
		ArrayList<ArrayList<String>> firstTwoLines = ReadInFile.readFirstLine(fileLocation);
		
		String unitNo = firstTwoLines.get(0).get(0);
		String journeyStart = firstTwoLines.get(0).get(1);
		String stationLeft = firstTwoLines.get(0).get(1);
		
		String sql = "SELECT * FROM Journey WHERE Unit_No = '" + unitNo + "' AND Journey_Started_Time_Stamp = '" + journeyStart + "' AND Station_Left_Time_Stamp = '" + stationLeft + "'";
		if(SQLDatabase.isDuplicate(sql)){
			return false;
		} else {
			return true;
		}
	}
	
	
}
