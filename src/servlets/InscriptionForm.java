package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import POJO.User;

public class InscriptionForm extends HttpServlet{
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 getServletContext().getRequestDispatcher("/WEB-INF/views/formInscription.jsp").forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 String pseudo = request.getParameter("pseudo");
		 String password = request.getParameter("password");
		 String passwordConfirm = request.getParameter("passwordConfirm");
		 String email = request.getParameter("mail");
		 if(User.isValid(pseudo, password, email))
		 {
			 User u = new User(0, pseudo, password, email);
			 UserDAO dao = new UserDAO();
			 try {
				dao.create(u);
			} catch (SQLException e) {
				request.setAttribute("error", "This name or mail adress is already taken");
				getServletContext().getRequestDispatcher("/WEB-INF/views/formInscription.jsp").forward(request, response);
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/inscriptionDone.jsp").forward(request, response);
		 }
		 else
		 {
			 Map<String, String> errors = User.getErrors(pseudo, password, passwordConfirm, email);
			 request.setAttribute("errors", errors);
			 getServletContext().getRequestDispatcher("/WEB-INF/views/formInscription.jsp").forward(request, response);
		 }
		 
	}

}
