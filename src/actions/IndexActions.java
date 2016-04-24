package actions;

import bean.MoviePersonRoleView;
import constants.PagePath;
import dao.MovieDAO;
import dao.view.MoviePersonRoleDAO;
import entities.Movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

/**
 * Created by Алексей on 18.04.2016.
 */
public class IndexActions extends Action {

    @Override
    public PageAction execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        MoviePersonRoleDAO personMRDAO = new MoviePersonRoleDAO();
        Movie movie = movieDAO.getRecent();
//        Map <String,MoviePersonRoleView>map= new HashMap<String, MoviePersonRoleView>();

        MoviePersonRoleView director = null, oper = null, composer = null;
        try {
            director = personMRDAO.getPersonByMovieId(movie.getMovie_id(), "DIRECTOR").get(0);
            oper = personMRDAO.getPersonByMovieId(movie.getMovie_id(), " operator").get(0);
            composer = personMRDAO.getPersonByMovieId(movie.getMovie_id(), "composer").get(0);
        } catch (Exception e) {

        }
        request.setAttribute("popular_movies", movieDAO.getPopularMovies());
        request.setAttribute("country", movieDAO.getCountry(movie.getMovie_id()));
        request.setAttribute("movie", movie);
        request.setAttribute("director", director);
        request.setAttribute("oper", oper);
        request.setAttribute("composer", composer);
        return new PageAction(PagePath.INDEX, true);
    }
}
