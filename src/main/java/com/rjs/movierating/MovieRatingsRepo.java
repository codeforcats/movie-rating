package com.rjs.movierating;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieRatingsRepo {
    public Optional<Collection<MovieRating>> findMovieRatingsByuserId(String userId) {

        if (userIdMovieRatingsMap.containsKey(userId))
            return Optional.ofNullable(userIdMovieRatingsMap.get(userId));
        else
            throw new ResourceNotFoundException();
    }

    private final Map<String, List<MovieRating>> userIdMovieRatingsMap =
        Map.of(
                "joe",
                Arrays.asList(
                        new MovieRating("Jaws", 4),
                        new MovieRating("StarWars", 3),
                        new MovieRating("Terminator", 7)),


                "hannah",
                Arrays.asList(
                        new MovieRating("Jaws", 3),
                        new MovieRating("Frozen", 3)),

                "jen",
                Arrays.asList()

        );

}
