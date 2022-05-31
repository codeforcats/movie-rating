package com.rjs.movieratingservice;

import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRatingsRepo {
    public Collection<MovieRating> getMovieRatingsForUser(String userId) {
        if (userIdMovieRatingsMap.containsKey(userId))
            return userIdMovieRatingsMap.get(userId);
        else
            throw new ResourceNotFoundException();
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
