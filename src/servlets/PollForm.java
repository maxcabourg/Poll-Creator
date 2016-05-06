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
import POJO.Answer;
import POJO.Poll;
import POJO.User;

public class PollForm extends HttpServlet{
	
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
					}
				}
			}
		}
		catch (SQLException e) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}
		if(u != null)
			getServletContext().getRequestDispatcher("/WEB-INF/views/createPoll.jsp").forward(request, response);
		else
			getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		/*Get current user*/
		User u = null;
		try
		{
			Cookie[] cookies = request.getCookies();
			if(cookies != null){
				for(Cookie c : cookies){
					if(c.getName().equals(LoginForm.LOGIN_COOKIE_NAME)){
						UserDAO dao = new UserDAO();
						u = dao.find(c.getValue());
					}
				}
			}
		}
		catch (SQLException e) {
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}
		if(u == null)
			getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
		
		 String question = request.getParameter("question");
		 Answer answer1 = new Answer(request.getParameter("answer1"));
		 Answer answer2 = new Answer(request.getParameter("answer2"));
		 Answer answer3 = new Answer(request.getParameter("answer3"));
		 Answer answer4 = new Answer(request.getParameter("answer4"));
		 ArrayList<Answer> answers = new ArrayList<Answer>();
		 if(answer1.getContent().length() != 0)
			 answers.add(answer1);
		 if(answer2.getContent().length() != 0)
			 answers.add(answer2);
		 if(answer3.getContent().length() != 0)
			 answers.add(answer3);
		 if(answer4.getContent().length() != 0)
			 answers.add(answer4);	 
		 try {
			 Poll poll = new Poll(0, question, answers, u);
			 PollDAO dao = new PollDAO();
			 dao.create(poll);
		} catch (SQLException e) {
			e.printStackTrace();
			getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
		}	 
		 getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
	}

}
