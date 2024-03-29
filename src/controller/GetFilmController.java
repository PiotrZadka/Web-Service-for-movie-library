package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.FilmDAO;

/**
 * Servlet implementation class GetFilmController
 */
@WebServlet("/GetFilm")
public class GetFilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetFilmController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO fdao = new FilmDAO();
		Film film = new Film();
		
		// Requesting ID for a film that is going to be retrieved from database
		int filmID = Integer.valueOf(request.getParameter("ID"));
		System.out.print(filmID);
		
		// Calling methord from DAO to retrieve film based on ID
		film = fdao.getFilmByID(filmID);
		System.out.print(film);
		
		request.setAttribute("film", film);
	    String format = request.getParameter("format");
	    String outputPage;
	    if ("xml".equals(format)) {
	      response.setContentType("text/xml");
	      outputPage = "/WEB-INF/results/film-xml.jsp";
	    } else if ("json".equals(format)) {
	      response.setContentType("application/json");
	      outputPage = "/WEB-INF/results/film-json.jsp";
	    } else {
	      response.setContentType("text/plain");
	      outputPage = "/WEB-INF/results/film-string.jsp";
	    }
	    
	    RequestDispatcher dispatcher =
	      request.getRequestDispatcher(outputPage);
	    dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
