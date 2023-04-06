package com.bde.flix.Service;

import com.bde.flix.model.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateFilmTestService {
    FilmRepository filmRepo;

    @Autowired
    public GenerateFilmTestService(FilmRepository filmRepo){
        this.filmRepo = filmRepo;
    }

}
