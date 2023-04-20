package com.bde.flix.Service;

import com.bde.flix.model.entity.History;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.repository.EpisodeRepository;
import com.bde.flix.model.repository.FilmRepository;
import com.bde.flix.model.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.Optional;
import java.util.UUID;

@Service
public class HistoryService {
    HistoryRepository histRepo;
    EpisodeRepository episodeRepo;
    FilmRepository filmRepo;

    @Autowired
    public HistoryService(HistoryRepository hist){
        this.histRepo = hist;
    }

    public History createHistory(UUID content_id, UUID account_id, boolean episode, int duration) throws InvalidAttributeValueException {
        History instance = new History();
        if (episode){
            Optional<Episode> con = episodeRepo.findById(content_id);
            if (con.isEmpty()){
                throw new InvalidAttributeValueException("No episode with given UUID");
            }
            else{
                instance.setEpisode(episodeRepo.getReferenceById(content_id));
                instance.setContentType(History.ContentType.EPISODE);
            }        }
        else{
            Optional<Film> con = filmRepo.findById(content_id);
            if (con.isEmpty()){
                throw new InvalidAttributeValueException("No film with given UUID");
            }
            else{
                instance.setFilm(filmRepo.getReferenceById(content_id));
                instance.setContentType(History.ContentType.FILM);
            }

        }
        // TODO: check correctness of watch_time
        instance.setWatch_time(duration);

        // TODO: w8 for accRepo
        // instance.setAccount();
        return histRepo.save(instance);

    }
}

