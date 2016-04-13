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

public class SQLDatabase {

	private static Connection DBConn;
	
	public static void OpenConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e1) {
			System.out.println("No associated SQL driver");
			e1.printStackTrace();
		}
		
		String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=RailTransportation";			
		String user = "Rail";
		String pass = "Rail";
		
		try {
			DBConn = DriverManager.getConnection(URL,user,pass);
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
		System.out.println("Stations that should be added = " + uniqueStations.size());
		System.out.println("Stations added = " + countAdded);
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
		System.out.println("Unit numbers that should be added = " + uniqueUnitNumbers.size());
		System.out.println("Unit numbers added = " + countAdded);
	}
	
}