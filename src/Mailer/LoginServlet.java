package Mailer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		/*Cookie C[]=request.getCookies();
		if(C==null)
		{
			System.out.println("First Run");
			Cookie C1=new Cookie("No. Of Time LoggedIN", "1");
			response.addCookie(C1);
		}
		else
		{
			System.out.println("Final Run");
			for(int i=0;i<C.length;i++) 
			{
				
			System.out.println(C[i].getName()+"="+C[i].getValue());
			}
			
			int c=Integer.parseInt(C[0].getValue());
			c=c+1;
			Cookie C1=new Cookie("No. Of Time LoggedIn", null);
			C1.setValue(""+c);
			response.addCookie(C1);
		}*/
		
		String l,p;
		l=request.getParameter("email");
		p=request.getParameter("pass");
		
					
		if(LoginDao.validate(l,p) == true)
		{
			HttpSession hs=request.getSession();
			hs.setAttribute("email", l);
			response.sendRedirect("InboxServlet");
		}
		else
		{
			out.print("Invalid LoginID or Password");
			request.getRequestDispatcher("login.html").include(request,response);
		}
	
	}

}
