package uyr;

import java.text.*;
import java.util.*;

public class CalStatistics {

	//Calculates the min/max value based on the values passed to it
	public static String calMinMax(ArrayList<ArrayList<String>> routeStasticsData, String operator, int count, int startPos, String format) 
	{
		long minMaxTime = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		for (int m = startPos; m < routeStasticsData.get(count).size(); m+=3) 
		{
			if(routeStasticsData.get(count).get(m).equals(" N/A")){
				return " N/A";
			}
			try {
				Date time = dateFormat.parse(routeStasticsData.get(count).get(m).toString());
				long timeInMilSec = time.getTime();
				if (m == startPos) 
				{
					minMaxTime = timeInMilSec;
				}
				if (operator == "min") 
				{
					if (timeInMilSec < minMaxTime) 
					{
						minMaxTime = timeInMilSec;
					}
				} else if (operator == "max") {
					if (timeInMilSec > minMaxTime) 
					{
						minMaxTime = timeInMilSec;
					}
				}
			} catch (ParseException e) {}
		}
		return dateFormat.format(minMaxTime);
	}
	
	//Calculates the average value based on the values passed to it
	public static String calAverage(ArrayList<ArrayList<String>> routeStasticsData, int count, int startPos, String format) 
	{
		long timeTotal = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		int loopCount = 0;
		for (int a = startPos; a < routeStasticsData.get(count).size(); a+=3) 
		{
			if(routeStasticsData.get(count).get(a).equals(" N/A")){
				return " N/A";
			}
			try {
				Date time = dateFormat.parse(routeStasticsData.get(count).get(a).toString());
				long timeInMilSec = time.getTime();
				timeTotal = timeTotal + timeInMilSec;
				loopCount++;
			} catch (ParseException e) {}
		}
		return dateFormat.format(timeTotal / loopCount);
	}
	
	//Calculates the median value based on the values passed to it
	public static String calMedian(ArrayList<ArrayList<String>> routeStasticsData, int count, int startPos, String format) {
		ArrayList<Integer> valsInMilSec = new ArrayList<Integer>();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		for (int m = startPos; m < routeStasticsData.get(count).size(); m+=3) 
		{
			if(routeStasticsData.get(count).get(m).equals(" N/A")){
				return " N/A";
			}
			Date time = null;
			try {
				time = dateFormat.parse(routeStasticsData.get(count).get(m).toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long timeInMilSec = time.getTime();
			valsInMilSec.add((int) timeInMilSec);
		}
		double result = 0;
		
		/**
		 * The below code to calculate the median was taken from
		 * Code Comments (2009) Code Comments Available at: http://code.hammerpig.com/simple-compute-median-java.html
		 * Accessed 27th April 2016
		 */
		Collections.sort(valsInMilSec);
		if (valsInMilSec.size() % 2 == 1)
			result = valsInMilSec.get((valsInMilSec.size()+1)/2-1);
		else{
			double lower = valsInMilSec.get(valsInMilSec.size()/2-1);
			double upper = valsInMilSec.get(valsInMilSec.size()/2);
			result = (lower + upper) / 2.0;
		   }
		return dateFormat.format(result);
	}
	
	//Calculates the time taken
	@SuppressWarnings("deprecation")
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
	
	//Calculates the elapsed time
	@SuppressWarnings("deprecation")
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
	
	//Calculates the stop time
	@SuppressWarnings("deprecation")
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
	
}
