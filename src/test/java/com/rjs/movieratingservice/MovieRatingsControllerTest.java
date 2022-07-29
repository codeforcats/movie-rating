package com.rjs.movieratingservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieRatingsController.class)
class MovieRatingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRatingsRepo mockMovieRatingsRepo;

    @Test
    public void whenRatingsExistForValidUserReturnPopulatedList() throws Exception {
        Mockito.when(mockMovieRatingsRepo.findMovieRatingsByuserId("joe")).thenReturn(
                Optional.of(Arrays.asList(
                new MovieRating("Jaws", 4),
                new MovieRating("Star Wars", 3))));

        mockMvc.perform(MockMvcRequestBuilders.get("/movieRatings/{userId}", "joe"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"movieId\":\"Jaws\",\"rating\":4},{\"movieId\":\"Star Wars\",\"rating\":3}]"));
    }

    @Test
    public void whenNoRatingsFoundForValidUserShouldReturnEmptyList() throws Exception{
        Mockito.when(mockMovieRatingsRepo.findMovieRatingsByuserId("jen"))
                .thenReturn(Optional.of(new ArrayList<>()));

        mockMvc.perform(MockMvcRequestBuilders.get("/movieRatings/{userId}}", "jen"))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    @Test
    public void whenInvalidUserShouldThrowResourceNotFoundException() throws Exception{
        Mockito.when(mockMovieRatingsRepo.findMovieRatingsByuserId("foo"))
                .thenThrow(new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/movieRatings/{userId}}", "foo"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}