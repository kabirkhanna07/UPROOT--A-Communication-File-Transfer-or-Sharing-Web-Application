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
		request.getRequestDispatcher("header.html").include(request, response);
		request.getRequestDispatcher("link.html").include(request, response);
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}
		
		else
		{
			String email=(String)session.getAttribute("email");
			out.print("<span style='float:right'>Hi, "+email+"</span>");
			
			int id=Integer.parseInt(request.getParameter("id"));
			
			
			try
			{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from Company_Mailer_Message where id=?");
				ps.setInt(1,id);
				ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					out.print("<h1><b>Subject : </b>"+rs.getString("Subject")+"</h1><hr/>");
					out.print("<p><b>Message:</b><br/> "+rs.getString("Message")+" <br/> <b>Sent By: "+rs.getString("Sender")+"</b></p>");
					out.print("<p><a href='DeleteMailServlet?id="+id+"'> Delete Mail </a></p>");
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
