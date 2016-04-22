package Display;

import importToDB.ReadInFile;
import importToDB.SQLDatabase;

import java.util.ArrayList;

public class checkFile {
	
	private static String fileToImportLocation = "";
	private static String formattedFileLocation = "";
	

	public static Boolean checkCSVType(String fileToImportLoc){
		fileToImportLocation = fileToImportLoc;
		setFormattedFileLocation();
		String fileType = fileToImportLocation.substring(fileToImportLocation.lastIndexOf(".")+1);
		if(fileType.equals("csv")){
			return true;
		}
		return false;
	}
	
	public static Boolean checkNotDuplicate(){
	
		ArrayList<ArrayList<String>> firstLine = ReadInFile.readFirstLine(formattedFileLocation);
		
		String unitNo = firstLine.get(0).get(0);
		String journeyStart = firstLine.get(0).get(1);
		String stationLeft = firstLine.get(0).get(1);
		//System.out.println(unitNo);
		//System.out.println(journeyStart);
		//System.out.println(stationLeft);
		
		String sql = "SELECT * FROM Journey WHERE Unit_No = '" + unitNo + "' AND Journey_Started_Time_Stamp = '" + journeyStart + "' AND Station_Left_Time_Stamp = '" + stationLeft + "'";
		if(SQLDatabase.isDuplicate(sql)){
			return false;
		} else {
			return true;
		}
	}
	
	public static Boolean checkCSVFormat(){
		ArrayList<ArrayList<String>> firstLine = ReadInFile.readFirstLine(formattedFileLocation);
		//System.out.println(firstLine);
		if(firstLine.get(0).size() == 18){
			ArrayList<Character> unitNocheck = new ArrayList<Character>();
			for(int a = 0; a < firstLine.get(0).get(0).length(); a++){
				unitNocheck.add(firstLine.get(0).get(0).charAt(a));
			}
			if(unitNocheck.size() == 6){
				
				Boolean isNumeric = true;
				for(int b = 0; b < unitNocheck.size(); b++){
					if(!Character.isDigit(unitNocheck.get(b))){
						isNumeric = false;
					}
				}
				if(isNumeric){
					if(firstLine.get(0).get(1).matches("\\d{2}\\/\\d{2}\\/\\d{4}\\s\\d{2}\\:\\d{2}\\:\\d{2}")){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
		}
		}else{
			return false;
		}
		return false;
	}
	
	
	
	
	
	public static void setFormattedFileLocation(){
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
		
		formattedFileLocation = fileLocation;
	}
	
	public static String getFormattedFileLocation(){
		return formattedFileLocation;
	}
	
}
