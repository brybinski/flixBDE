package com.bde.flix.controller;

import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.model.entity.content.Content;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.service.ContentService;
import com.bde.flix.service.FilmService;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.Valid;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class StreamController {
    @Autowired
    private FilmService filmservice;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("api/stream")
    public ResponseEntity<Optional<Film>> ServeFilm(@RequestBody IdRecord record){
        Optional<Film> film = filmservice.getFilm(record.id());
        if(film.isPresent()){
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

