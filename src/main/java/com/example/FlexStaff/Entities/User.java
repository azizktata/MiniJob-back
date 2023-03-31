package com.example.FlexStaff.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

@MappedSuperclass
@Data
public class User {

    @NonNull
    @Column(name = "firstName")
    public String firstName;

    @NonNull
    @Column(name = "lastName")
    public String lastName;

    @NonNull
    @Column(name = "email")
    public String email;

    @NonNull
    @Column(name = "password")
    public String password;

    @Column(name = "region")
    public String region;


    public User(@NonNull String firstName, @NonNull String lastName, @NonNull String email, @NonNull String password, String region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.region = region;
    }
    public User(){

    }
}
