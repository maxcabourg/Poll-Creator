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

public class AnswerPoll extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try 
		{
			String id = request.getParameter("answer");
			String comment = request.getParameter("comment");
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
				CommentDAO commentDAO = new CommentDAO();
				if(comment.length() > 0 && comment != null)
				{			
					Comment commentary = new Comment(comment, u.getId(), poll.getId());
					commentDAO.create(commentary);
				}
				ArrayList<Comment> allComments = commentDAO.getAll(poll.getId());
				ArrayList<User> allUsers = new ArrayList<User>();
				for(int i = 0; i<allComments.size(); i++)
					allUsers.add(userDao.find(allComments.get(i).getId_user()));
				ArrayList<Answer> answers = answerDAO.findByPoll(poll);
				ArrayList<Double> rates = new ArrayList<Double>();
				for(Answer ans : answers)
					rates.add(userAnswerDao.getAnswersRates(poll, ans));
				request.setAttribute("allComments", allComments);
				request.setAttribute("allUsers", allUsers);
				request.setAttribute("answers", answers);
				request.setAttribute("rates", rates);
				getServletContext().getRequestDispatcher("/WEB-INF/views/seePollStats.jsp").forward(request, response);
			}
		}
		catch (SQLException e){
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
				e.printStackTrace();
		}
		
	}
}
