package RailData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class Main {

	public static void main(String[] args) {

		String fileLcoation = "C:\\Users\\Michael\\Desktop\\Spark\\DataSets\\465245-weekly-routes.txt";
		ArrayList<ArrayList<String>> railData = ReadInFile.readInCSV(fileLcoation);
		ArrayList<ArrayList<Object>> allRoutes = RailDataToRoute.convertRailDataToRoutes(railData);
		ArrayList<ArrayList<Object>> routeStasticsData = RouteStastics.generateRouteStasticsData(allRoutes);
		ArrayList<ArrayList<Object>> routeStatistics = RouteStastics.generateRouteStatistics(routeStasticsData);
		//PrintOut.printRouteStatistics(routeStatistics);
		
		
		
		System.out.printf("%-45s%-20s%-20s%-20s%-20s%-20s\n", "Route", "Day of Week", "Count", "Average", "Minimum", "Maximum");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("E");
		for(int c = 0; c < routeStasticsData.size(); c++)
		{
			ArrayList<ArrayList<Object>> routeStatisticsDays = new ArrayList<ArrayList<Object>>();
			ArrayList<String> listOfDays = new ArrayList<String>();
			for(int a = 3; a < routeStasticsData.get(c).size(); a = a + 3)
			{
				ArrayList<Object> dayDetails = new ArrayList<Object>();
				Date date = (Date) routeStasticsData.get(c).get(a);	
		        String currentDayOfWeek = dateFormat.format(date);
		        
		        if(!listOfDays.contains(currentDayOfWeek)){
		        	listOfDays.add(currentDayOfWeek);
		        	dayDetails.add(currentDayOfWeek);
		        	for(int b = 1; b < routeStasticsData.get(c).size()-1; b = b + 3){
		        		
		        		Date date2 = (Date) routeStasticsData.get(c).get(b + 2);
		        		String dayOfWeek = dateFormat.format(date2);
		        		
		        		if (currentDayOfWeek.equals(dayOfWeek)) {
			        		dayDetails.add(routeStasticsData.get(c).get(b));
		        		}
		        	}
		        	routeStatisticsDays.add(dayDetails);
		        }
		        
			}
			
			
			
			
			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
			dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
			ArrayList<Object> DayStats = new ArrayList<Object>();
			for(int d = 0; d < routeStatisticsDays.size(); d++){
		

			DayStats.add(routeStatisticsDays.get(d).get(0));
			DayStats.add(routeStatisticsDays.get(d).size()-1);
			
			long timeTotal = 0;
			int loopCount = 0;
			for(int e = 1; e < routeStatisticsDays.get(d).size(); e++){
				try {
					Date time = dateFormat2.parse(routeStatisticsDays.get(d).get(e).toString());
					long timeInMilSec = time.getTime();
					timeTotal = timeTotal + timeInMilSec;
					loopCount++;
				} catch (ParseException e1) {}
				
			}
			String result = dateFormat2.format(timeTotal / loopCount);
			DayStats.add(result);
			

			
			long minTime = 0;
			for(int p = 1; p < routeStatisticsDays.get(d).size(); p++){
				try{
					Date time = dateFormat2.parse(routeStatisticsDays.get(d).get(p).toString());
					long timeInMilSec = time.getTime();
					if(p == 1)
						minTime = timeInMilSec;
					if(timeInMilSec < minTime)
						minTime = timeInMilSec;
				}catch (ParseException e1) {}
			}
			String minResult = dateFormat2.format(minTime);
			DayStats.add(minResult);
			
			
			long maxTime = 0;
			for(int p = 1; p < routeStatisticsDays.get(d).size(); p++){
				try{
					Date time = dateFormat2.parse(routeStatisticsDays.get(d).get(p).toString());
					long timeInMilSec = time.getTime();
					if(p == 1)
						minTime = timeInMilSec;
					if(timeInMilSec > minTime)
						minTime = timeInMilSec;
				}catch (ParseException e1) {}
			}
			String maxResult = dateFormat2.format(minTime);
			DayStats.add(maxResult);

			
				
	}
			
			
			System.out.printf("%-45s", routeStasticsData.get(c).get(0));
			for(int m = 0; m < DayStats.size(); m +=5){
				if(m > 0)
					System.out.print("                                             ");
				System.out.printf("%-20s", DayStats.get(m));
				System.out.printf("%-20s", DayStats.get(m+1));
				System.out.printf("%-20s", DayStats.get(m+2));
				System.out.printf("%-20s", DayStats.get(m+3));
				System.out.printf("%-20s\n", DayStats.get(m+4));
			}
		}
		
	}
	
}
