package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PollDAO;
import DAO.UserDAO;
import POJO.Poll;
import POJO.User;

public class Profile extends HttpServlet{
	
	private User u = null;
	private UserDAO dao = new UserDAO();
	private PollDAO pollDao = new PollDAO();
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		UserDAO dao = new UserDAO();
		PollDAO pollDao = new PollDAO();
		try
		{
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie c : cookies){
					if(c.getName().equals(LoginForm.LOGIN_COOKIE_NAME)){					
						u = dao.find(c.getValue());
						request.setAttribute("user", u);
					}
				}
			}
			if(u != null)
			{
				ArrayList<Poll> polls = pollDao.findByUser(u);
				request.setAttribute("user", u);
				request.setAttribute("polls", polls);
				getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
			}
			else
				getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
		}
		catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}	 
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{		
		if(request.getParameter("put") != null)
			doPut(request, response);
	}
	
	public void doPut( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		if(u != null)
		{
			
			try {
				ArrayList<Poll> polls = pollDao.findByUser(u);
				u.setPseudo(request.getParameter("pseudo"));
				u.setEmail(request.getParameter("mail"));
				u.setPassword(request.getParameter("password"));
				if(u.validUpdate())
				{	
					UserDAO dao = new UserDAO();		
					dao.update(u);
				}
				else
				{				
					request.setAttribute("invalidParameter", "Invalid parameter");
				}
				request.setAttribute("user", u);
				request.setAttribute("polls", polls);
				Cookie cookie = new Cookie(LoginForm.LOGIN_COOKIE_NAME, u.getPseudo()); //Update cookie
				cookie.setPath("/");
				cookie.setMaxAge(60*60); //1 hour
				response.addCookie(cookie);
				getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
			}
		}		
		else
			getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
			
	}

}
