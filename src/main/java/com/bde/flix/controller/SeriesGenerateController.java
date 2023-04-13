package com.bde.flix.controller;


import com.bde.flix.Service.EpisodeService;
import com.bde.flix.Service.SeriesService;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDate;
import java.util.UUID;

@RestController
public class SeriesGenerateController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping("/series-create")
    public  SeriesGenerateRecord test(
                                       @RequestParam(required = true) String title,
                                       @RequestParam(required = false, defaultValue = "opis") String dscrp,
                                       @RequestParam(required = false, defaultValue = "./") String poster,
                                       @RequestParam(required = false, defaultValue = "./") String direc,
// FIXME pass localdate
// @RequestParam(required = false, defaultValue = "String") LocalDate rdate,
                                       @RequestParam(required = false) int dur
                                       ) {

        Series entity = seriesService.createSeries(title, dscrp, dur, poster, direc);

        return new SeriesGenerateRecord(entity.getId(), entity.getDescription());
    }
}
