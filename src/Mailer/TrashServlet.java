package Mailer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TrashServlet")
public class TrashServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		System.out.println("welcome to tarsh");
		//request.getRequestDispatcher("header.html").include(request, response);
		out.print("<link rel='stylesheet' type='text/css' href='inbox_style.css'>");
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}
		
		else
		{
			String email=(String)session.getAttribute("email");
			//out.print("<span style='float:right'>Hi, "+email+"</span>");

		
			try
			{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from Company_Mailer_Message where Reciver=? OR Sender=? and Trash=? order by MessageDate desc");
				ps.setString(1,email);
				ps.setString(2,email);
				ps.setString(3,"yes");
				ResultSet rs=ps.executeQuery();
				out.print("<main>");
				out.print("<nav>");
				out.print("<img src='logo1.png' width='33' height='30'> ");
				out.print("<img src='up.png' width='56' height='16'>");
				request.getRequestDispatcher("link.html").include(request, response);
				out.print("</nav>");
				out.print("<article>");
				out.print("<h1>TrashMails<hr></h1>");
				out.print("<table id ='inbox'>");
				out.print("<th>Sender</th><th>Subject</th><th><center>Permanent Delete</center></th><th>Restore</th>");
				while(rs.next()){
					out.print("<tr><td>"+rs.getString("Sender")+"</td><td><a href='ViewMailServlet?id="+rs.getString("id")+"'>"+rs.getString("Subject")+"</a></td>"
							+ "<td><a href='DeleteParam?mid="+rs.getString("ID")+"'><center>Delete Permanently</center></a></td>"
							+"<td><a href='RestoreServlet?mid="+rs.getString("ID")+"'><center>Restore Mail</center></a></td></tr>");
				}
				out.print("</table>");
				out.print("</article>");
				out.print("<aside>");
				out.print("Welcome, "+email);
				out.print("</aside>");
				out.print("</main>");
				
				con.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
