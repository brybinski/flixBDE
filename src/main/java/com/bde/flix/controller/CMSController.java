package com.bde.flix.controller;

import com.bde.flix.model.entity.content.Content;
import com.bde.flix.service.ContentService;
import com.bde.flix.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CMSController
{
    @Autowired
    private FilmService filmService;

    private static final Boolean hardcodedTemplate = true;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/api/CMS")
    public CMS cms()
    {
        return new CMS(hardcodedTemplate);
    }


    @DeleteMapping("api/CMS/film/{title}")
    public ResponseEntity<HttpStatus> deleteFilm(@PathVariable("title") String title) {
        try
        {
            filmService.deleteFilm(title);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
