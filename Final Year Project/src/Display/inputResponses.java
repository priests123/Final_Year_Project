package Display;

import java.util.ArrayList;

public class inputResponses {

	private static ArrayList<String> inputResponses = new ArrayList<String>();
	
	public static void addInoutResponses(String message){
		inputResponses.add(message);
	}
	
	public static ArrayList<String> getInputResponses(){
		return inputResponses;
	}
	
	public static void clearInputResponses(){
		inputResponses.clear();
	}
	
}