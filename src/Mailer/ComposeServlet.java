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


@WebServlet("/ComposeServlet")
public class ComposeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<link rel='stylesheet' type='text/css' href='inbox_style.css'>");
		//request.getRequestDispatcher("header.html").include(request, response);
		//request.getRequestDispatcher("link.html").include(request, response);
		
		
		HttpSession session=request.getSession(false);
		if(session==null)
		{
			response.sendRedirect("index.html");
		}
		else{
			String email=(String)session.getAttribute("email");
			out.print("<main>");
			out.print("<nav>");
			out.print("<img src='logo1.png' width='33' height='30'> ");
			out.print("<img src='up.png' width='56' height='16'>");
			request.getRequestDispatcher("link.html").include(request, response);
			out.print("</nav>");
			out.print("<article>");
			String msg=(String)request.getAttribute("msg");
			if(msg!=null){
				out.print("<p>"+msg+"</p>");
			}
		request.getRequestDispatcher("compose.html").include(request, response);
		out.print("</article>");
		out.print("<aside>");
		out.print("Welcome, "+email);
		out.print("</aside>");
		out.print("</main>");
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
