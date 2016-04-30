package servlets;

import java.io.IOException;
import java.sql.SQLException;

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
		 String email = request.getParameter("mail");
		 System.out.println(pseudo+" "+password+" "+email);
		 if(User.isValid())
		 {
			 User u = new User(0, pseudo, password, email);
			 UserDAO dao = new UserDAO();
			 try {
				dao.create(u);
			} catch (SQLException e) {
				getServletContext().getRequestDispatcher("/WEB-INF/views/SQLerror.jsp").forward(request, response);
			}
		 }
		 getServletContext().getRequestDispatcher("/WEB-INF/views/inscriptionDone.jsp").forward(request, response);
	}

}
