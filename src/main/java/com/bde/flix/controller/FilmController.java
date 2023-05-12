package com.bde.flix.controller;

import com.bde.flix.service.FilmService;
import com.bde.flix.model.entity.content.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
public class FilmController
{
    @Autowired
    private FilmService filmService;
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/api/film")
    public FilmRecord GetCredentials(@RequestParam(required = true, defaultValue = "test title") String title,
                                     @RequestParam(required = true, defaultValue = "0") int duration,
                                     @RequestParam(required = true, defaultValue = "Test description" ) String description,
//                                   @RequestParam(required = true) String releaseDate,
                                     @RequestParam(required = true, defaultValue = "posters/test_poster") String poster,
                                     @RequestParam(required = true, defaultValue = "Test director") String director,
                                     @RequestParam(required = true, defaultValue = "Actor1, Actor2, Actor3" ) Set<String> actors_cast,
                                     @RequestParam(required = true, defaultValue = "tag1, tag2, tag3") Set<String>genreTags
                                     )
    {
        Film entity = filmService.createFilm(
                title,
                duration,
                description,
//              releaseDate,
                poster,
                director,
                actors_cast,
                genreTags);

        return new FilmRecord(
                entity.getTitle(),
                entity.getDuration(),
                entity.getDescription(),
                "00.00.0000",
                entity.getPoster(),
                entity.getDirector(),
                entity.getActorsCast(),
                entity.getGenreTag()
        );
    }
}
