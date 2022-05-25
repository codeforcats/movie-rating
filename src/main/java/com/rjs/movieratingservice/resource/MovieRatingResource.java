package com.rjs.movieratingservice.resource;

import com.rjs.movieratingservice.model.MovieRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieRatings")
public class MovieRatingResource {

    @RequestMapping("/{movieId}")
    public MovieRating getMovieRating(@PathVariable int movieId) {
        return new MovieRating(1,4);
    }
}
