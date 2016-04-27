package uyr;

import java.text.*;
import java.util.*;

public class DataForDisplay {

	static ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
	
	//Constructor for the class, when the user searches this looks up in the dataabse based on their terms.
	//Depending on what the user is searching, like with or with dates/times will depend on which query is used.
	public DataForDisplay(String routeSelected, String dateTimeFrom, String dateTimeTo) {
		String baseSQL = "SELECT Unit_No, Journey_Started_Time_Stamp, Route_Name, station1.Station_Name, " + 
						 "Station_Left_Time_Stamp, station2.Station_Name, Station_Arrived_Time_Stamp FROM " + 
						 "Journey INNER JOIN Route ON Journey.Route_ID = Route.Route_ID INNER JOIN Station " + 
						 "AS station1 ON Journey.Station_Left = station1.Station_ID INNER JOIN Station AS " + 
						 "station2 ON Journey.Station_Arrived = station2.Station_ID WHERE Route_Name = ?";
		
		SimpleDateFormat formatterOrigional = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat formatterNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(dateTimeFrom.equals(" 00:00:00") && dateTimeTo.equals(" 00:00:00")){
			results = SQLDatabase.usrSearch(baseSQL, "justRoute", routeSelected, dateTimeFrom, dateTimeTo);
		} else if(!(dateTimeFrom.equals(" 00:00:00")) && !(dateTimeTo.equals(" 00:00:00"))){
			try{
				Date dateFromNew = formatterOrigional.parse(dateTimeFrom);
				Date dateToNew = formatterOrigional.parse(dateTimeTo);
				results = SQLDatabase.usrSearch(baseSQL + " AND Journey_Started_Time_Stamp BETWEEN ? AND ?", 
												"bothDateTime", routeSelected, formatterNew.format(dateFromNew),
												formatterNew.format(dateToNew));
			}catch(Exception e){}
		} else if(!(dateTimeFrom.equals(" 00:00:00"))){
			try{
				Date dateFromNew = formatterOrigional.parse(dateTimeFrom);
				results = SQLDatabase.usrSearch(baseSQL + " AND Journey_Started_Time_Stamp >= ?",
												"justFromDateTime", routeSelected, formatterNew
												.format(dateFromNew), dateTimeTo);
			}catch(Exception e){}
		}else if(!(dateTimeTo.equals(" 00:00:00"))){
			try{
				Date dateToNew = formatterOrigional.parse(dateTimeTo);
				results = SQLDatabase.usrSearch(baseSQL + " AND Journey_Started_Time_Stamp <= ?", 
												"justToDateTime", routeSelected, dateTimeFrom, 
												formatterNew.format(dateToNew));
			}catch(Exception e){}
		}
		ProduceStatistics.valuesForDisplay(results);
	}
	
}
