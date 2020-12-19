package casm.javatests.movies.data;

import casm.javatests.movies.model.Genre;
import casm.javatests.movies.model.Movie;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MovieRepositoryJdbcTest {

    private static MovieRepository movieRepository;

    @BeforeClass
    public static void config() throws SQLException {
        DataSource dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sa", "sa");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        movieRepository = new MovieRepositoryJdbc(jdbcTemplate);

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql_scripts/test_data.sql"));
    }

    @Test
    public void loadAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        assertEquals(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION),
                new Movie(2, "Memento", 113, Genre.THRILLER),
                new Movie(3, "Matrix", 136, Genre.ACTION)
        ), movies);
    }

    @Test
    public void loadMovieById() {
        Movie movie = movieRepository.findById(1);
        assertEquals(new Movie(1, "Dark Knight", 152, Genre.ACTION), movie);
    }

    @Test
    public void insertMovie() {
        Movie movie = new Movie("Super 8", 112, Genre.THRILLER);
        movieRepository.saveOrUpdate(movie);
        Movie insertedMovie = movieRepository.findById(4);
        movie = new Movie(4, "Super 8", 112, Genre.THRILLER);
        assertEquals(movie, insertedMovie);
    }
}