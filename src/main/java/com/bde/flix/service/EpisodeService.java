package com.bde.flix.service;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.repository.EpisodeRepository;
import com.bde.flix.model.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EpisodeService {
    EpisodeRepository epiRepo;
    SeasonRepository seasonRepo;

    @Autowired
    public EpisodeService(EpisodeRepository hist){
        this.epiRepo = hist;
    }

    public Episode createEpisode(UUID season, int num, String description, String path, int duration){

        Episode instance = new Episode();
        //TODO: uuid check
        instance.setSeason(seasonRepo.getReferenceById(season));
        instance.setNumber(num);
        instance.setDescription(description);
        instance.setPath(path);
        instance.setDuration(duration);
        epiRepo.save(instance);
        return instance;
    }
}

