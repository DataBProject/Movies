package dao;

import entities.UserMovieFavorite;
import util.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Станислав on 16.03.16.
 */
public class UserMovieFavoriteDAO {
    private static final String GET_MOVIE_ID_FAVORITE_MOVIE_USER ="SELECT * FROM user_movie_favorite WHERE user_ID =?";
    private static final String DELETE_MOVIE_FAVORITE_FROM_USER="DELETE FROM user_movie_favorite WHERE user_ID=? AND movie_ID=?";
    private static final String INSERT_MOVIE_FAVORITE_FROM_USER="INSERT INTO user_movie_favorite (user_movie_favorite_ID,user_ID,movie_ID) VALUES(NULL, ? , ? )";
    Connection connection;

    public void deleteMovieIdFavoriteUser(UserMovieFavorite userMovieFavorite)throws SQLException{
        int user_ID= userMovieFavorite.getUserID();
        int movie_ID =userMovieFavorite.getMovieID();
        PreparedStatement statement = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(DELETE_MOVIE_FAVORITE_FROM_USER);
            statement.setInt(1, user_ID);
            statement.setInt(2,movie_ID);
            statement.executeQuery();
        } finally {
            Connector.close(statement);
        }
    }

    public List<UserMovieFavorite> getMovieIdFavoriteUser(int user_id)throws SQLException{
        List<UserMovieFavorite> userMovieFavoritesList = new ArrayList<UserMovieFavorite>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(GET_MOVIE_ID_FAVORITE_MOVIE_USER);
            statement.setInt(1, user_id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                  userMovieFavoritesList.add(obtain(resultSet));
            }
        } finally {
            Connector.close(statement);
            Connector.close(resultSet);
        }
        return userMovieFavoritesList;
    }

    private UserMovieFavorite obtain(ResultSet resultSet) throws SQLException {
        UserMovieFavorite userMovieFavorite = new UserMovieFavorite();
        userMovieFavorite.setMovieID(resultSet.getInt("movie_ID"));
        userMovieFavorite.setUserID(resultSet.getInt("user_ID"));
        userMovieFavorite.setUserMovieFavoriteID(resultSet.getInt("user_movie_favorite_ID"));
    return userMovieFavorite;
    }

    public void AddMovieIdFavoriteUser(UserMovieFavorite userMovieFavorite) throws SQLException{
        int userId= userMovieFavorite.getUserID();
        int movieId = userMovieFavorite.getMovieID();
        PreparedStatement statement = null;
        try {
            connection = Connector.getConnection();
            statement = connection.prepareStatement(INSERT_MOVIE_FAVORITE_FROM_USER);
            statement.setInt(1, userId);
            statement.setInt(2,movieId);
            statement.executeQuery();
        } finally {
            Connector.close(statement);
        }
    }
}
