package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AnswerDAO;
import DAO.CommentDAO;
import DAO.PollDAO;
import DAO.UserAnswerDAO;
import DAO.UserDAO;
import POJO.Answer;
import POJO.Comment;
import POJO.Poll;
import POJO.User;
import POJO.UserAnswer;


public class PollDisplayer extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Display stats if user already answered
		String idToDisplay = request.getParameter("id");
		User u = null;
		Poll poll = null;
		UserAnswer ua = null;
		UserDAO userDao = new UserDAO();
		UserAnswerDAO dao2 = new UserAnswerDAO();
		try
		{
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie c : cookies){
					if(c.getName().equals(LoginForm.LOGIN_COOKIE_NAME)){				
						u = userDao.find(c.getValue());
					}
				}
			}
			if(idToDisplay == null)//Parameter not entered in database
				getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
			int idToSearch = Integer.parseInt(idToDisplay);
			PollDAO dao = new PollDAO();
			poll = dao.find(idToSearch);
			if(u != null && poll != null)		
				ua = dao2.find(u.getId(), poll.getId());
			else if(u == null) //User not logged -> send him to login
				getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
			else if(poll == null)
				getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
		}
		catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}
		request.setAttribute("poll", poll);
		if(ua != null) //Means the user already answered the poll -> display stats	
		{
			
			try {
				CommentDAO commentDAO = new CommentDAO();
				AnswerDAO answerDAO = new AnswerDAO();
				ArrayList<Comment> allComments;
				allComments = commentDAO.getAll(poll.getId());
				ArrayList<User> allUsers = new ArrayList<User>();
				ArrayList<Answer> answers = answerDAO.findByPoll(poll);
				ArrayList<Double> rates = new ArrayList<Double>();
				for(Answer ans : answers)
					rates.add(dao2.getAnswersRates(poll, ans));
				for(int i = 0; i<allComments.size(); i++)
					allUsers.add(userDao.find(allComments.get(i).getId_user()));
				request.setAttribute("allComments", allComments);
				request.setAttribute("allUsers", allUsers);
				request.setAttribute("answers", answers);
				request.setAttribute("rates", rates);
				getServletContext().getRequestDispatcher("/WEB-INF/views/seePollStats.jsp").forward(request, response);
			} catch (SQLException e) {
				getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
				e.printStackTrace();
			}			
		}
		else //User didnt answer so we show him the form to answer
			getServletContext().getRequestDispatcher("/WEB-INF/views/answerPoll.jsp").forward(request, response);
	}
}
