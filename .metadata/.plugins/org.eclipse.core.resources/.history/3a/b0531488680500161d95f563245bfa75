package importToDB;

import java.util.ArrayList;
import java.util.HashSet;

public class Stations {

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
		if(uniqueStationsInFile.size() > 0){
			SQLDatabase.addNewStations(uniqueStationsInFile, "INSERT INTO Station (Station_Name) VALUES (?)");
		} else {
			System.out.println("There were no new stations to be added");
		}
		
		return SQLDatabase.selectAllFromStation("SELECT Station_ID, Station_Name FROM Station", 2);
	}
	
}
