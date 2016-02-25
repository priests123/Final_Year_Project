package RailData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class CalculateStats {

	//Description
	
	public static String calMinMax(ArrayList<ArrayList<Object>> routeStasticsData, String operator, int count) 
	{
		long minMaxTime = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		for (int m = 1; m < routeStasticsData.get(count).size(); m = m + 3) 
		{
			try 
			{
				Date time = dateFormat.parse(routeStasticsData.get(count).get(m).toString());
				long timeInMilSec = time.getTime();
				if (m == 1) 
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
	
	//DEscription
	
	public static String calAverage(ArrayList<ArrayList<Object>> routeStasticsData, int count) 
	{
		long timeTotal = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		int loopCount = 0;
		for (int a = 1; a < routeStasticsData.get(count).size(); a = a + 3) 
		{
			try 
			{
				Date time = dateFormat.parse(routeStasticsData.get(count).get(a).toString());
				long timeInMilSec = time.getTime();
				timeTotal = timeTotal + timeInMilSec;
				loopCount++;
			} catch (ParseException e) {}
		}
		return dateFormat.format(timeTotal / loopCount);
	}
	
}
