package com.bde.flix.controller;


import com.bde.flix.controller.Payload.EpisodeRecord;
import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.service.EpisodeService;
import com.bde.flix.model.entity.content.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EpisodeController {

    @Autowired
    private EpisodeService episodeService;

    @PostMapping("api/episode")
    public EpisodeRecord test(@RequestBody Episode record) {
        Episode entity = episodeService.createEpisode(record.getSeason().getId(),
                                                      record.getNumber(),
                                                      record.getDescription(),
                                                      record.getPath(),
                                                      record.getDuration());
        return new EpisodeRecord(entity.getId(), entity.getDescription());
    }

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

    @PutMapping("api/episode")
    public ResponseEntity<HttpStatus> updateEpisode(@RequestBody Episode update) {
        if (episodeService.isExistEpisode(update.getId()))
        {
            episodeService.updateEpisode(update.getId(), update);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/episode")
    public ResponseEntity<Episode> getEpisodeById(@RequestBody IdRecord record) {
        if (episodeService.isExistEpisode(record.id())) {
            return new ResponseEntity<>(episodeService.getEpisode(record.id()).get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/episode/all")
    public ResponseEntity<List<Episode>> getEpisodeBySeason (@RequestBody IdRecord record) {
        try {
            List<Episode> result = new ArrayList<Episode>();

            if (record.id() == null)
                episodeService.getEpisodes().forEach(result::add);
            else
                episodeService.getEpisodeBySeason(record.id()).forEach(result::add);

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
