package exercise1;
//import javafx.application.Application;
//import javafx.stage.Stage;
import java.sql.*;
public class Main 
 {
	
	public static void main(String[] args)

//public void start(Stage primaryStage) 
{
try 
{
System.out.println("> Start Program ...");
Class.forName("oracle.jdbc.driver.OracleDriver");   //driver
System.out.println("> Driver Loaded successfully.");
//establish a connection
Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@ 199.212.26.208:1521:SQLD","COMP214F21_009_P_36", "password");
//COMP228_M20_001_8 is the Oracle user account: will be provided by your prof.
//password: is the password provided by your prof.
System.out.println("> Database connected successfully.");
} 
catch(Exception e) 
{
e.printStackTrace();
}
}
 }




