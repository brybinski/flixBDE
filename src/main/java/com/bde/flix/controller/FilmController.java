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
public class FilmController
{
    @Autowired
    private FilmService filmService;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/api/film")
    public FilmRecord CreateFilm(@RequestBody Film create)
    {
        Film entity = filmService.createFilm(
                create.getTitle(),
                create.getDuration(),
                create.getDescription(),
                create.getReleaseDate(),
                create.getPoster(),
                create.getDirector(),
                create.getActorsCast(),
                create.getPath(),
                create.getGenreTag());

        return new FilmRecord(
                entity.getTitle(),
                entity.getDuration(),
                entity.getDescription(),
                entity.getReleaseDate(),
                entity.getPoster(),
                entity.getDirector(),
                entity.getActorsCast(),
                entity.getGenreTag()
        );
    }

    @DeleteMapping("api/film")
    public ResponseEntity<HttpStatus> deleteFilm(@RequestBody IdRecord record) {
        try
        {
            filmService.deleteFilm(record.id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("api/film")
    public ResponseEntity<HttpStatus> updateFilm(@RequestBody Film update) {
        if (filmService.isExistFilm(update.getId()))
        {
            filmService.updateFilm(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/film")
    public ResponseEntity<Film> getFilmById(@RequestBody IdRecord record) {
        if (filmService.isExistFilm(record.id())) {
            return new ResponseEntity<>(filmService.getFilm(record.id()).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/film/all")
    public ResponseEntity<List<Film>> getFilmByTitle (@RequestBody TitleRecord record) {
        try {
            List<Film> result = new ArrayList<Film>();

            if (record.title() == null)
                filmService.getFilms().forEach(result::add);
            else
                filmService.getFilmByTitle(record.title()).forEach(result::add);

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
