package Mailer;
import java.sql.*;
public class ConProvider {
static Connection con=null;
	
	public static Connection getConnection()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mailer","root","");
			
		}
		catch(Exception exp)
		{
			System.out.println(exp);
			return null;
		}
		return con;
	}
}
