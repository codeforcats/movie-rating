package com.rjs.movieratingservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieRatingsController.class)
class MovieRatingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRatingsRepo mockMovieRatingsRepo;

    @Test
    public void userFound() throws Exception {
        Mockito.when(mockMovieRatingsRepo.getMovieRatingsForUser("joe")).thenReturn(Arrays.asList(
                new MovieRating("Jaws", 4),
                new MovieRating("Star Wars", 3)));

        mockMvc.perform(MockMvcRequestBuilders.get("/movieRatings/joe"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"movieId\":\"Jaws\",\"rating\":4},{\"movieId\":\"Star Wars\",\"rating\":3}]"));
    }

    @Test
    public void userNotFound() throws Exception{
        Mockito.when(mockMovieRatingsRepo.getMovieRatingsForUser("tom"))
                .thenThrow( new ResourceNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/movieRatings/tom"))
                .andExpect(status().isNotFound());
    }
}