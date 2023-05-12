package com.bde.flix.controller.Generate;


import com.bde.flix.service.EpisodeService;
import com.bde.flix.model.entity.content.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EpisodeGenerateController {

    @Autowired
    private EpisodeService episodeService;

    @GetMapping("api/episode-create")
    public  EpisodeGenerateRecord test(@RequestParam(required = true) UUID season,
                                       @RequestParam(required = true) int num,
                                       @RequestParam(required = false, defaultValue = "opis") String dscrp,
                                       @RequestParam(required = false, defaultValue = "./") String path,
                                       @RequestParam(required = true) int dur
                                       ) {
        Episode entity = episodeService.createEpisode(season,num, dscrp, path, dur);

        return new EpisodeGenerateRecord(entity.getId(), entity.getDescription());
    }
}
