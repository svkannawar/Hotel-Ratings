package com.pockett.userService.external.services;

import com.pockett.userService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    //get

    //post
    @PostMapping("/ratings")
    Rating createRating(Rating rating);//yaha pe Map<key, values> map bhi use kr skte hai aur uske base pe object

    //put
    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);


    //delete rating
    @DeleteMapping("ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);

}
