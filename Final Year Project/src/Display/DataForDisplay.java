package Display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import importToDB.SQLDatabase;

public class DataForDisplay {

	public DataForDisplay(String routeSelected, String dateTimeFrom, String dateTimeTo) {
		
		String baseSQL = "SELECT Unit_No, Journey_Started_Time_Stamp, Route_Name, station1.Station_Name, Station_Left_Time_Stamp, station2.Station_Name, Station_Arrived_Time_Stamp FROM Journey INNER JOIN Route ON Journey.Route_ID = Route.Route_ID INNER JOIN Station AS station1 ON Journey.Station_Left = station1.Station_ID INNER JOIN Station AS station2 ON Journey.Station_Arrived = station2.Station_ID WHERE Route_Name = ?";
		
		//System.out.println(routeSelected);
		//System.out.println(dateTimeFrom);
		//System.out.println(dateTimeTo);
		
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		SimpleDateFormat formatterOrigional = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat formatterNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(dateTimeFrom.equals(" 00:00:00") && dateTimeTo.equals(" 00:00:00")){
			results = SQLDatabase.usrSearch(baseSQL, "justRoute", routeSelected, dateTimeFrom, dateTimeTo);
		} else if(!(dateTimeFrom.equals(" 00:00:00")) && !(dateTimeTo.equals(" 00:00:00"))){
			try{
				Date dateFromNew = formatterOrigional.parse(dateTimeFrom);
				Date dateToNew = formatterOrigional.parse(dateTimeTo);
				System.out.println("Here");
				System.out.println(formatterNew.format(dateFromNew));
				System.out.println(formatterNew.format(dateToNew));
				results = SQLDatabase.usrSearch(baseSQL + " AND Journey_Started_Time_Stamp BETWEEN ? AND ?", "bothDateTime", routeSelected, formatterNew.format(dateFromNew), formatterNew.format(dateToNew));
			}catch(Exception e){}
		} else if(!(dateTimeFrom.equals(" 00:00:00"))){
			try{
				Date dateFromNew = formatterOrigional.parse(dateTimeFrom);
				results = SQLDatabase.usrSearch(baseSQL + " AND Journey_Started_Time_Stamp >= ?", "justFromDateTime", routeSelected, formatterNew.format(dateFromNew), dateTimeTo);
			}catch(Exception e){}
		}else if(!(dateTimeTo.equals(" 00:00:00"))){
			try{
				Date dateToNew = formatterOrigional.parse(dateTimeTo);
				results = SQLDatabase.usrSearch(baseSQL + " AND Journey_Started_Time_Stamp <= ?", "justToDateTime", routeSelected, dateTimeFrom, formatterNew.format(dateToNew));
			}catch(Exception e){}
			}
		//System.out.println(results);
		ArrayList<ArrayList<String>> finalResultsToPrint = new ArrayList<ArrayList<String>>();
		int count = 0;
		while(results.size() > count){
			String currInstanceStartTime = results.get(count).get(1);
			ArrayList<ArrayList<String>> currInstanceRoute = new ArrayList<ArrayList<String>>();
			for(int b = count; b < results.size(); b++){
				if(results.get(b).get(1).equals(currInstanceStartTime)){
					currInstanceRoute.add(results.get(b));
					count++;
					//results.remove(0);
				}
			}
		
		
		
		
		for(int m = 0; m < currInstanceRoute.size(); m++){
			ArrayList<String> resultsRow = new ArrayList<String>();
			String stationJourneyName = currInstanceRoute.get(m).get(3) + " to " + currInstanceRoute.get(m).get(5);
			resultsRow.add(stationJourneyName);
			String leftTime = reformatDateTime(currInstanceRoute.get(m).get(4));
			resultsRow.add(leftTime);
			String arrivedTime = reformatDateTime(currInstanceRoute.get(m).get(6));
			resultsRow.add(arrivedTime);
			resultsRow.add(calTimeTaken(leftTime, arrivedTime));
			if(m == 0){
				resultsRow.add("N/A");
			}else{
				resultsRow.add(calStopTime(reformatDateTime(currInstanceRoute.get(m-1).get(6)), reformatDateTime(currInstanceRoute.get(m).get(4))));
			}
			resultsRow.add(calElapsedTime(reformatDateTime(currInstanceRoute.get(0).get(1)), reformatDateTime(currInstanceRoute.get(m).get(6))));
			
			finalResultsToPrint.add(resultsRow);
		}
		System.out.println(finalResultsToPrint);
		}
		
		
	}
	
	public static String reformatDateTime(String dateTime){
		SimpleDateFormat formatterOrigional = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatterNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateFromNew = null;
		try {
			dateFromNew = formatterOrigional.parse(dateTime);
		} catch (ParseException e) {e.printStackTrace();}
		return formatterNew.format(dateFromNew);
	}
	
	public static String calTimeTaken(String leftTimeS, String arrivedTimeS){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date leftTime = null;
		Date arrivedTime = null;
		try {
			leftTime = format.parse(leftTimeS);
			arrivedTime = format.parse(arrivedTimeS);
		} catch (ParseException e) {e.printStackTrace();}
		
		long timeDifference = arrivedTime.getTime() - leftTime.getTime();
		long diffMinutes = timeDifference / (60 * 1000) % 60;
		long diffSeconds = timeDifference / 1000 % 60;
		Date diffTime = new Date(timeDifference);
		diffTime.setMinutes((int) diffMinutes);
		diffTime.setSeconds((int)diffSeconds);
		SimpleDateFormat formatResult = new SimpleDateFormat("mm:ss");
		String result = formatResult.format(diffTime);
		return result;
	}

	public static String calStopTime(String stationArrivedS, String stationLeftS){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date stationArrived = null;
		Date stationLeft = null;
		try {
			stationArrived = format.parse(stationArrivedS);
			stationLeft = format.parse(stationLeftS);
		} catch (ParseException e) {e.printStackTrace();}
		long timeDifference = stationLeft.getTime() - stationArrived.getTime();
		long diffMinutes = timeDifference / (60 * 1000) % 60;
		long diffSeconds = timeDifference / 1000 % 60;
		Date diffTime = new Date(timeDifference);
		diffTime.setMinutes((int) diffMinutes);
		diffTime.setSeconds((int)diffSeconds);
		SimpleDateFormat formatResult = new SimpleDateFormat("mm:ss");
		String result = formatResult.format(diffTime);
		return result;
	}
	
	public static String calElapsedTime(String firstStartTimeS, String currArrivedTimeS){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date firstStartTime = null;
		Date currArrivedTime = null;
		try {
			firstStartTime = format.parse(firstStartTimeS);
			currArrivedTime = format.parse(currArrivedTimeS);
		} catch (ParseException e) {e.printStackTrace();}
		long timeDifference = currArrivedTime.getTime() - firstStartTime.getTime();
		long diffHours = timeDifference / (60 * 60 * 1000) % 24;
		long diffMinutes = timeDifference / (60 * 1000) % 60;
		long diffSeconds = timeDifference / 1000 % 60;
		Date diffTime = new Date(timeDifference);
		diffTime.setHours((int)diffHours);
		diffTime.setMinutes((int) diffMinutes);
		diffTime.setSeconds((int)diffSeconds);
		SimpleDateFormat formatResult = new SimpleDateFormat("HH:mm:ss");
		String result = formatResult.format(diffTime);
		return result;
	}
}
