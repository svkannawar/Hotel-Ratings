package com.pockett.rating.Rating.Service.services;

import com.pockett.rating.Rating.Service.entities.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //allratings
    List<Rating> getAllRatings();

    //get a rating by rating id
    Rating getRatingByRatingId(String ratingId);
    //ratings by userid

    List<Rating> getRatingbyUserId(String userId);

    //ratings by hotelId
    List<Rating> getRatingbyHotelId(String hotelId);



}
