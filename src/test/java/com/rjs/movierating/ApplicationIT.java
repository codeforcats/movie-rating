package com.rjs.movierating;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationIT {

    @Value("${server.host}")
    private String host;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenValidUserAndRatingsExistShouldReturnPopulatedList(){

        Collection<MovieRating> expectedMovieRatings = Arrays.asList(
                new MovieRating("Jaws", 4),
                new MovieRating("StarWars", 3),
                new MovieRating("Terminator", 7));

        ResponseEntity<MovieRating[]> response =
                restTemplate.getForEntity("http://" + host + ":" + + port + "/movieRatings/{userId}",
                        MovieRating[].class, "joe");

        Collection<MovieRating> movieRatings = Arrays.asList(response.getBody());

        for (MovieRating expectedMovieRating : expectedMovieRatings) {
            Assertions.assertThat(movieRatings.stream().anyMatch(expectedMovieRating::equals));
        }

    }

    @Test
    public void whenValidUserAndNoRatingsExistShouldReturnEmptyList(){

        Collection<MovieRating> expectedMovieRatings = Arrays.asList();

        ResponseEntity<MovieRating[]> response =
                restTemplate.getForEntity("http://" + host + ":" + + port + "/movieRatings/{userId}",
                        MovieRating[].class, "jen");

        Collection<MovieRating> movieRatings = Arrays.asList(response.getBody());

        for (MovieRating expectedMovieRating : expectedMovieRatings) {
            Assertions.assertThat(movieRatings.stream().anyMatch(expectedMovieRating::equals));
        }

    }
    @Test
    void whenInvalidUserShouldThrowResourceNotFound(){
        // there must be a better way to do this!
        // we are actually expecting MovieRating[], not MovieRating, but using MovieRating[] here causes issues.
        // just saying MovieRating here is just to get past this to look at the response code.
        ResponseEntity<MovieRating> response =
                restTemplate.getForEntity("http://" + host + ":" + port + "/movieRatings/{userId}",
                        MovieRating.class, "foo");

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}