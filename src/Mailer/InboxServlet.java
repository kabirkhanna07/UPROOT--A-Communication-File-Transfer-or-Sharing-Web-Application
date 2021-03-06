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


@WebServlet("/InboxServlet")

public class InboxServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("header.html").include(request, response);
		out.print("<link rel='stylesheet' type='text/css' href='inbox_style.css'>");
		
		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}else{
			String email=(String)session.getAttribute("email");
		
			String msg=(String)request.getAttribute("msg");
			if(msg != null) {
				out.println("<p>"+msg+"</p>");
			}
			
			try{
				Connection con=ConProvider.getConnection();
				PreparedStatement ps=con.prepareStatement("select * from Company_Mailer_Message where Reciver=? and Trash='no' order by MessageDate desc");
				ps.setString(1,email);
				ResultSet rs=ps.executeQuery();
				out.print("<main>");
				out.print("<nav>");
				out.print("<img src='logo1.png' width='33' height='30'> ");
				out.print("<img src='up.png' width='56' height='16'>");
				request.getRequestDispatcher("link.html").include(request, response);			
				out.print("</nav>");
				out.print("<article>");
				out.print("<h1>Inbox</h1>");			
				out.print("<table id='inbox'>");
				out.print("<th>Sender</th> <th>Subject</th>");
				while(rs.next())
				{
					out.print("<tr><td>"+rs.getString("Sender")+"</td><td><a href='ViewMailServlet?id="+rs.getString("id")+"'>"+rs.getString("Subject")+"</a></td></tr>");
				}
				out.print("</table>");
				out.print("</article>");
				out.print("<aside>");
				out.print("Welcome, "+email);
				out.print("</aside>");
				out.print("</main>");
				
				con.close();
			}
			catch(Exception e){out.print(e);}
		}
		
		
	/*	request.getRequestDispatcher("footer.html").include(request, response);
		out.close();*/
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
