package com.bde.flix.controller;

import com.bde.flix.controller.Payload.FilmRecord;
import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.TitleRecord;
import com.bde.flix.service.FilmService;
import com.bde.flix.model.entity.content.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/api/film")
    public ResponseEntity<HttpStatus> CreateFilm(@RequestBody Film create) {
        try
        {
            filmService.createFilm(
                    create.getTitle(),
                    create.getDuration(),
                    create.getDescription(),
                    create.getReleaseDate(),
                    create.getPoster(),
                    create.getDirector(),
                    create.getPath(),
                    create.getActorsCast(),
                    create.getGenreTag());

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("api/film")
    public ResponseEntity<HttpStatus> deleteFilm(@RequestBody IdRecord record) {
        try
        {
            filmService.deleteFilm(record.id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("api/film")
    public ResponseEntity<HttpStatus> updateFilm(@RequestBody Film update) {
        Optional<Film> film = filmService.getFilm(update.getId());
        if (film.isPresent())
        {
            filmService.updateFilm(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/film")
    public ResponseEntity<Optional<Film>> getFilmById(@RequestBody IdRecord record) {
        Optional<Film> film = filmService.getFilm(record.id());
        if (film.isPresent())
            return new ResponseEntity<>(film, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/film/all")
    public ResponseEntity<List<Film>> getAllFilms() {
        try {
            List<Film> films = filmService.getFilms();
            if (!films.isEmpty()) {
                return new ResponseEntity<>(films, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/film/title")
    public ResponseEntity<List<Film>> getFilmByTitle(@RequestBody TitleRecord record) {
        try {
            List<Film> result = filmService.getFilmByTitle(record.title());
            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
