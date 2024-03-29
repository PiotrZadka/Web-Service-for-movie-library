package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FilmDAO;

/**
 * Servlet implementation class DeleteFilmController
 */

// Class that handles get request for removing movie from database and passing the result to .jsp file

@WebServlet("/DeleteFilm")
public class DeleteFilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteFilmController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteMessage = null;
		// Requesting ID number of film that is going to be deleted
		int filmID = Integer.valueOf(request.getParameter("ID"));
		FilmDAO fdao = new FilmDAO();
		// Calling method to remove the movie based on requested ID
		if(fdao.deleteFilm(filmID)) {
			deleteMessage = "Film with ID "+filmID+" deleted successfully";
		}else {
			deleteMessage = "Film with ID "+filmID+" failed to delete from database";
		}

		request.setAttribute("message", deleteMessage);
	    response.setContentType("text/plain");
	    String outputPage = "/WEB-INF/results/filmInsert.jsp";
	    
	    RequestDispatcher dispatcher =
	      request.getRequestDispatcher(outputPage);
	    dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
