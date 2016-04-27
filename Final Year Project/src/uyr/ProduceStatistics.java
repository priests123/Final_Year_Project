package uyr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProduceStatistics {

	//Calculates and formats the data that will be displayed on the StatisticsGUI, 
	//calls methods to add the values to the display
	public static void valuesForDisplay(ArrayList<ArrayList<String>> results){
		StatisticsGUI.clearDataView();
		if(results.size() > 0){
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
					}
				}
				for(int m = 0; m < currInstanceRoute.size(); m++){
					ArrayList<String> resultsRow = new ArrayList<String>();
					String stationJourneyName = currInstanceRoute.get(m).get(3) + " to " + 
												currInstanceRoute.get(m).get(5);
					if(m == 0){
						firstStationJourney = stationJourneyName;
					}
					resultsRow.add(stationJourneyName);
					String leftTime = reformatDateTime(currInstanceRoute.get(m).get(4));
					resultsRow.add(leftTime);
					String arrivedTime = reformatDateTime(currInstanceRoute.get(m).get(6));
					resultsRow.add(arrivedTime);
					resultsRow.add(CalStatistics.calTimeTaken(leftTime, arrivedTime));
					if(m == 0){
						resultsRow.add(" N/A");
					}else{
						resultsRow.add(CalStatistics.calStopTime(reformatDateTime(currInstanceRoute.get(m-1).get(6)),
												   reformatDateTime(currInstanceRoute.get(m).get(4))));
					}
					resultsRow.add(CalStatistics.calElapsedTime(reformatDateTime(currInstanceRoute.get(0).get(1)), 
												  reformatDateTime(currInstanceRoute.get(m).get(6))));
					finalResultsToPrint.add(resultsRow);
				}
			}
			AddToStatisticsGUI.addDataViewColumnNames();
			AddToStatisticsGUI.addDataViewValues(finalResultsToPrint, firstStationJourney);
			AddToStatisticsGUI.addDataViewStatisticsColumnNames();
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
			AddToStatisticsGUI.addDataViewStatisticsValues(finalStatisticsToPrint);
		}else{
			StatisticsGUI.addToDataViewFont1(String.format("No results found. Please alter your selections"));
		}
		StatisticsGUI.setScrollPos();
	}
		
	//Formats the date inputed by the user ready to by used in the database SELECT statements
	public static String reformatDateTime(String dateTime){
		SimpleDateFormat formatterOrigional = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatterNew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateFromNew = null;
		try {
			dateFromNew = formatterOrigional.parse(dateTime);
		} catch (ParseException e) {e.printStackTrace();}
		return formatterNew.format(dateFromNew);
	}
}

