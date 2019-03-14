package Mailer;
import java.sql.*;
public class LoginDao {
	static Connection con;
	public static boolean validate(String l,String p)
	{
		try 
		{
			con=ConProvider.getConnection();
			PreparedStatement Ps=con.prepareStatement("select * from Company_Mailer_User where Email=? and Password=? and Authorized=?");
			Ps.setString(1,l);
			Ps.setString(2,p);
			Ps.setString(3,"YES");
			ResultSet rs= null;
			rs=Ps.executeQuery();
			if(rs.next())
			{
				return true;
			}
			else

			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}

}
