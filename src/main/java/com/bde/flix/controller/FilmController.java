package com.bde.flix.controller;

import com.bde.flix.Service.FilmService;
import com.bde.flix.model.entity.content.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController
{

    @Autowired
    private FilmService filmService;
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/film")

    public FilmRecord GetCredentials(@RequestParam(required = true) String title,
                                     @RequestParam(required = true) int duration,
                                     @RequestParam(required = true) String description,
//                                   @RequestParam(required = true) String releaseDate,
                                     @RequestParam(required = true) String poster,
                                     @RequestParam(required = true) String director
//                                   @RequestParam(required = true) String actors_cast
                                     )
    {
        Film entity = filmService.createFilm(
                title,
                duration,
                description,
//              releaseDate,
                poster,
                director
        );

        return new FilmRecord(
                entity.getTitle(),
                entity.getDuration(),
                entity.getDescription(),
                "00.00.0000",
                entity.getPoster(),
                entity.getDirector(),
                "dummy value"
        );
    }
}
