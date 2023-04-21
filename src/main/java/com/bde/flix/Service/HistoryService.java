package com.bde.flix.Service;

import com.bde.flix.model.entity.History;
import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.userman.Account;
import com.bde.flix.model.entity.userman.Admin;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.Optional;
import java.util.UUID;

@Service
public class HistoryService
{
    HistoryRepository histRepo;
    EpisodeRepository episodeRepo;
    FilmRepository filmRepo;
    UserRepository userRepo;
    AdminRepository adminRepo;
    AccountRepository accountRepo;


    @Autowired
    public HistoryService(
            AccountRepository accRepo,
            HistoryRepository hist,
            UserRepository userRepo,
            AdminRepository adminRepo,
            EpisodeRepository episodeRepo,
            FilmRepository filmRepo
    )
    {
        this.histRepo = hist;
        this.episodeRepo = episodeRepo;
        this.filmRepo = filmRepo;
        this.userRepo = userRepo;
        this.adminRepo = adminRepo;
        this.accountRepo = accRepo;
    }

    public History createHistory(
            UUID content_id,
            UUID account_id,
            boolean episode,
            int duration
    ) throws InvalidAttributeValueException
    {
        History instance = new History();
        if (episode)
        {
            Optional<Episode> con = episodeRepo.findById(content_id);
            if (con.isEmpty())
            {
                throw new InvalidAttributeValueException("No episode with given UUID");
            }
            else
            {
                instance.setEpisode(episodeRepo.getReferenceById(content_id));
                instance.setContentType(History.ContentType.EPISODE);
            }
        }
        else
        {
            Optional<Film> con = filmRepo.findById(content_id);
            if (con.isEmpty())
            {
                throw new InvalidAttributeValueException("No film with given UUID");
            }
            else
            {
                instance.setFilm(filmRepo.getReferenceById(content_id));
                instance.setContentType(History.ContentType.FILM);
            }

        }
        // TODO: check correctness of watch_time
        instance.setWatch_time(duration);

        Optional<User> acc = userRepo.findById(account_id);
        if (acc.isEmpty())
        {
            Optional<Admin> adm = adminRepo.findById(account_id);
            if(adm.isEmpty())
            {
                throw new InvalidAttributeValueException("No account with given UUID");
            }
            else
            {
                instance.setAccount(accountRepo.getReferenceById(account_id));
            }
        }
        else
        {
            instance.setAccount(accountRepo.getReferenceById(account_id));
        }
        return histRepo.save(instance);
    }
}

