package com.pockett.rating.Rating.Service.repositories;

import com.pockett.rating.Rating.Service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    //custom finder methods
    //find by userId
    List<Rating> findByUserId(String userId);


    //findbyhotelId
    List<Rating> findByHotelId(String hotelId);


}
