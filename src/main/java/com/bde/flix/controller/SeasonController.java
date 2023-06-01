package com.bde.flix.controller;

import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.SeasonRecord;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.service.SeasonService;
import com.bde.flix.model.entity.content.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @PostMapping("api/season")
    public SeasonRecord CreateFilm(@RequestBody Season record)
    {
        Season entity = seasonService.createSeason(record.getNumber(),
                                                   record.getDescription(),
                                                   record.getSeries().getId());

        return new SeasonRecord(entity.getId(), entity.getDescription());
    }

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

    @PutMapping("api/season")
    public ResponseEntity<HttpStatus> updateSeason(@RequestBody Season update) {
        if (seasonService.isExistSeason(update.getId()))
        {
            seasonService.updateSeason(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/season")
    public ResponseEntity<Season> getSeasonById(@RequestBody IdRecord record) {
        if (seasonService.isExistSeason(record.id())) {
            return new ResponseEntity<>(seasonService.getSeason(record.id()).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/season/all")
    public ResponseEntity<List<Season>> getSeasonBySeries(@RequestBody IdRecord record) {
        try {
            List<Season> result = new ArrayList<Season>();

            if (record.id() == null)
                seasonService.getSeasons().forEach(result::add);
            else
                seasonService.getSeasonBySeries(record.id()).forEach(result::add);

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}