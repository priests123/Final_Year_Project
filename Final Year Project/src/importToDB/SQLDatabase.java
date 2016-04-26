package importToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Display.inputResponses;

public class SQLDatabase {

	private static Connection DBConn;
	
	public static void OpenConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			System.out.println("No associated SQL driver");
			e1.printStackTrace();
		}
		
		String URL = "jdbc:sqlserver://mpp11-fyp.database.windows.net:1433;database=Rail_Transportation;user=mpp11@mpp11-fyp;password=BananaApple09!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";			
		
		
		try {
			DBConn = DriverManager.getConnection(URL);
		} catch (SQLException e) {
			System.out.println("No connection to RailTransportation database");
			e.printStackTrace();
		}
		if(!DBConn.equals(null)){
			System.out.println("Connected to the Rail Transportation Database");
		}
	}
	
	public static ArrayList<String> selectAllFromStation(String sql, int noOfColumns){
		ArrayList<String> allStations = new ArrayList<String>();
		try{
			ResultSet result = DBConn.createStatement().executeQuery(sql);
			while(result.next()){
				allStations.add(result.getString(1));
				if(noOfColumns == 2)
					allStations.add(result.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(sql + ", query failed");
			e.printStackTrace();
		}
		return allStations;
	}
	
	public static void addNewStations(HashSet<String> uniqueStationsInFile, String sql){
		ArrayList<String> uniqueStations = new ArrayList<String>(uniqueStationsInFile);
		int countAdded = 0;
		for(int a = 0; a < uniqueStations.size(); a++){
			try{
				PreparedStatement statement = DBConn.prepareStatement(sql);
				statement.setString(1, uniqueStations.get(a));
				int rowInserted = statement.executeUpdate();
				if(rowInserted > 0)
					countAdded++;
			} catch (SQLException e) {
				System.out.println("Failed to insert station value " + uniqueStations.get(a));
				e.printStackTrace();
			}	
		}
		inputResponses.clearInputResponses();
		inputResponses.addInoutResponses("New stations in file = " + uniqueStations.size());
		inputResponses.addInoutResponses("New stations added = " + countAdded);

		//System.out.println("Stations that should be added = " + uniqueStations.size());
		//System.out.println("Stations added = " + countAdded);
	}
	
	public static ArrayList<String> selectAllFromUnit(String sql){
		ArrayList<String> allUnitNumbers = new ArrayList<String>();
		try{
			ResultSet result = DBConn.createStatement().executeQuery(sql);
			while(result.next()){
				allUnitNumbers.add(result.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(sql + ", query failed");
			e.printStackTrace();
		}
		return allUnitNumbers;
	}
	
	public static void addNewUnitNumbers(HashSet<String> uniqueUnitNumbersInFile, String sql){
		ArrayList<String> uniqueUnitNumbers = new ArrayList<String>(uniqueUnitNumbersInFile);
		int countAdded = 0;
		for(int a = 0; a < uniqueUnitNumbers.size(); a++){
			try{
				PreparedStatement statement = DBConn.prepareStatement(sql);
				statement.setString(1, uniqueUnitNumbers.get(a));
				int rowInserted = statement.executeUpdate();
				if(rowInserted > 0)
					countAdded++;
			} catch (SQLException e) {
				System.out.println("Failed to insert unit number value " + uniqueUnitNumbers.get(a));
				e.printStackTrace();
			}	
		}
		
		inputResponses.addInoutResponses("New units in file = " + uniqueUnitNumbers.size());
		inputResponses.addInoutResponses("New units added = " + countAdded);
		
		
		//System.out.println("Unit numbers that should be added = " + uniqueUnitNumbers.size());
		//System.out.println("Unit numbers added = " + countAdded);
	}
	
	public static ArrayList<String> selectAllFromRoute(String sql, int noOfColumns){
		ArrayList<String> allRouteDetails = new ArrayList<String>();
		try{
			ResultSet result = DBConn.createStatement().executeQuery(sql);
			while(result.next()){
				allRouteDetails.add(result.getString(1));
				//if(noOfColumns == 2)
				allRouteDetails.add(result.getString(2));
				if(noOfColumns == 3)
					allRouteDetails.add(result.getString(3));
			}
		} catch (SQLException e) {
			System.out.println(sql + ", query failed");
			e.printStackTrace();
		}
		return allRouteDetails;
	}
	
	public static void addNewRoutes(ArrayList<String> uniqueRoutesInFile, String sql){
		ArrayList<String> uniqueRoutes = new ArrayList<String>(uniqueRoutesInFile);
		int countAdded = 0;
		for(int a = 0; a < uniqueRoutes.size(); a+=2){
			try{
				PreparedStatement statement = DBConn.prepareStatement(sql);
				statement.setString(1, uniqueRoutes.get(a));
				statement.setString(2, uniqueRoutes.get(a+1));
				int rowInserted = statement.executeUpdate();
				if(rowInserted > 0)
					countAdded++;
			} catch (SQLException e) {
				System.out.println("Failed to insert station value " + uniqueRoutes.get(a));
				e.printStackTrace();
			}	
		}
		
		inputResponses.addInoutResponses("New routes in file = " + uniqueRoutes.size()/2);
		inputResponses.addInoutResponses("New routes added = " + countAdded);
		
		//System.out.println("Routes that should be added = " + uniqueRoutes.size()/2);
		//System.out.println("Routes added = " + countAdded);
	}
	
	public static void addNewJourneys(ArrayList<ArrayList<String>> formattedData, String sql){
		int countAdded = 0;
		for(int a = 0; a<formattedData.size(); a++){
			try{
				PreparedStatement statement = DBConn.prepareStatement(sql);
				statement.setString(1, formattedData.get(a).get(0));
				statement.setString(2, formattedData.get(a).get(1));
				statement.setString(3, formattedData.get(a).get(2));
				statement.setString(4, formattedData.get(a).get(3));
				statement.setString(5, formattedData.get(a).get(4));
				statement.setString(6, formattedData.get(a).get(5));
				statement.setString(7, formattedData.get(a).get(6));
				int rowInserted = statement.executeUpdate();
				if(rowInserted > 0)
					countAdded++;
			} catch (SQLException e) {
				System.out.println("Failed to insert journey value " + formattedData.get(a));
				e.printStackTrace();
			}	
			
		}
		
		inputResponses.addInoutResponses("Records that should be added = " + formattedData.size());
		inputResponses.addInoutResponses("Records added = " + countAdded);
		
		//System.out.println("Journeys that should be added = " + formattedData.size());
		//System.out.println("Journeys added = " + countAdded);
	}
	
	public static Boolean isDuplicate(String sql){
		try{
			ResultSet result = DBConn.createStatement().executeQuery(sql);
			while(result.next()){
				return true;
			}
		} catch (SQLException e) {
			System.out.println(sql + ", query failed");
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<String> selectAllFromRoute2(String sql) {
		ArrayList<String> allRouteDetails = new ArrayList<String>();
		try{
			ResultSet result = DBConn.createStatement().executeQuery(sql);
			while(result.next()){
				allRouteDetails.add(result.getString(1));
			}
		} catch (SQLException e) {
			System.out.println(sql + ", query failed");
			e.printStackTrace();
		}
		return allRouteDetails;
	}
	
	public static ArrayList<ArrayList<String>> usrSearch(String sql, String type, String routeSelected, String dateTimeFrom, String dateTimeTo){
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		try{
			PreparedStatement statement = DBConn.prepareStatement(sql);
			if(type.equals("justRoute")){		
				statement.setString(1, routeSelected);
			}else if(type.equals("bothDateTime")){
				statement.setString(1, routeSelected);
				statement.setString(2, dateTimeFrom);
				statement.setString(3, dateTimeTo);
			}else if(type.equals("justFromDateTime")){
				statement.setString(1, routeSelected);
				statement.setString(2, dateTimeFrom);
			}else if(type.equals("justToDateTime")){
				statement.setString(1, routeSelected);
				statement.setString(2, dateTimeTo);
			}else{
				return results;
			}
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				ArrayList<String> row = new ArrayList<String>();
				row.add(resultSet.getString(1));
				row.add(resultSet.getString(2));
				row.add(resultSet.getString(3));
				row.add(resultSet.getString(4));
				row.add(resultSet.getString(5));
				row.add(resultSet.getString(6));
				row.add(resultSet.getString(7));
				results.add(row);
			}
		} catch (SQLException e) {
			System.out.println(sql + ", query failed");
			e.printStackTrace();
		}
		return results;
	}
	
}
