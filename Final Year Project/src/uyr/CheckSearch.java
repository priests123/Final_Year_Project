package uyr;

import java.sql.Time;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

public class CheckSearch {
	
	//Check the search terms selected by the user. Checking that time/date to isn't before time/date from,
	//there is a route value & you need to have a date to have a time
	public static Boolean checkSearchTerms(String routeSelected, String dateFrom, String timeFrom, String dateTo, String timeTo){
		
		StatisticsGUI.setErrorMsg("");
		
		if(!routeSelected.equals("")){
			if(!dateFrom.equals("") && !dateTo.equals("")){
				DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
				LocalDate dateFromValue = LocalDate.parse(dateFrom, formatDate);
				LocalDate dateToValue = LocalDate.parse(dateTo, formatDate);
				if(dateFromValue.isAfter(dateToValue)){
					StatisticsGUI.setErrorMsg("Please ensure that date from isn't after date to");
					return false;
				}
			}
				
			if(!(timeFrom.equals("00:00:00"))){
				if(dateFrom.equals("")){
					StatisticsGUI.setErrorMsg("Please ensure date from has a value");
					return false;
				}
			}
			
			if(!(timeTo.equals("00:00:00"))){
				if(dateTo.equals("")){
					StatisticsGUI.setErrorMsg("Please ensure date to has a value");
					return false;
				}
			}
			
			if(dateFrom.equals(dateTo)){
				DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
				Date dFrom;
				Date dTo;
				try {
					dFrom = (Date)formatter.parse(timeFrom);
					dTo = (Date)formatter.parse(timeTo);
					Time tFrom = new Time(dFrom.getTime());
					Time tTo = new Time(dTo.getTime());
					if(tFrom.after(tTo)){
						StatisticsGUI.setErrorMsg("Please ensure time from isn't after time to");
						return false;
					}
				} catch (ParseException e) {e.printStackTrace();}  
			}
			
		}else{
			StatisticsGUI.setErrorMsg("Please select a route");
			return false;
		}
		return true;
	}

}
