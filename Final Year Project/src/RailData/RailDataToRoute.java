package RailData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RailDataToRoute {

	//Takes in the railData and returns an arraylist, allRoutes, of each route with time taken, started and ended
	//Each route is it's own seperate arraylist that contains the data on the route, each is inserted into allRoutes arraylist
	
	public static ArrayList<ArrayList<Object>> convertRailDataToRoutes(ArrayList<ArrayList<String>> railData){
		ArrayList<ArrayList<Object>> allRoutes = new ArrayList<ArrayList<Object>>();
		for (int c = 0; c < railData.size(); c++) {
			String locationFirst = null;
			String locationLast = null;
			Date dateFirst = null;
			Date dateLast = null;
			if ((railData.get(c).size() == 18) && (railData.get(c).get(17).equals("FIRST"))) {
				locationFirst = railData.get(c).get(3);
				dateFirst = Conversions.convertToDate(railData.get(c).get(1));
				for (int d = c; d < railData.size(); d++) {
					if ((railData.get(d).size() == 18) && (railData.get(d).get(17).equals("LAST"))) {
						locationLast = railData.get(d).get(3);
						dateLast = Conversions.convertToDate(railData.get(d).get(1));
						long timeDifference = dateLast.getTime() - dateFirst.getTime();
						long differenceHours = timeDifference / (60 * 60 * 1000);
						long differenceMinutes = timeDifference / (60 * 1000) % 60;
						String routeName = locationFirst + " to " + locationLast;
						Date Time = new Date(timeDifference);
						Time.setHours((int) differenceHours);
						Time.setMinutes((int) differenceMinutes);
						SimpleDateFormat hoursMins = new SimpleDateFormat("HH:mm");
						String routeTime = hoursMins.format(Time);
						ArrayList<Object> route = new ArrayList<Object>();
						route.add(routeName);
						route.add(routeTime);
						route.add(dateFirst);
						route.add(dateLast);
						allRoutes.add(route);
						break;
					}
				}
			}
		}
		return allRoutes;
	}
}
