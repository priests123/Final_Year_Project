package importToDB;

import java.util.ArrayList;


public class FormatFile {

		public static ArrayList<ArrayList<String>> FormatCSV(String fileLcoation){
			ArrayList<ArrayList<String>> rawRailDataFile = ReadInFile.readInCSV(fileLcoation);
			ArrayList<ArrayList<String>> formattedFile = null;
			
			for(int a = 0; a < rawRailDataFile.size(); a++){
				for(int b = 0; b < 13; b++){
					rawRailDataFile.get(a).remove(4);
				}
			}
			
			//ArrayList<String> allStations = Stations.addStations(rawRailDataFile);			
			//UnitNumber.addUnitNumber(rawRailDataFile);
			ArrayList<String> allRoutes = Routes.addRoutes(rawRailDataFile);
			
			
			
			
			
			
			
			for(int c = 0; c < rawRailDataFile.size(); c++){
				String journeyStartedTime = null;
				String unitNo = null;
				ArrayList<String> currentRow = null;
				if((rawRailDataFile.get(c).size() == 5) && (rawRailDataFile.get(c).get(4).equals("FIRST"))){
					journeyStartedTime = rawRailDataFile.get(c).get(1);
					unitNo = rawRailDataFile.get(c).get(0);
					//System.out.println(journeyStartedTime);
				}
				
				//currentRow.add(unitNo);
				//currentRow.add(journeyStartedTime);
				//ADD ROUTE ID
				
				
				
				
				
				
				
				if((rawRailDataFile.get(c).size() == 5) && (rawRailDataFile.get(c).get(4).equals("LAST"))){
					
				}else{
					
				}
			}
			
			
			
			return rawRailDataFile;
		}
}
