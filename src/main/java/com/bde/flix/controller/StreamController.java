package com.bde.flix.controller;

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
    public ResponseEntity<Optional<Film>> ServeFilm(@RequestBody FlixUUID id){
//        System.out.println(id);
//        System.out.println(id.toString());
//        System.out.println(id.getId());
        Optional<Film> film = filmservice.getFilm(id.getId());
        if(film.isPresent()){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.;
            return new ResponseEntity<>(film, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

//New class for UUID is needed, because Jackson needs getters for
//deserialization, and i can't add getId to java.util.UUID becuase
//that class is final smh
class FlixUUID {
    private UUID id;
    public UUID getId() {
        return id;
    }

}
