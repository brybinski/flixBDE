package com.bde.flix.controller.Generate;


import com.bde.flix.service.SeriesService;
import com.bde.flix.model.entity.content.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeriesGenerateController {

    @Autowired
    private SeriesService seriesService;

    @GetMapping("api/series-create")
    public  SeriesGenerateRecord test(
                                       @RequestParam(required = true) String title,
                                       @RequestParam(required = false, defaultValue = "opis") String dscrp,
                                       @RequestParam(required = false, defaultValue = "./") String poster,
                                       @RequestParam(required = false, defaultValue = "none") String direc,
// FIXME: pass localdate
// @RequestParam(required = false, defaultValue = "String") LocalDate rdate,
                                       @RequestParam(required = false) int dur
                                       ) {

        Series entity = seriesService.createSeries(title, dscrp, dur, poster, direc);

        return new SeriesGenerateRecord(entity.getId(), entity.getDescription());
    }
}
