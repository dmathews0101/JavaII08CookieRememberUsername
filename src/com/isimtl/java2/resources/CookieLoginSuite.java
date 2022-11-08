package com.isimtl.java2.resources;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieLoginSuite
 */
@WebServlet("/CookieLoginSuite")
public class CookieLoginSuite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	Cookie loginCookie;       

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieLoginSuite() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Login Page</title></head><body>");
		response.setContentType("text/html");
		Cookie[]cookies= request.getCookies();
		if(cookies!=null)
		{
			
			for (int i=0; i<cookies.length ; i++) 
			{
				String cookieName = cookies[i].getName();
				if (cookieName.equals("username")) 
				{					
						out.println("Welcome "+cookies[i].getValue()+"!  Cookie of "+cookies[i].getValue()+" is stored");		
						cookies[i].setMaxAge(30);

				}
				
				
			}
		}
		else
		{
			out.println("<h3>Page de login</h3>");
			out.println("<form action='CookieLoginSuite' method='Post'>Nom d'usager: "
					+ "<input type='text' name='firstname'>"
					+ "<br>Se rappeler de mon nom d'usager<input type='checkbox' name='remmember' value='yes' checked><br>"
					+ "<input type='submit' value='Soumettre'></form>");
			
		}
		out.println("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("remmember").equals("yes"))
		{
			Cookie newCookie=new Cookie("username",request.getParameter("firstname"));
			newCookie.setMaxAge(30);
			response.addCookie(newCookie);
			System.out.println("hello "+request.getParameter("firstname"));
		}
		//doGet(request, response);
	}

}
