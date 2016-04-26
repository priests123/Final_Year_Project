package Display;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

public class CalStatistics {

	public static String calMinMax(ArrayList<ArrayList<String>> routeStasticsData, String operator, int count, int startPos, String format) 
	{
		
		long minMaxTime = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		for (int m = startPos; m < routeStasticsData.get(count).size(); m+=3) 
		{
			if(routeStasticsData.get(count).get(m).equals(" N/A")){
				return " N/A";
			}
			try 
			{
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long timeInMilSec = time.getTime();
			valsInMilSec.add((int) timeInMilSec);
		}
		double result = 0;
		
		// taken from http://code.hammerpig.com/simple-compute-median-java.html to calculate median
		Collections.sort(valsInMilSec);
		if (valsInMilSec.size() % 2 == 1)
			result = valsInMilSec.get((valsInMilSec.size()+1)/2-1);
		 else
		    {
			double lower = valsInMilSec.get(valsInMilSec.size()/2-1);
			double upper = valsInMilSec.get(valsInMilSec.size()/2);
		 
			result = (lower + upper) / 2.0;
		    }
		return dateFormat.format(result);
	}
	
}