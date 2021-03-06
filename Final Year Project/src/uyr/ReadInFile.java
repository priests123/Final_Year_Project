package uyr;

import java.io.*;
import java.util.ArrayList;

public class ReadInFile {
	
	//Takes a file location of a CSV and returns an arraylist, rawData, that contains the CSV's data. 
	//Each row in the CSV is it's own arraylist which contains it's data, each row is put in the rawData arraylist, an arraylist of arraylists
	
	public static ArrayList<ArrayList<String>> readInCSV(String fileLocation){
		BufferedReader rawFile = null;
		String rawFileLine = null;
		ArrayList<ArrayList<String>> rawData = new ArrayList<ArrayList<String>>();	
		try {
			rawFile = new BufferedReader(new FileReader(fileLocation));
			while ((rawFileLine = rawFile.readLine()) != null) {
				rawData.add(convertToArrayList(rawFileLine));
			}
		} catch (IOException e) {
			System.out.println("File does not exist");
		} finally {
			try {
				if (rawFile != null)
					rawFile.close();
			} catch (IOException e) {
				System.out.println("Can't close the file");
			}
		}
		return rawData;
		
	}
	
	//Takes in a row from the CSV and returns it as an arraylist
	//Splits each row via the comma and places each data item into a position in a arraylist
	
	public static ArrayList<String> convertToArrayList(String rawLine) {
		ArrayList<String> lineData = new ArrayList<String>();
		if (rawLine != null) {
			String[] splitData = rawLine.split("\\s*,\\s*"); // Regular expression to split CSV
			for (int b = 0; b < splitData.length; b++) {
				if ((splitData[b] != null) || (splitData[b].length() != 0)) {
					lineData.add(splitData[b].trim());
				}
			}
		}
		return lineData;
	}
	
	//Reads in just the first line of the file trying to be imported, this is used to check the file is valid
	public static ArrayList<ArrayList<String>> readFirstLine(String fileLocation){

		ArrayList<ArrayList<String>> rawData = new ArrayList<ArrayList<String>>();
		String rawFileLine = null;
		BufferedReader firstLine = null;
		try {
			firstLine = new BufferedReader(new FileReader(fileLocation));
			rawFileLine = firstLine.readLine();
			rawData.add(convertToArrayList(rawFileLine));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			firstLine.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return rawData;	
	}
	
}