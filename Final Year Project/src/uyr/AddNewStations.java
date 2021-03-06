package uyr;

import java.util.*;

public class AddNewStations {

	//First checks the stations numbers in the file being imported, then only adds new stations to the database
	public static ArrayList<String> addStations(ArrayList<ArrayList<String>> rawRailDataFile){
		HashSet<String> uniqueStationsInFile = new HashSet<String>();
		for(int a = 0; a < rawRailDataFile.size(); a++){
			String currentStation = rawRailDataFile.get(a).get(3);
			if(uniqueStationsInFile.add(currentStation));	
		}
		ArrayList<String> allStations = SQLDatabase.selectAllFromStation("SELECT Station_Name FROM Station", 1);
		for(int b = 0; b < allStations.size(); b++){
			if(uniqueStationsInFile.contains(allStations.get(b))){
				uniqueStationsInFile.remove(allStations.get(b));
			}
		}
		SQLDatabase.addNewStations(uniqueStationsInFile, "INSERT INTO Station (Station_Name) VALUES (?)");
		return SQLDatabase.selectAllFromStation("SELECT Station_ID, Station_Name FROM Station", 2);
	}
	
}
