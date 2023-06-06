package com.bde.flix.controller;


import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.SeriesRecord;
import com.bde.flix.controller.Payload.TitleRecord;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.content.Season;
import com.bde.flix.service.SeasonService;
import com.bde.flix.service.SeriesService;
import com.bde.flix.model.entity.content.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SeriesController {

    @Autowired
    private SeriesService seriesService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/series")
    public ResponseEntity<HttpStatus> CreateSeries(@RequestBody Series create)
    {
        seriesService.createSeries(
                create.getTitle(),
                create.getDuration(),
                create.getDescription(),
                create.getReleaseDate(),
                create.getPoster(),
                create.getDirector(),
                create.getActorsCast(),
                create.getGenreTag());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("api/series")
    public ResponseEntity<HttpStatus> deleteSeries(@RequestBody IdRecord record) {
//        try
//        {
            seriesService.deleteSeries(record.id());
            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("api/series")
    public ResponseEntity<HttpStatus> updateSeries(@RequestBody Series update) {
        Optional<Series> result = seriesService.getSeries(update.getId());
        if (result.isPresent())
        {
            seriesService.updateSeries(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/series")
    public ResponseEntity<Optional<Series>> getSeriesById(@RequestParam(required = true) UUID id) {
        Optional<Series> result = seriesService.getSeries(id);
        if (result.isPresent())
            return new ResponseEntity<>(seriesService.getSeries(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/series/all")
    public ResponseEntity<List<Series>> getAllSeries() {
        try {
            List<Series> serieses = seriesService.getSerieses();
            if (!serieses.isEmpty()) {
                return new ResponseEntity<>(serieses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/series/random")
    public ResponseEntity<List<Series>> getRandomSeries() {
        try {
            List<Series> randSeries = seriesService.getRandomSeries();
            if (!randSeries.isEmpty()) {
                return new ResponseEntity<>(randSeries, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/series/title")
    public ResponseEntity<List<Series>> getSeriesByTitle (@RequestBody TitleRecord record) {
        try {
            List<Series> result = seriesService.getSeriesByTitle(record.title());

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
