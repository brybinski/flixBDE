package com.bde.flix.service;

import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.content.Series;
import com.bde.flix.model.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilmService
{
    FilmRepository filmRepo;

    @Autowired
    public FilmService(FilmRepository filmRepo)
    {
        this.filmRepo = filmRepo;
    }

    public Film createFilm(
            String title,
            int duration,
            String description,
            Date releaseDate,
            String poster,
            String director,
            String path,
            Set<String> actors_cast,
            Set<String> genreTag
    )
    {
        Film instance = new Film();
        instance.setTitle(title);
        instance.setDuration(duration);
        instance.setDescription(description);
        instance.setReleaseDate(releaseDate);
        instance.setPoster(poster);
        instance.setDirector(director);
        instance.setPath(path);
        instance.setActorsCast(actors_cast);
        instance.setGenreTag(genreTag);
        return filmRepo.save(instance);
    }

    public void deleteFilm(UUID id)
    {
        filmRepo.deleteById(id);
    }

    public void updateFilm(UUID id, Film update)
    {
        Optional<Film> data = getFilm(id);

        if (data.isPresent())
        {
            Film _film = data.get();
            _film.setTitle(update.getTitle());
            _film.setDuration(update.getDuration());
            _film.setDescription(update.getDescription());
            _film.setReleaseDate(update.getReleaseDate());
            _film.setDirector(update.getDirector());
            _film.setActorsCast(update.getActorsCast());
            _film.setGenreTag(update.getGenreTag());
            filmRepo.save(_film);
        }
    }

    public boolean isExistFilm(UUID id)
    {
        return filmRepo.existsById(id);
    }

    public List<Film> getRandomFilms()
    {
        List<Film> films = getFilms();
        Collections.shuffle(films);
        return films.subList(0, Math.min(films.size(), 10));
    }

    public Optional<Film> getFilm(UUID id)
    {
        return filmRepo.findById(id);
    }

    public List<Film> getFilms()
    {
        return filmRepo.findAll();
    }

    public List<Film> getFilmByTitle(String title)
    {
        return filmRepo.findByTitleContaining(title);
    }

}
