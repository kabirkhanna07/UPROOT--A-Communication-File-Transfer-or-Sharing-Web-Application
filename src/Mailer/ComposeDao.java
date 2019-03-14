package Mailer;
import java.sql.*;

public class ComposeDao {

	public static int save(String email, String reciver, String subject, String message) {
		int status=0;
		try
		{
			Connection con=ConProvider.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into Company_Mailer_Message(Sender,Reciver,Subject,Message,Trash,MessageDate) values(?,?,?,?,?,?)");
			ps.setString(1,email);
			ps.setString(2,reciver);
			ps.setString(3,subject);
			ps.setString(4,message);
			ps.setString(5,"no"); 
			ps.setDate(6,formatter.getCurrentDate());
			
			status=ps.executeUpdate();
						
		}
		catch(Exception e) 
		{
			System.out.println(e);
		}
				
		return status;
	}

}
