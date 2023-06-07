package com.bde.flix.service;

import com.bde.flix.model.entity.content.Season;
import com.bde.flix.model.entity.content.Series;
import com.bde.flix.model.repository.SeasonRepository;
import com.bde.flix.model.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeriesService
{
    SeriesRepository serRepo;
    SeasonRepository seasonRepo;
    @Autowired
    private SeasonService seasonService;

    @Autowired
    public SeriesService(SeriesRepository seriesRepository, SeasonRepository seasonRepository)
    {
        this.serRepo = seriesRepository;
        this.seasonRepo = seasonRepository;
    }

    public Series createSeries(
            String title,
            int duration,
            String description,
            Date releaseDate,
            String poster,
            String director,
            Set<String> actors_cast,
            Set<String> genreTag
            )
    {
        Series instance = new Series();
//      TODO: check parameters...
        instance.setTitle(title);
        instance.setDuration(duration);
        instance.setDescription(description);
        instance.setReleaseDate(releaseDate);
        instance.setPoster(poster);
        instance.setDirector(director);
        instance.setActorsCast(actors_cast);
        instance.setGenreTag(genreTag);
        return serRepo.save(instance);
    }

    public void deleteSeries(UUID id)
    {
        List<Season> result = seasonRepo.findBySeries(serRepo.getById(id));
        result.forEach( (season) ->
        {
            if(season != null)
                seasonService.deleteSeason(season.getId());
        });
        serRepo.deleteById(id);
    }

    public void updateSeries(UUID id, Series update)
    {
        Optional<Series> data = getSeries(id);

        if (data.isPresent())
        {
            Series _series = data.get();
            _series.setTitle(update.getTitle());
            _series.setDuration(update.getDuration());
            _series.setDescription(update.getDescription());
            _series.setReleaseDate(update.getReleaseDate());
            _series.setPoster(update.getPoster());
            _series.setDirector(update.getDirector());
            _series.setActorsCast(update.getActorsCast());
            _series.setGenreTag(update.getGenreTag());
            serRepo.save(_series);
        }
    }

    public boolean isExistSeries(UUID id)
    {
        return serRepo.existsById(id);
    }

    public List<Series> getRandomSeries()
    {
        List<Series> serieses = getSerieses();
        Collections.shuffle(serieses);
        return serieses.subList(0, Math.min(serieses.size(), 10));
    }

    public Optional<Series> getSeries(UUID id)
    {
        return serRepo.findById(id);
    }

    public List<Series> getSerieses()
    {
        return serRepo.findAll();
    }

    public List<Series> getSeriesByTitle(String title)
    {
        return serRepo.findByTitleContaining(title);
    }
}

