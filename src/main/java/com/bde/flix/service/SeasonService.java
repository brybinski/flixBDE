package com.bde.flix.service;

import com.bde.flix.model.entity.content.Season;
import com.bde.flix.model.repository.SeasonRepository;
import com.bde.flix.model.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SeasonService {
    SeasonRepository seasonRepo;
    SeriesRepository seriesRepo;

    @Autowired
    public SeasonService(SeasonRepository test){
        this.seasonRepo = test;
    }

    public Season createSeason(int num, String description, UUID series){
        Season instance = new Season();
        //TODO: check parameters
        instance.setNumber(num);
        instance.setSeries(seriesRepo.getReferenceById(series));
        instance.setDescription(description);
        seasonRepo.save(instance);
        return instance;
    }
}
