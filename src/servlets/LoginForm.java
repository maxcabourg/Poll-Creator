package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;

public class LoginForm extends HttpServlet{
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 String pseudo = request.getParameter("pseudo");
		 String password = request.getParameter("password");
		 UserDAO dao = new UserDAO();
		 try {
			if(dao.existsInDabatase(pseudo, password))
				getServletContext().getRequestDispatcher("/WEB-INF/views/accueil.jsp").forward(request, response);
			else
			{
				System.out.println("existe pas");
				request.setAttribute("error", "Invalid pseudo or password");
				getServletContext().getRequestDispatcher("/WEB-INF/views/formLogin.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
