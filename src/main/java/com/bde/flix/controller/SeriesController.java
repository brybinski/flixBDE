package com.bde.flix.controller;


import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.SeriesRecord;
import com.bde.flix.controller.Payload.TitleRecord;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.content.Season;
import com.bde.flix.service.SeriesService;
import com.bde.flix.model.entity.content.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeriesController {

    @Autowired
    private SeriesService seriesService;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/series")
    public SeriesRecord CreateFilm(@RequestBody Series create) {

        Series entity = seriesService.createSeries(create.getTitle(),
                                                   create.getDuration(),
                                                   create.getDescription(),
                                                   create.getReleaseDate(),
                                                   create.getPoster(),
                                                   create.getDirector(),
                                                   create.getActorsCast(),
                                                   create.getGenreTag());
        return new SeriesRecord(entity.getId(), entity.getDescription());
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("api/series")
    public ResponseEntity<HttpStatus> deleteSeries(@RequestBody IdRecord record) {
        try
        {
            seriesService.deleteSeries(record.id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("api/series")
    public ResponseEntity<HttpStatus> updateSeries(@RequestBody Series update) {
        if (seriesService.isExistSeries(update.getId()))
        {
            seriesService.updateSeries(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/series")
    public ResponseEntity<Series> getSeriesById(@RequestBody IdRecord record) {
        if (seriesService.isExistSeries(record.id())) {
            return new ResponseEntity<>(seriesService.getSeries(record.id()).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/series/all")
    public ResponseEntity<List<Series>> getSeriesByTitle (@RequestBody TitleRecord record) {
        try {
            List<Series> result = new ArrayList<Series>();

            if (record.title() == null)
                seriesService.getSerieses().forEach(result::add);
            else
                seriesService.getSeriesByTitle(record.title()).forEach(result::add);

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
