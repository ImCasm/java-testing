package casm.javatests.movies.service;

import casm.javatests.movies.data.MovieRepository;
import casm.javatests.movies.model.Genre;
import casm.javatests.movies.model.Movie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Before
    public void config() {
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "There's Something About Mary", 119, Genre.COMEDY),
                        new Movie(4, "Super 8", 112, Genre.THRILLER),
                        new Movie(5, "Scream", 111, Genre.HORROR),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 136, Genre.ACTION)
                )
        );
    }

    private List<Integer> getMoviesIds(List<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }

    @Test
    public void returnMoviesByGenre() {
        List<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);
        assertEquals(Arrays.asList(3,6), getMoviesIds(movies));
    }

    @Test
    public void returnMoviesByName() {
        List<Movie> movies = movieService.findMoviesByName("t");
        assertEquals(Arrays.asList(1,2,3,7), getMoviesIds(movies));
    }

    @Test
    public void loadMovieByTemplate() {
        Movie movie = new Movie("s", 112, null);
        List<Movie> movies = movieService.findMoviesByTemplate(movie);
        assertEquals(Arrays.asList(4,5), getMoviesIds(movies));
    }

    @Test
    public void returnMoviesByMaxLength() {
        List<Movie> moviesByLength = movieService.findMoviesByMaxLength(136);
        assertEquals(Arrays.asList(2,3,4,5,6,7), getMoviesIds(moviesByLength));
    }
}