package com.pockett.userService.services;

import com.pockett.userService.entities.Hotel;
import com.pockett.userService.entities.Rating;
import com.pockett.userService.entities.User;
import com.pockett.userService.external.services.HotelService;
import com.pockett.userService.repositories.UserRepository;
import com.pockett.userService.exceptions.ResourceNotFoundException;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {

        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given id not found: "+ userId));
        //fetch rating od above user from rating-service
       Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get hotel
            //set the hotel to rating
            //return rating
            //Below was done using resttemplate which is now replaced by FeignClient
           // ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

         user.setRatings(ratingList);
        return user;
    }
}
