package uyr;

import java.util.ArrayList;

public class RouteSelectValues {

	private static ArrayList<String> allRoutes = new ArrayList<String>();
	
	//Gets all the route names from the route table and returns them for the StatisticsGUI
	public static ArrayList<String> populateAllRoutes(){
		allRoutes.clear();
		return allRoutes = SQLDatabase.selectAllFromRoute2("SELECT Route_Name from Route ORDER BY Route_Name");
	}
	
}
