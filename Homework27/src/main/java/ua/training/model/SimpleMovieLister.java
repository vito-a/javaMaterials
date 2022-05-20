package ua.training.model;

import org.springframework.beans.factory.annotation.Autowired;
import ua.training.repository.MovieFinder;

public class SimpleMovieLister {

    private MovieFinder movieFinder;

    @Autowired
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }

    // ...
}