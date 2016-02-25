package RailData;

import java.util.ArrayList;
import java.util.Collections;

public class RouteStastics {
	
	//Takes in an Arraylist comprising of each route, allRoute, and returns an Arraylist of each route, which contains route name,
	//duration of journey, time started and ended for each time the route is found, if it shows in the data 3 times, there's 3 sets of
	//the timing data, lastly it has the count of the route. This Arraylist is returned inside another which contains all the routes
	
	public static ArrayList<ArrayList<Object>> generateRouteStasticsData(ArrayList<ArrayList<Object>> allRoutes)
	{
		ArrayList<ArrayList<Object>> routeStasticsData = new ArrayList<ArrayList<Object>>();
		ArrayList<String> listOfRoutes = new ArrayList<String>();
		
		for (int a = 0; a < allRoutes.size(); a++) {
			String currRoute = allRoutes.get(a).get(0).toString();
			int currRouteCount = 0;
			ArrayList<Object> singleRouteDetails = new ArrayList<Object>();
			if (!listOfRoutes.contains(currRoute)) {
				listOfRoutes.add(currRoute);
				singleRouteDetails.add(currRoute);
				for (int b = 0; b < allRoutes.size(); b++) {
					if (Collections.frequency(allRoutes.get(b), currRoute) == 1) {
						singleRouteDetails.add(allRoutes.get(b).get(1));
						singleRouteDetails.add(allRoutes.get(b).get(2));
						singleRouteDetails.add(allRoutes.get(b).get(3));
						currRouteCount++;
					}
				}
				singleRouteDetails.add(currRouteCount);
				routeStasticsData.add(singleRouteDetails);
			}
		}
		return routeStasticsData;
	}
	
	//DESCRIPTION
	
	public static ArrayList<ArrayList<Object>> generateRouteStatistics(ArrayList<ArrayList<Object>> routeStasticsData)
	{
		ArrayList<ArrayList<Object>> routeStastics = new ArrayList<ArrayList<Object>>();
		
		for(int a = 0; a < routeStasticsData.size();a++)
		{
			ArrayList<Object> currRouteStatistics = new ArrayList<Object>();
			currRouteStatistics.add(routeStasticsData.get(a).get(0).toString());
			currRouteStatistics.add(routeStasticsData.get(a).get(routeStasticsData.get(a).size()-1).toString());
			currRouteStatistics.add(CalculateStats.calMinMax(routeStasticsData, "min", a));
			currRouteStatistics.add(CalculateStats.calAverage(routeStasticsData, a));
			currRouteStatistics.add(CalculateStats.calMinMax(routeStasticsData, "max", a));
			OffOnPeakStats offOnStats = new OffOnPeakStats(routeStasticsData, a);
			currRouteStatistics.add(offOnStats.getOffPeakCount());
			currRouteStatistics.add(offOnStats.getOnPeakCount());
			currRouteStatistics.add(offOnStats.getAverageOffPeakTime());
			currRouteStatistics.add(offOnStats.getAverageOnPeakTime());
			currRouteStatistics.add(offOnStats.getOffOnDiff());
			routeStastics.add(currRouteStatistics);
			offOnStats.resetVariables();
		}
		return routeStastics;
	}
	
	
}
