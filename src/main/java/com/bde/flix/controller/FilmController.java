package com.bde.flix.controller;

import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.TitleRecord;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/film")
    public ResponseEntity<Optional<Film>> getFilmById(@RequestParam(required = true) UUID id) {
        Optional<Film> result = filmService.getFilm(id);
        if (result.isPresent())
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/film/all")
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/film/title")
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
