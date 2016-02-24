package RailData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversions {
	
	//Takes in a string type and returns it as a date type
	
	public static Date convertToDate(String strDate) {
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		Date convertedDate = null;
		try {
			convertedDate = format.parse(strDate);
		} catch (ParseException e) {
			System.out.println("Couldn't convert date formats");
		}
		return convertedDate;
	}
}
