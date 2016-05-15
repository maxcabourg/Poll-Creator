package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import POJO.User;

public class LoginForm extends HttpServlet{
	
	public static final String LOGIN_COOKIE_NAME = "userConnected";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		User u = null;
		try
		{
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie c : cookies){
					if(c.getName().equals(LoginForm.LOGIN_COOKIE_NAME)){
						UserDAO dao = new UserDAO();
						u = dao.find(c.getValue());
						request.setAttribute("user", u);
					}
				}
			}
		}
		catch (SQLException e) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}
		if(u == null)
			getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
		else
			getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 User u = null;
		 String pseudo = request.getParameter("pseudo");
		 String password = request.getParameter("password");
		 UserDAO dao = new UserDAO();
		 try {
			if(dao.existsInDabatase(pseudo, password))
			{
				Cookie cookie = new Cookie(LOGIN_COOKIE_NAME,pseudo);
				cookie.setPath("/");
				cookie.setMaxAge(60*60); //1 hour
				response.addCookie(cookie);
				u = dao.find(pseudo);
				request.setAttribute("user", u);
				getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
			}
			else
			{
				request.setAttribute("error", "Invalid pseudo or password");
				getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
