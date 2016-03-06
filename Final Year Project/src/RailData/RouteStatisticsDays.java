package RailData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class RouteStatisticsDays {

	//Takes the routeStatisticsData arraylist then formats that into an arraylist routeDayStastics which for each route contains a list of days along with the duration
	//of each journey. Then this is used to calculate the statistics for each day of each route
	
	public static ArrayList<ArrayList<Object>> generateRouteStasticsDaysData(ArrayList<ArrayList<Object>> routeStasticsData){
		
		ArrayList<ArrayList<Object>> routeStatisticsDays = new ArrayList<ArrayList<Object>>();
		for(int c = 0; c < routeStasticsData.size(); c++)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("E");
			ArrayList<ArrayList<Object>> routeDayStatistics = new ArrayList<ArrayList<Object>>();
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
		        	routeDayStatistics.add(dayDetails);
		        }
		        
			}
			
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm");
			dateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
			ArrayList<Object> DayStats = new ArrayList<Object>();
			DayStats.add(routeStasticsData.get(c).get(0));
			for(int d = 0; d < routeDayStatistics.size(); d++){	
				DayStats.add(routeDayStatistics.get(d).get(0));
				DayStats.add(routeDayStatistics.get(d).size()-1);
				long timeTotal = 0;
				int loopCount = 0;
				
				for(int e = 1; e < routeDayStatistics.get(d).size(); e++){
					try {
						Date time = dateFormat2.parse(routeDayStatistics.get(d).get(e).toString());
						long timeInMilSec = time.getTime();
						timeTotal = timeTotal + timeInMilSec;
						loopCount++;
					} catch (ParseException e1) {}
					
				}
				String result = dateFormat2.format(timeTotal / loopCount);
				DayStats.add(result);
				
				long minTime = 0;
				for(int p = 1; p < routeDayStatistics.get(d).size(); p++){
					try{
						Date time = dateFormat2.parse(routeDayStatistics.get(d).get(p).toString());
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
				for(int p = 1; p < routeDayStatistics.get(d).size(); p++){
					try{
						Date time = dateFormat2.parse(routeDayStatistics.get(d).get(p).toString());
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
			routeStatisticsDays.add(DayStats);
		}
		return routeStatisticsDays;
	}
	
}
