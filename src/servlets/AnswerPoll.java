package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AnswerDAO;
import DAO.PollDAO;
import DAO.UserAnswerDAO;
import DAO.UserDAO;
import POJO.Answer;
import POJO.Poll;
import POJO.User;
import POJO.UserAnswer;

public class AnswerPoll extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try 
		{
			String id = request.getParameter("answer");
			int idChosenAnswer = Integer.parseInt(id);
			UserAnswerDAO userAnswerDao = new UserAnswerDAO();
			UserDAO userDao = new UserDAO();
			AnswerDAO answerDAO = new AnswerDAO();
			PollDAO pollDao = new PollDAO();
			Cookie[] cookies = request.getCookies();
			User u = null;
			Answer answer = answerDAO.find(idChosenAnswer);
			
			if(cookies != null){
				for(Cookie c : cookies){
					if(c.getName().equals(LoginForm.LOGIN_COOKIE_NAME))					
						u = userDao.find(c.getValue());
				} 
			}
			if(u != null && answer != null)
			{
				Poll poll = pollDao.find(answer.getId_poll());
				UserAnswer ua = new UserAnswer(u.getId(), answer.getId_poll(), answer.getId());
				userAnswerDao.create(ua);
				request.setAttribute("poll", poll);
				getServletContext().getRequestDispatcher("/WEB-INF/views/seePollStats.jsp").forward(request, response);
			}
		}
		catch (SQLException e){
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
				e.printStackTrace();
		}
		
	}
}
