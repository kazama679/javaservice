package com.ra.ss14.service;

import com.ra.ss14.model.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie save(Movie movie);
    Movie update(Long id, Movie movie);
    void delete(Long id);
}
