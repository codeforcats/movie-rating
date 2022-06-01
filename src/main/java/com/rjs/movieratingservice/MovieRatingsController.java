package com.rjs.movieratingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/movieRatings")
public class MovieRatingsController {

    @Autowired
    private MovieRatingsRepo movieRatingsRepo;

    @RequestMapping("/{userId}")
    public Collection<MovieRating> getMovieRatingsForUserId(@PathVariable String userId){
            return movieRatingsRepo.findMovieRatingsByuserId(userId).orElseThrow(ResourceNotFoundException::new);
    }

}
