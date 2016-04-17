package Display;

import importToDB.FormatFile;
import importToDB.SQLDatabase;

import java.util.ArrayList;

import javax.swing.*;


public class Main {

	public static void main(String[] args) {
		
		SQLDatabase.OpenConnection();
		
		//String fileLcoation = "C:\\Users\\Michael\\Desktop\\Datasets\\465235.csv";
		//ArrayList<ArrayList<String>> dataForImport = FormatFile.FormatCSV(fileLcoation);
		Main.displayStasticsGUI();
		
	}
	
	public static void displayStasticsGUI() {
		JFrame window = new JFrame();
		window.setTitle("Name");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new StatisticsGUI(window);
	}
	
	public static void displayImportGUI(){
		JFrame window = new JFrame();
		window.setTitle("Import");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ImportGUI(window);
	}
	

}
