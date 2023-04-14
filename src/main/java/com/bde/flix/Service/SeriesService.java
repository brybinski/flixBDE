package com.bde.flix.Service;

import com.bde.flix.model.entity.content.Series;
import com.bde.flix.model.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeriesService {
    SeriesRepository serRepo;

    @Autowired
    public SeriesService(SeriesRepository repo){
        this.serRepo = repo;
    }

    public Series createSeries(String title,
                               String description,
                               int duration,
//                               LocalDate releaseDate,
                               String poster,
                               String director){


        Series instance = new Series();
//        TODO: check parameters...
        instance.setTitle(title);
        instance.setDescription(description);
        instance.setDuration(duration);
//        instance.setReleaseDate(releaseDate);
        instance.setPoster(poster);
        instance.setDirector(director);



        serRepo.save(instance);
        return instance;
    }
}

