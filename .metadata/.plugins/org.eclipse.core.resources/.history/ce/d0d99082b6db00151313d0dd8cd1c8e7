package RailData;

import java.util.ArrayList;

public class PrintOut {
	
	public static void printRouteStatistics(ArrayList<ArrayList<Object>> routeStastics)
	{
		System.out.printf("%-45s%-20s%-20s%-20s%-20s%-20s%-20s%-25s%-25s%-30s\n", "Route", "Count", "Minimum", "Average", "Maximum", "Off peak count", "On peak count", "Average off peak time", "Average on peak time", "% increase between off/on peak");
		System.out.format("%-45s%-20s%-20s%-20s%-20s%-20s%-20s%-25s%-25s%-30s\n", "---------------------------------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "-------------------------", "-------------------------", "------------------------------");
		for(int a = 0; a < routeStastics.size(); a++)
		{
			System.out.printf("%-45s", routeStastics.get(a).get(0));
			System.out.printf("%-20s", routeStastics.get(a).get(1));
			System.out.printf("%-20s", routeStastics.get(a).get(2));
			System.out.printf("%-20s", routeStastics.get(a).get(3));
			System.out.printf("%-20s", routeStastics.get(a).get(4));
			System.out.printf("%-20s", routeStastics.get(a).get(5));
			System.out.printf("%-20s", routeStastics.get(a).get(6));
			System.out.printf("%-25s", routeStastics.get(a).get(7));
			System.out.printf("%-25s", routeStastics.get(a).get(8));
			System.out.printf("%-30s\n", routeStastics.get(a).get(9));
		}
	}
	
}
