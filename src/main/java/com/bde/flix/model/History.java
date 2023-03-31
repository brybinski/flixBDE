package com.bde.flix.model;

import com.bde.flix.model.content.Episode;
import com.bde.flix.model.content.Film;
import com.bde.flix.model.userman.Account;
import jakarta.persistence.*;

@Entity
public class History{

    @Id
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;


    private int watch_time;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
