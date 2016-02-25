package RailData;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		String fileLcoation = "C:\\Users\\Michael\\Desktop\\Spark\\DataSets\\465245-weekly-routes.txt";
		ArrayList<ArrayList<String>> railData = ReadInFile.readInCSV(fileLcoation);
		ArrayList<ArrayList<Object>> allRoutes = RailDataToRoute.convertRailDataToRoutes(railData);
		ArrayList<ArrayList<Object>> routeStasticsData = RouteStastics.generateRouteStasticsData(allRoutes);
		ArrayList<ArrayList<Object>> routeStastics = RouteStastics.generateRouteStatistics(routeStasticsData);
		PrintOut.printRouteStatistics(routeStastics);
	}

}
