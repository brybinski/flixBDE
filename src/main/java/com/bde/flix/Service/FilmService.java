package com.bde.flix.Service;

import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class FilmService {
    FilmRepository filmRepo;

    @Autowired
    public FilmService(FilmRepository filmRepo){this.filmRepo = filmRepo;
    }

    public Film createFilm(String title,
                           int duration,
                           String description,
//                           LocalDate releaseDate,
                           String poster,
                           String director
                           //ArrayList<String> actors_cast
                            ){
        Film instance = new Film();
        instance.setTitle(title);
        instance.setDuration(duration);
        instance.setDescription(description);
//        instance.setReleaseDate(releaseDate);
        instance.setPoster(poster);
        instance.setDirector(director);
//      instance.setActors_cast(actors_cast);
        return filmRepo.save(instance);
    }
    // TODO: Casting of string to arraylist
//    public Film addCast(String actors_cast)
//    {
//
//    }
}
