package Display;

import importToDB.SQLDatabase;

import java.util.ArrayList;

public class RouteSelectValues {

	private static ArrayList<String> allRoutes = new ArrayList<String>();
	
	public static ArrayList<String> populateAllRoutes(){
		allRoutes.clear();
		return allRoutes = SQLDatabase.selectAllFromRoute2("SELECT Route_Name from Route ORDER BY Route_Name");
	}
	
	
}