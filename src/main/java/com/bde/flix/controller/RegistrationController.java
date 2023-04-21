package com.bde.flix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController
{
//    TODO: mail sending function
//    private String token = "abcdf";
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/register")

    public Registration GetInfo(@RequestParam(required = true) String email,
                                @RequestParam(required = true) String password,
                                @RequestParam(required = true) String name,
                                @RequestParam(required = true) String surname)
    {
        return new Registration(HttpStatus.OK);
    }



}
