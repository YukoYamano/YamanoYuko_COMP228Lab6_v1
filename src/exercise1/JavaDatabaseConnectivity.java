package exercise1;

import java.sql.*;

/*
 * MEMO
 * 1.import
 * 2.load and register the driver
 * 3.Create connection
 * 4.Create a statement
 * 5.execute the query
 * 6.process the results
 * 7.close 
 * */

public class JavaDatabaseConnectivity {
	
	public static void main(String[]arg) throws Exception
	{
		String url ="jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
		String uname="COMP214F21_009_P_36";
		String pass= "password";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		
		Statement stmt=con.createStatement();

		ResultSet rs=stmt.executeQuery("select * from authors");
		while(rs.next())
		System.out.println(rs.getInt(1)+"  "+rs.getString(2));

		con.close();
		
	}

}
