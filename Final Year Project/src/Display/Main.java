package Display;

import importToDB.FormatFile;
import importToDB.SQLDatabase;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Main {

	public static void main(String[] args) {
		
		SQLDatabase.OpenConnection();
		
		
		Main.displayStasticsGUI();
		
		
		
		 

		  
		
		
		
		
		
		
	}
	
	public static void displayStasticsGUI() {
		JFrame window = new JFrame();
		window.setTitle("Understanding your routes");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new StatisticsGUI(window);
	}
	
	public static void displayImportGUI(){
		JFrame window = new JFrame();
		window.setTitle("Import");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ImportGUI(window);
	}
	
	public static void displayImportSummaryGUI(){
		JFrame window = new JFrame();
		window.setTitle("Import Summary");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ImportSummaryGUI(window);
	}
	

}
