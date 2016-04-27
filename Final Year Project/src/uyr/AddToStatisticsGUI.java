package uyr;

import java.util.ArrayList;

public class AddToStatisticsGUI {
	
	//Adds the DataView column names to StatisticsGUI
	public static void addDataViewColumnNames(){
		StatisticsGUI.addToDataViewFont1(String.format("%-40s", "Stations"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Time Left"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Time Arrived"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Time Travel(mm:ss)"));
		StatisticsGUI.addToDataViewFont1(String.format("%-22s", "Stop Time(mm:ss)"));
		StatisticsGUI.addToDataViewFont1(String.format("%s", "Time Elapsed(hh:mm:ss)"));
		StatisticsGUI.addToDataViewFont1("\n");
	}
	
	//Adds the DataView values to StatisticsGUI
	public static void addDataViewValues(ArrayList<ArrayList<String>> finalResultsToPrint, String firstStationJourney){
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
	}

	//Adds the DataViewStatistics column names to StatisticsGUI
	public static void addDataViewStatisticsColumnNames(){
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
	}
	
	//Adds the DataViewStatistics values to StatisticsGUI
	public static void addDataViewStatisticsValues(ArrayList<ArrayList<String>> finalStatisticsToPrint){
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
	}
	
}
