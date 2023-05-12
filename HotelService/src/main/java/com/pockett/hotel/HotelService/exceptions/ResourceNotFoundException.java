package com.pockett.hotel.HotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    //u can add ecxtra propertis that u want
    public ResourceNotFoundException(){

        super("Resource not found on server!!");
    }

    public ResourceNotFoundException(String message){

        super(message);
    }

}
