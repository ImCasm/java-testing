package casm.javatests.movies.service;

import casm.javatests.movies.data.MovieRepository;
import casm.javatests.movies.model.Genre;
import casm.javatests.movies.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public List<Movie> findMoviesByGenre(Genre genre) {
        return findAll()
                .stream().filter(m -> m.getGenre().equals(genre))
                .collect(Collectors.toList());
    }

    public List<Movie> findMoviesByName(String name) {
        return findAll()
                .stream().filter(m -> m.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> findMoviesByMaxLength(int length) {
        return findAll()
                .stream()
                .filter(movie -> movie.getMinutes() <= length)
                .collect(Collectors.toList());
    }

    public List<Movie> findMoviesByTemplate(Movie movieTemplate) {
       return filter(movieTemplate);
    }

    private List<Movie> filter(Movie movie){

        if (movie.getGenre() == null && movie.getMinutes() == null && movie.getName() == null) {
            return new ArrayList<>();
        }

        return findAll()
                .stream()
                .filter(m -> {

                    boolean match = true;

                    if (movie.getName() != null) {
                        match &= m.getName().toLowerCase().contains(movie.getName().toLowerCase());
                    }

                    if (movie.getMinutes() != null) {
                        match &= m.getMinutes() <= movie.getMinutes();
                    }

                    if (movie.getGenre() != null) {
                        match &= m.getGenre().equals(movie.getGenre());
                    }

                    return match;

                }).collect(Collectors.toList());
    }
}
