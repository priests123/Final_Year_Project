package uyr;

import java.util.ArrayList;

//When values are inputed to the database, the count of values added are stored and added here
public class DBInputResponses {

	private static ArrayList<String> inputResponses = new ArrayList<String>();
	
	public static void addInputResponses(String message){
		inputResponses.add(message);
	}
	
	public static ArrayList<String> getInputResponses(){
		return inputResponses;
	}
	
	public static void clearInputResponses(){
		inputResponses.clear();
	}
	
}
