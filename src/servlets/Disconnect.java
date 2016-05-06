package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Disconnect extends HttpServlet{
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				if(c.getName().equals(LoginForm.LOGIN_COOKIE_NAME))
				{
					c.setValue(null);
	                c.setMaxAge(0);
	                c.setPath("/");
	                response.addCookie(c);
				}				
			}
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}
}