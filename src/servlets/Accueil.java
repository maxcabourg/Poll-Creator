package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import POJO.User;

public class Accueil extends HttpServlet{

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
		 
		 getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}
}
