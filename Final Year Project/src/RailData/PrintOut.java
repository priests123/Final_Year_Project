package RailData;

import java.util.ArrayList;

public class PrintOut {
	
	//Prints out the contents of routeStatistics
	
	public static void printRouteStatistics(ArrayList<ArrayList<Object>> routeStatistics)
	{
		System.out.printf("%-45s%-20s%-20s%-20s%-20s%-20s%-20s%-25s%-25s%-30s\n", "Route", "Count", "Minimum", "Average", "Maximum", "Off peak count", "On peak count", "Average off peak time", "Average on peak time", "% increase between off/on peak");
		System.out.format("%-45s%-20s%-20s%-20s%-20s%-20s%-20s%-25s%-25s%-30s\n", "---------------------------------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "--------------------", "-------------------------", "-------------------------", "------------------------------");
		for(int a = 0; a < routeStatistics.size(); a++)
		{
			System.out.printf("%-45s", routeStatistics.get(a).get(0));
			System.out.printf("%-20s", routeStatistics.get(a).get(1));
			System.out.printf("%-20s", routeStatistics.get(a).get(2));
			System.out.printf("%-20s", routeStatistics.get(a).get(3));
			System.out.printf("%-20s", routeStatistics.get(a).get(4));
			System.out.printf("%-20s", routeStatistics.get(a).get(5));
			System.out.printf("%-20s", routeStatistics.get(a).get(6));
			System.out.printf("%-25s", routeStatistics.get(a).get(7));
			System.out.printf("%-25s", routeStatistics.get(a).get(8));
			System.out.printf("%-30s\n", routeStatistics.get(a).get(9));
		}
	}
	
	//Prints out the contents of routeStatisticsDays
	
	public static void printRouteDayStatistics(ArrayList<ArrayList<Object>> routeStasticsDays){
		System.out.printf("%-45s%-20s%-20s%-20s%-20s%-20s\n", "Route", "Day of Week", "Count", "Average", "Minimum", "Maximum");
		for(int m = 0; m < routeStasticsDays.size(); m ++){
			for(int i = 0; i < routeStasticsDays.get(m).size()-1; i+=5){
			if(i > 0)
				System.out.print("                                             ");
			else
				System.out.printf("%-45s", routeStasticsDays.get(m).get(i));
			System.out.printf("%-20s", routeStasticsDays.get(m).get(i+1));
			System.out.printf("%-20s", routeStasticsDays.get(m).get(i+2));
			System.out.printf("%-20s", routeStasticsDays.get(m).get(i+3));
			System.out.printf("%-20s", routeStasticsDays.get(m).get(i+4));
			System.out.printf("%-20s\n", routeStasticsDays.get(m).get(i+5));
			}
		}
	}
	
}
