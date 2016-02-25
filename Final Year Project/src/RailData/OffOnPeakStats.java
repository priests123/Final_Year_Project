package RailData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class OffOnPeakStats {

	static int offPeakCount = 0;
	static int onPeakCount = 0;
	static String averageOffPeakTime = null;
	static String averageOnPeakTime = null;
	static String offOnDiff = null;
	
	
	public OffOnPeakStats(ArrayList<ArrayList<Object>> routeStasticsData, int count)
	{
		long offPeakTotalTime = 0;
		long onPeakTotalTime = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try{
		for (int a = 2; a < routeStasticsData.get(count).size(); a = a + 3) {
			int currentHour = ((Date) routeStasticsData.get(count).get(a)).getHours();
			if((currentHour >= 6 && currentHour < 9)|| (currentHour >= 16 && currentHour < 19)){
				try{
					Date time = sdf.parse(routeStasticsData.get(count).get(a-1).toString());
					long timeInMilSec = time.getTime();
					onPeakTotalTime = onPeakTotalTime + timeInMilSec;
					onPeakCount++;
				}catch(ParseException e){}
			}else{
				try{
					Date time = sdf.parse(routeStasticsData.get(count).get(a-1).toString());
					long timeInMilSec = time.getTime();
					offPeakTotalTime = offPeakTotalTime + timeInMilSec;
					offPeakCount++;
				}catch(ParseException e){}
			}
		}
		}catch(IndexOutOfBoundsException e){}
		
		
		long offAverage = 0;
		long onAverage = 0;
		
		if(offPeakCount != 0){
			offAverage = offPeakTotalTime / offPeakCount;
			averageOffPeakTime = sdf.format(offAverage);
		}else{
			averageOffPeakTime = "N/A";
		}
		if(onPeakCount != 0){
			onAverage = onPeakTotalTime / onPeakCount;
			averageOnPeakTime = sdf.format(onAverage);
		}else{
			averageOnPeakTime = "N/A";
		}
		
		double diffBetweenOffOn = 0;
		if((onPeakCount != 0)) 
		{
			if(offPeakCount != 0) 
			{
				double diff = TimeUnit.MILLISECONDS.toMinutes(onAverage) - TimeUnit.MILLISECONDS.toMinutes(offAverage);
				double percentage = diff/TimeUnit.MILLISECONDS.toMinutes(offAverage);;
				diffBetweenOffOn = (percentage * 100);	
			}
		}
		
		if(averageOffPeakTime.equals(averageOnPeakTime)){
			offOnDiff = "0.0";
		}else if(diffBetweenOffOn == 0){
			offOnDiff = "N/A";
		}else{
			offOnDiff = String.format("%.1f",diffBetweenOffOn); 
		}
		
	}
	
	public void resetVariables()
	{
		offPeakCount = 0;
		onPeakCount = 0;
		averageOffPeakTime = null;
		averageOnPeakTime = null;
		offOnDiff = null;
	}
	
	public String getOffPeakCount()
	{
		String result = String.valueOf(offPeakCount);
		return result;	
	}
	
	public String getOnPeakCount()
	{
		String result = String.valueOf(onPeakCount);
		return result;
	}
	
	public String getAverageOffPeakTime()
	{
		return averageOffPeakTime;
	}
	
	public String getAverageOnPeakTime()
	{
		return averageOnPeakTime;
	}
	
	public String getOffOnDiff()
	{
		String result = String.valueOf(offOnDiff);
		return result;
	}
}
