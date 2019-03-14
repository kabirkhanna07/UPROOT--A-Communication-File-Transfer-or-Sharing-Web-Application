package Mailer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String n,e,p,g,d,a,c,s,coun,co;
		
		n=request.getParameter("name");
		e=request.getParameter("email");
		p=request.getParameter("pass");
		g=request.getParameter("gender");
		d=request.getParameter("dob");
		a=request.getParameter("address");
		c=request.getParameter("city");
		s=request.getParameter("state");
		coun=request.getParameter("country");
		co=request.getParameter("contact");
		
		int Status=RegisterDao.save(n,e+"@uproot.com",p,g,d,a,c,s,coun,co);
		
		if(Status==1)
		{
			out.print("Sucessfully Registered");
			request.getRequestDispatcher("index.html").include(request,response);
		}
		else
		{
			out.print("Something Went Worng");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		
	}

}
