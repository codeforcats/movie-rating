package com.rjs.movieratingservice;

import com.rjs.movieratingservice.model.MovieRating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieRatingServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldReturnHardCodedMovieRating(){
		MovieRating movieRating = restTemplate.getForObject("http://localhost:" + port + "/movieRatings/1",
				MovieRating.class);

		assertThat(movieRating).isEqualTo(new MovieRating(1,4));
	}
}
