package RailData;

import java.util.ArrayList;
import java.util.Date;

public class TimeBetweenStations {

	//Takes in the railData and prints the time taken for a train to go between each station
	
	public static void timeBetween(ArrayList<ArrayList<String>> railData){
		for (int a = 0; a < railData.size() - 1; a = a + 2) {
			String locationValue1 = railData.get(a).get(3);
			String locationValue2 = railData.get(a + 1).get(3);
			Date val1 = Conversions.convertToDate(railData.get(a).get(1));
			Date val2 = Conversions.convertToDate(railData.get(a + 1).get(1));
			long timeDifference = (val2.getTime() - val1.getTime()) / (60 * 1000) % 60;
			System.out.println("The time taken between " + locationValue1 + " and " + locationValue2 + " was " + timeDifference + " minutes");
		}
	}
	
}
