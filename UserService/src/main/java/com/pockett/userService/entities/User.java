package com.pockett.userService.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;

    private String name;


   // private String fName;

    //private String lName;

   // private int rollNo;


    private String email;
    private String about;

    //transient because database me save nahi karna hai
    @Transient
    private List<Rating> ratings = new ArrayList<>();


}

