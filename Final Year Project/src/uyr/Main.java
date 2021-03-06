package uyr;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		SQLDatabase.openConnection();
		Main.displayStatisticsGUI();
	}
	
	//Displays the StatisticsGUI
	public static void displayStatisticsGUI() {
		JFrame window = new JFrame();
		window.setTitle("Understanding your routes");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new StatisticsGUI(window);
	}
	
	//Displays the ImportGUI
	public static void displayImportGUI(){
		JFrame window = new JFrame();
		window.setTitle("Import");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ImportGUI(window);
	}
	
	//Displays the ImportSummaryGUI
	public static void displayImportSummaryGUI(){
		JFrame window = new JFrame();
		window.setTitle("Import Summary");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new ImportSummaryGUI(window);
	}
	
}
