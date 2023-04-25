package com.bde.flix.Service;

import com.bde.flix.model.entity.content.Season;
import com.bde.flix.model.repository.SeasonRepository;
import com.bde.flix.model.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SeasonService
{
    SeasonRepository seasonRepo;
    SeriesRepository seriesRepo;

    @Autowired
    public SeasonService(
            SeasonRepository test,
            SeriesRepository seriesRepo)
    {
        this.seasonRepo = test;
        this.seriesRepo = seriesRepo;
    }

    public Season createSeason(
            int num,
            String description,
            UUID series)
    {
        Season instance = new Season();
        instance.setNumber(num);
        instance.setSeries(seriesRepo.getReferenceById(series));
        instance.setDescription(description);
        return seasonRepo.save(instance);
    }
}
