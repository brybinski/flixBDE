package com.bde.flix.service;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.repository.EpisodeRepository;
import com.bde.flix.model.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EpisodeService
{
    EpisodeRepository epiRepo;
    SeasonRepository seasonRepo;

    @Autowired
    public EpisodeService(EpisodeRepository hist, SeasonRepository seasonRepo)
    {
        this.epiRepo = hist;
        this.seasonRepo = seasonRepo;
    }

    public Episode createEpisode(UUID season, int num, String description, String path, int duration)
    {
        Episode instance = new Episode();
        instance.setSeason(seasonRepo.getReferenceById(season));
        instance.setNumber(num);
        instance.setDescription(description);
        instance.setPath(path);
        instance.setDuration(duration);
        return epiRepo.save(instance);
    }

    public void deleteEpisode(UUID id)
    {
        epiRepo.deleteById(id);
    }

    public void updateEpisode(UUID id, Episode update)
    {
        Optional<Episode> data = getEpisode(id);

        if (data.isPresent())
        {
            Episode _episode = data.get();
            _episode.setNumber(update.getNumber());
            _episode.setDescription(update.getDescription());
            _episode.setPath(update.getPath());
            _episode.setDuration(update.getDuration());
            epiRepo.save(_episode);
        }
    }

    public boolean isExistEpisode(UUID id)
    {
        return epiRepo.existsById(id);
    }

    public Optional<Episode> getEpisode(UUID id)
    {
        return epiRepo.findById(id);
    }

    public List<Episode> getEpisodes()
    {
        return epiRepo.findAll();
    }

    public List<Episode> getEpisodeBySeason(UUID id)
    {
        return epiRepo.findBySeason(seasonRepo.getReferenceById(id));
    }

}

