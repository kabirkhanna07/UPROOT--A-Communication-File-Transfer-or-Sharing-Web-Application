package Mailer;
import java.sql.*;

public class RegisterDao {
	
	static Connection con;
	public static int save(String n, String e, String p, String g, String d, String a, String c, String s,String coun, String co) {
		try
		{
			con=ConProvider.getConnection();
			java.sql.Date sqldob=formatter.getSqlDate(d);
			PreparedStatement Ps=con.prepareStatement("insert into Company_Mailer_User(Name,Email,Password,Gender,DOB,Address,City,State,Country,Contact,RegDate,Authorized) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			Ps.setString(1,n);
			Ps.setString(2,e);
			Ps.setString(3,p);
			Ps.setString(4,g);
			Ps.setDate(5,sqldob);
			Ps.setString(6,a);
			Ps.setString(7,c);
			Ps.setString(8,s);
			Ps.setString(9,coun);
			Ps.setString(10,co);
			Ps.setDate(11,formatter.getCurrentDate());
			Ps.setString(12,"YES");
			Ps.executeUpdate();
			return 1;
		}
		catch(Exception exp) {
			System.out.println(exp);
			return 0;
		}
		
	}

}
