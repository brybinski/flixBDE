package com.bde.flix.controller;


import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.service.EpisodeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/episode")
    public ResponseEntity<HttpStatus> CreateEpisode(@RequestBody Episode record) {
        try
        {
            episodeService.createEpisode(
                    record.getSeason().getId(),
                    record.getNumber(),
                    record.getDescription(),
                    record.getPath(),
                    record.getDuration());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("api/episode")
    public ResponseEntity<HttpStatus> deleteEpisode(@RequestBody IdRecord record) {
        try
        {
            episodeService.deleteEpisode(record.id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("api/episode")
    public ResponseEntity<HttpStatus> updateEpisode(@RequestBody Episode update) {
        Optional<Episode> result = episodeService.getEpisode(update.getId());
        if (result.isPresent())
        {
            episodeService.updateEpisode(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_USER')")

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/episode")
    public ResponseEntity<Optional<Episode>> getEpisodeById(@RequestParam(required = true) UUID id) {
        Optional<Episode> result = episodeService.getEpisode(id);
        if (result.isPresent())
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/episode/all")
    public ResponseEntity<List<Episode>> getAllEpisodes() {
        try {
            List<Episode> episodes = episodeService.getEpisodes();
            if (!episodes.isEmpty()) {
                return new ResponseEntity<>(episodes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")

    @Transactional
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/episode/season")
    public ResponseEntity<List<Episode>> getEpisodeBySeason (@RequestBody IdRecord record) {
        try {
            List<Episode> result = episodeService.getEpisodeBySeason(record.id());

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
