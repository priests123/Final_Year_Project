package RailData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RouteStasticsBetweenStations {
	public static ArrayList<ArrayList<Object>> generateRouteStasticsData(ArrayList<ArrayList<String>> rawRailData){
		ArrayList<ArrayList<Object>> allStops = new ArrayList<ArrayList<Object>>();
		
		for(int p = 0; p < rawRailData.size(); p++){
			if ((rawRailData.get(p).size() == 18) && (rawRailData.get(p).get(17).equals("FIRST"))) {
				String locationFirst = rawRailData.get(p).get(3);
				ArrayList<Object> rawRailDataForRoute = new ArrayList<Object>();
				for(int a = p; a < rawRailData.size(); a++){			
					if(rawRailData.get(a).size() == 18 && (rawRailData.get(a).get(17).equals("LAST"))){
						rawRailDataForRoute.add(rawRailData.get(a).get(3)); //Station name
						rawRailDataForRoute.add(rawRailData.get(a).get(1)); //Date
						rawRailDataForRoute.add(rawRailData.get(a).get(2)); //Start/End
						rawRailDataForRoute.add(locationFirst + " to " + rawRailData.get(a).get(3));
						allStops.add(rawRailDataForRoute);
						break;
					} else {
						rawRailDataForRoute.add(rawRailData.get(a).get(3)); //Station name
						rawRailDataForRoute.add(rawRailData.get(a).get(1)); //Date
						rawRailDataForRoute.add(rawRailData.get(a).get(2)); //Start/End
					}
				}	
			}
		}		
		
		
		
		ArrayList<ArrayList<Object>> routeStatsBetweenStations = new ArrayList<ArrayList<Object>>();
		for(int a = 0; a < allStops.size(); a++){
			ArrayList<Object> routeStats = new ArrayList<Object>();
			routeStats.add(allStops.get(a).get(allStops.get(a).size()-1)); //adding route name
			for(int r = 0; r < allStops.get(a).size()-1; r+=3){
				
				if(r+3 != allStops.get(a).size()-1){
				try{
					routeStats.add(allStops.get(a).get(r) + " to " + allStops.get(a).get(r + 3)); //adding station names
					Date timeStart = Conversions.convertToDate(allStops.get(a).get(r+1).toString());
					Date timeEnd = Conversions.convertToDate(allStops.get(a).get(r+4).toString());
					long timeDifference = timeEnd.getTime() - timeStart.getTime();
					long differenceHours = timeDifference / (60 * 60 * 1000);
					long differenceMinutes = timeDifference / (60 * 1000) % 60;
					Date Time = new Date(timeDifference);
					Time.setHours((int) differenceHours);
					Time.setMinutes((int) differenceMinutes);
					SimpleDateFormat hoursMins = new SimpleDateFormat("HH:mm");
					String journeyTime = hoursMins.format(Time);
					routeStats.add(journeyTime); //add time between stations
				}catch(IndexOutOfBoundsException ex){}
				}
			}
			routeStatsBetweenStations.add(routeStats);
		}
		
		
		
		//System.out.println(routeStatsBetweenStations);
		
		
		return routeStatsBetweenStations;
		
		
		
		
		
		
		
		
	}
	
}
