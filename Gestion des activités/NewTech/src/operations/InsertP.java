package operations;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DataBase;
import entity.Partenaire;

/**
 * Servlet implementation class InsertP
 */
@WebServlet("/InsertP")
public class InsertP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		PreparedStatement ps;
		Connection con;
		
		
		try {
			con = DataBase.creerCon();
			if (con != null) {
				System.err.println("ok");
			}
			else
			{
				System.err.println("no");
			}
			
			
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			

					
			ps = con.prepareStatement("insert into partenaire(Nom_partenaire,Email) values (?,?)"); 
	        
	       
	        ps.setString(1, nom);
	        ps.setString(2, email);
	        
	        int i = ps.executeUpdate();
	        if (i>0) {
	        	Partenaire p = new Partenaire();
	        	
	        	p.setNom(nom);
	        	p.setEmail(email);
	        	request.setAttribute("partenaire", p);
	    		request.getRequestDispatcher("/insertionP.jsp").forward(request, response);

	        }
	        else {
	        	out.println("erreur d'insertion");
	    		request.getRequestDispatcher("/gestionPartenaire.jsp").include(request, response);

	        }
		 
		 } 
		 catch (ClassNotFoundException e) {
		 System.err.println(e.getMessage());
		 } catch (SQLException e) {
			 System.err.println(e.getMessage());

		}

	}

}
