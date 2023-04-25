package com.bde.flix.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController
{
    private String token = "abcdf";
    private int uuid = 54321;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/login")

    public Login GetCredentials(@RequestParam(required = true) String email,
                                @RequestParam(required = true) String password)
    {
        return new Login(token, uuid);
    }



}