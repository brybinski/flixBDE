package com.bde.flix.controller;

import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.SeasonRecord;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Series;
import com.bde.flix.service.SeasonService;
import com.bde.flix.model.entity.content.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SeasonController {

    @Autowired
    private SeasonService seasonService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/season")
    public ResponseEntity<HttpStatus> CreateSeason(@RequestBody Season record)
    {
        seasonService.createSeason(
                record.getNumber(),
                record.getDescription(),
                record.getSeries().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("api/season")
    public ResponseEntity<HttpStatus> deleteSeason(@RequestBody IdRecord record) {
        try
        {
            seasonService.deleteSeason(record.id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("api/season")
    public ResponseEntity<HttpStatus> updateSeason(@RequestBody Season update) {
        Optional<Season> result = seasonService.getSeason(update.getId());
        if (result.isPresent())
        {
            seasonService.updateSeason(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/season")
    public ResponseEntity<Optional<Season>> getSeasonById(@RequestParam(required = true) UUID id) {
        Optional<Season> result = seasonService.getSeason(id);
        if (result.isPresent())
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/season/all")
    public ResponseEntity<List<Season>> getAllSeasons() {
        try {
            List<Season> seasons = seasonService.getSeasons();
            if (!seasons.isEmpty()) {
                return new ResponseEntity<>(seasons, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/season/series")
    public ResponseEntity<List<Season>> getSeasonBySeries(@RequestBody IdRecord record) {
        try {
            List<Season> result = seasonService.getSeasonBySeries(record.id());

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}