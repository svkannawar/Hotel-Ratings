package com.pockett.userService.Controllers;

import com.pockett.userService.entities.User;
import com.pockett.userService.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User usr = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }

    //get singke user
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelMethod")//because its calling these 2 services
//    @Retry(name="ratingHotelBreaker", fallbackMethod = "ratingHotelMethod")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelMethod" )
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){

        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //creating falback method for ratingHotelMethod

    public ResponseEntity<User> ratingHotelMethod(String userId, Exception ex){
       logger.info("fallback is executed: ", ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("dummy")
                .about("Dummmy user in falllback")
                .userId("1242")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    //all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }
}


