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
		PrintOut.printRouteStatistics(routeStatistics);
		ArrayList<ArrayList<Object>> routeStasticsDays = RouteStatisticsDays.generateRouteStasticsDaysData(routeStasticsData);
		PrintOut.printRouteDayStatistics(routeStasticsDays);
	}
	
}
