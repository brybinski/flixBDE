package com.bde.flix;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.content.Series;
import com.bde.flix.service.EpisodeService;
import com.bde.flix.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class FlixApplication {
    @Autowired
    private FilmService filmService;
    @Autowired
    private EpisodeService episodeService;
    public static Boolean DEBUG = true;
    public static void main(String[] args) {
        SpringApplication.run(FlixApplication.class, args);

    }
//
//    @Autowired
//    private EmailSenderService sender;
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        sender.sendEmail("bartoszrybinski@outlook.com",
//                         "Sup",
//                         "I is working");
//    }
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        List<Film> filmsIds = filmService.getFilms();
        List<Episode> episodeIds =episodeService.getEpisodes();
    }
}
