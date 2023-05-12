package com.pockett.rating.Rating.Service.controllers;

import com.pockett.rating.Rating.Service.entities.Rating;
import com.pockett.rating.Rating.Service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating){

        Rating rating1 = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

        //get All ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    //geta rating by rating id
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingsByRatingId(@PathVariable String ratingId){
        return ResponseEntity.ok(ratingService.getRatingByRatingId(ratingId));
    }

    //getRatingbyuserid
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingbyUserId(userId));
    }


    //getratingbyhotelid
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getRatingbyHotelId(hotelId));
    }


}
