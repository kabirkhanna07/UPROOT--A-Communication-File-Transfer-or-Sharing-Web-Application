package Mailer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DeleteMailServlet")
public class DeleteMailServlet extends HttpServlet {
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
				PreparedStatement ps=con.prepareStatement("update Company_Mailer_Message set Trash=? where id=?");
				ps.setString(1,"yes");
				ps.setInt(2, id);
				int i= ps.executeUpdate();
				out.print("<main>");
				out.print("<nav>");
				request.getRequestDispatcher("link.html").include(request, response);
				out.print("</nav>");
				out.print("<article>");
				if(i>0)
				{
					request.setAttribute("msg", "Mail Successfully Deleted");
					request.getRequestDispatcher("InboxServlet").forward(request, response);
				}
				out.print("</article>");
				out.print("<aside>");
				out.print("</aside>");
				out.print("</main>");
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
