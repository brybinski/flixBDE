package com.bde.flix.controller.Generate;

import com.bde.flix.service.SeasonService;
import com.bde.flix.model.entity.content.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SeasonGenerateController {

    @Autowired
    private SeasonService seasonService;

    @GetMapping("api/season-create")
    public SeasonGenerateRecord test(@RequestParam(required = true) UUID series,
                                      @RequestParam(required = true) int num,
                                      @RequestParam(required = false, defaultValue = "opis") String dscrp
    ) {
        Season entity = seasonService.createSeason(num, dscrp, series);

        return new SeasonGenerateRecord(entity.getId(), entity.getDescription());
    }
}