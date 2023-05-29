package com.bde.flix.service;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Season;
import com.bde.flix.model.repository.SeasonRepository;
import com.bde.flix.model.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void deleteSeason(UUID id)
    {
        seasonRepo.deleteById(id);
    }

    public void updateSeason(UUID id, Season update)
    {
        Optional<Season> data = getSeason(id);

        if (data.isPresent())
        {
            Season _season = data.get();
            _season.setNumber(update.getNumber());
            _season.setDescription(update.getDescription());
            seasonRepo.save(_season);
        }
    }

    public boolean isExistSeason(UUID id)
    {
        return seasonRepo.existsById(id);
    }

    public Optional<Season> getSeason(UUID id)
    {
        return seasonRepo.findById(id);
    }

    public List<Season> getSeasons()
    {
        return seasonRepo.findAll();
    }

    public List<Season> getSeasonBySeries(UUID id)
    {
        return seasonRepo.findBySeries(seriesRepo.getReferenceById(id));
    }
}
