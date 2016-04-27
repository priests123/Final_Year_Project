package RailData;

import java.sql.*;


public class DbConnection {
	public static Connection con;
	
	public static void OpenConnection() throws ClassNotFoundException, SQLException{


		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=RailTransportation";			
			String user = "Rail";
			String pass = "Rail";
			
			Connection myConn = DriverManager.getConnection(URL,user,pass);
			
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT * from Route");
			
			while(myRs.next()){
				System.out.println(myRs.getString(4));
			}
		
		
		
		
	}
}
