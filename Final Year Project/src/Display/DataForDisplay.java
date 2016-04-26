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
		
		StatisticsGUI.clearDataView();
		if(results.size() > 0)
		{
		
		
		
		ArrayList<ArrayList<String>> finalResultsToPrint = new ArrayList<ArrayList<String>>();
		

		
		int count = 0;
		String firstStationJourney = null;
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
		
		//int noOfStops = 0;
		
		
		for(int m = 0; m < currInstanceRoute.size(); m++){
			ArrayList<String> resultsRow = new ArrayList<String>();
			String stationJourneyName = currInstanceRoute.get(m).get(3) + " to " + currInstanceRoute.get(m).get(5);
			if(m == 0){
				firstStationJourney = stationJourneyName;
			}
			resultsRow.add(stationJourneyName);
			String leftTime = reformatDateTime(currInstanceRoute.get(m).get(4));
			resultsRow.add(leftTime);
			String arrivedTime = reformatDateTime(currInstanceRoute.get(m).get(6));
			resultsRow.add(arrivedTime);
			resultsRow.add(calTimeTaken(leftTime, arrivedTime));
			if(m == 0){
				resultsRow.add(" N/A");
				
			}else{
				resultsRow.add(calStopTime(reformatDateTime(currInstanceRoute.get(m-1).get(6)), reformatDateTime(currInstanceRoute.get(m).get(4))));
				
			}
			resultsRow.add(calElapsedTime(reformatDateTime(currInstanceRoute.get(0).get(1)), reformatDateTime(currInstanceRoute.get(m).get(6))));
			finalResultsToPrint.add(resultsRow);
		}
		
		

		//System.out.println(finalResultsToPrint);
		}
		
		StatisticsGUI.addToDataViewFont1(String.format("%-40s", "Stations"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Time Left"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Time Arrived"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Time Travel(mm:ss)"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Stop Time(mm:ss)"));
		StatisticsGUI.addToDataViewFont1(String.format("%s", "Time Elapsed(hh:mm:ss)"));
		StatisticsGUI.addToDataViewFont1("\n");
		
		for(int b = 0; b < finalResultsToPrint.size(); b++){
			
			if(finalResultsToPrint.get(b).get(0).equals(firstStationJourney)){
				for(int a = 0; a < 220; a++)
					StatisticsGUI.addToDataViewFont2("-");
				StatisticsGUI.addToDataViewFont1("\n");
			}
			
			StatisticsGUI.addToDataViewFont2(String.format("%-54s", finalResultsToPrint.get(b).get(0).toString()));
			StatisticsGUI.addToDataViewFont2(String.format("%-34s", finalResultsToPrint.get(b).get(1).toString()));
			StatisticsGUI.addToDataViewFont2(String.format("%-41s", finalResultsToPrint.get(b).get(2).toString()));
			StatisticsGUI.addToDataViewFont2(String.format("%-31s", finalResultsToPrint.get(b).get(3).toString()));
			StatisticsGUI.addToDataViewFont2(String.format("%-34s", finalResultsToPrint.get(b).get(4).toString()));
			StatisticsGUI.addToDataViewFont2(String.format("%s", finalResultsToPrint.get(b).get(5).toString()));
			StatisticsGUI.addToDataViewFont2("\n");
			
			
		}
		
		
		
		
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-50s", ""));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-40s", "Travel Time"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-40s", "Stop Time"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%s", "Elapsed Time"));
		StatisticsGUI.addToDataViewStatsFont1("\n");
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-40s", "Stations"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Min"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Max"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Avg"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-16s", "Median"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Min"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Max"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Avg"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-16s", "Median"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Min"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Max"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Avg"));
		StatisticsGUI.addToDataViewStatsFont1(String.format("%-8s", "Median"));
		StatisticsGUI.addToDataViewStatsFont1("\n");
		for(int a = 0; a < 220; a++)
			StatisticsGUI.addToDataViewStatsFont2("-");
		StatisticsGUI.addToDataViewFont1("\n");
		
		
		
		ArrayList<ArrayList<String>> stationsTimesForStats = new ArrayList<ArrayList<String>>();
		for(int a = 0; a < finalResultsToPrint.size(); a++){
			if(finalResultsToPrint.get(a).get(0).equals(firstStationJourney) && a != 0){
				break;
			}else{
				String currStations = finalResultsToPrint.get(a).get(0);
				ArrayList<String> currStationsTimes = new ArrayList<String>();
				currStationsTimes.add(currStations);
				for(int b = 0; b < finalResultsToPrint.size(); b++){
					if(finalResultsToPrint.get(b).get(0).equals(currStations)){
						currStationsTimes.add(finalResultsToPrint.get(b).get(3));
						currStationsTimes.add(finalResultsToPrint.get(b).get(4));
						currStationsTimes.add(finalResultsToPrint.get(b).get(5));
					}
				}
				stationsTimesForStats.add(currStationsTimes);
			}
		
		}
		
		
		
		ArrayList<ArrayList<String>> finalStatisticsToPrint = new ArrayList<ArrayList<String>>();
		
		for(int c = 0; c < stationsTimesForStats.size(); c++){
			ArrayList<String> statsRow = new ArrayList<String>();
			statsRow.add(stationsTimesForStats.get(c).get(0));
			statsRow.add(CalStatistics.calMinMax(stationsTimesForStats, "min", c, 1, "mm:ss"));
			statsRow.add(CalStatistics.calMinMax(stationsTimesForStats, "max", c, 1, "mm:ss"));
			statsRow.add(CalStatistics.calAverage(stationsTimesForStats, c, 1, "mm:ss"));
			statsRow.add(CalStatistics.calMedian(stationsTimesForStats, c, 1, "mm:ss"));
			statsRow.add(CalStatistics.calMinMax(stationsTimesForStats, "min", c, 2, "mm:ss"));
			statsRow.add(CalStatistics.calMinMax(stationsTimesForStats, "max", c, 2, "mm:ss"));
			statsRow.add(CalStatistics.calAverage(stationsTimesForStats, c, 2, "mm:ss"));
			statsRow.add(CalStatistics.calMedian(stationsTimesForStats, c, 2, "mm:ss"));
			statsRow.add(CalStatistics.calMinMax(stationsTimesForStats, "min", c, 3, "HH:mm:ss"));
			statsRow.add(CalStatistics.calMinMax(stationsTimesForStats, "max", c, 3, "HH:mm:ss"));
			statsRow.add(CalStatistics.calAverage(stationsTimesForStats, c, 3, "HH:mm:ss"));
			statsRow.add(CalStatistics.calMedian(stationsTimesForStats, c, 3, "HH:mm:ss"));
			
			finalStatisticsToPrint.add(statsRow);
		}
		
		StatisticsGUI.addToDataViewStatsFont1("\n");
		for(int d = 0; d < finalStatisticsToPrint.size(); d++){
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-57s", finalStatisticsToPrint.get(d).get(0).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-11s", finalStatisticsToPrint.get(d).get(1).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-11s", finalStatisticsToPrint.get(d).get(2).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-14s", finalStatisticsToPrint.get(d).get(3).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-21s", finalStatisticsToPrint.get(d).get(4).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-12s", finalStatisticsToPrint.get(d).get(5).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-11s", finalStatisticsToPrint.get(d).get(6).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-13s", finalStatisticsToPrint.get(d).get(7).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-20s", finalStatisticsToPrint.get(d).get(8).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-11s", finalStatisticsToPrint.get(d).get(9).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-12s", finalStatisticsToPrint.get(d).get(10).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%-13s", finalStatisticsToPrint.get(d).get(11).toString()));
			StatisticsGUI.addToDataViewStatsFont2(String.format("%s", finalStatisticsToPrint.get(d).get(12).toString()));
			
			
			
			
			
			
			
			StatisticsGUI.addToDataViewStatsFont2("\n");
		}
		
		
		
		
		
		
		
		}else{
			StatisticsGUI.addToDataViewFont1(String.format("No results found. Please alter your selections"));
		}
		
		StatisticsGUI.setScrollPos();
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
