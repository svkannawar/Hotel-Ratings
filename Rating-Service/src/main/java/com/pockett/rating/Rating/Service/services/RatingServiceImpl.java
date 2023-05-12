package com.pockett.rating.Rating.Service.services;

import com.pockett.rating.Rating.Service.entities.Rating;
import com.pockett.rating.Rating.Service.exceptions.ResourceNotFoundException;
import com.pockett.rating.Rating.Service.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with given id counld not be found"+ratingId));

    }

    @Override
    public List<Rating> getRatingbyUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingbyHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
