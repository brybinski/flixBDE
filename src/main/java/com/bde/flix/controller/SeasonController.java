package com.bde.flix.controller;

import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.model.entity.content.Season;
import com.bde.flix.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SeasonController {

    @Autowired
    private SeasonService seasonService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")

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
    @PreAuthorize("hasRole('ROLE_ADMIN')")

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

    @PreAuthorize("hasRole('ROLE_USER')")

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/season")
    public ResponseEntity<Optional<Season>> getSeasonById(@RequestParam(required = true) UUID id) {
        Optional<Season> result = seasonService.getSeason(id);
        if (result.isPresent())
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PreAuthorize("hasRole('ROLE_USER')")

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
    @PreAuthorize("hasRole('ROLE_USER')")

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