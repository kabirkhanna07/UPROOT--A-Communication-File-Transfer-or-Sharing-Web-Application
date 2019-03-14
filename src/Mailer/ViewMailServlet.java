package Mailer;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ViewMailServlet")
public class ViewMailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
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
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			
			try
			{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from Company_Mailer_Message where id=?");
				ps.setInt(1,id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					out.print("<main>");
					out.print("<nav>");
					out.print("<img src='logo1.png' width='33' height='30'> ");
					out.print("<img src='up.png' width='56' height='16'>");
					request.getRequestDispatcher("link.html").include(request, response);
					out.print("</nav>");
					out.print("<article>");
					out.print("<h1><b>Subject : </b></hr>"+rs.getString("Subject")+"<hr></h1>");
					out.print("<b>Message:</b><br><table id='inbox'><tr><td>"+rs.getString("Message")+" </td><tr></table><br><br> <b>Sent By: "+rs.getString("Sender")+"</b><br><br>");
					out.print("<a href='DeleteMailServlet?id="+id+"'>Delete Mail </a>");
					out.print("</article>");
					out.print("<aside>");
					out.print("Welcome, "+email);
					out.print("</aside>");
					out.print("</main>");
				}
			}	
			catch(Exception exp)
			{
				System.out.println(exp);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
