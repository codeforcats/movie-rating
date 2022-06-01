package com.rjs.movieratingservice;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieRatingsRepo {
    public Optional<Collection<MovieRating>> findMovieRatingsByuserId(String userId) {
        return Optional.of(userIdMovieRatingsMap.get(userId));
    }

    private final Map<String, List<MovieRating>> userIdMovieRatingsMap =
        Map.of(
                "joe",
                Arrays.asList(
                        new MovieRating("Jaws", 4),
                        new MovieRating("Star Wars", 3),
                        new MovieRating("Terminator", 7)),


                "hannah",
                Arrays.asList(
                        new MovieRating("Jaws", 3),
                        new MovieRating("Frozen", 3))
        );

}
