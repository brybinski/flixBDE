package com.bde.flix.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CMSController
{
    private static final Boolean hardcodedTemplate = true;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/CMS")
    public CMS cms()
    {
        return new CMS(hardcodedTemplate);
    }
}
