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

public class DeletePoll extends HttpServlet{
	
	private User u = null;
	private UserDAO dao = new UserDAO();
	private PollDAO pollDao = new PollDAO();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String idToDelete = request.getParameter("id");
		if(idToDelete == null)
			getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
		int id = Integer.parseInt(idToDelete);		
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
			if(u != null)//User logged
			{
				Poll poll = pollDao.find(id);
				if(!pollDao.isCreatedBy(poll, u)) //If the current user is not the creator of the poll
					getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
				else
					doDelete(request, response);
			}
			else
				getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
		}
		catch (SQLException e) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}	
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String idToDelete = request.getParameter("id");
		int id = Integer.parseInt(idToDelete);
		try {
			Poll poll = pollDao.find(id);
			pollDao.delete(poll);
			request.setAttribute("user", u);
			ArrayList<Poll> polls = pollDao.findByUser(u);
			request.setAttribute("polls", polls);
			getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}
		
	}

}
