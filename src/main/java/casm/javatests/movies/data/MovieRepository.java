package casm.javatests.movies.data;

import casm.javatests.movies.model.Movie;

import java.util.List;

public interface MovieRepository {
    Movie findById(long id);
    List<Movie> findAll();
    void saveOrUpdate(Movie movie);
}
