package com.bde.flix.model.entity;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.userman.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class History{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // why many to one and not one to one
    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    // why many to one and not one to one
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    // why many to one and not one to one
    @ManyToOne
    @JoinColumn(name = "episode_id")
    private Episode episode;


    private int watch_time;

    private ContentType contentType;
    //
    public static enum ContentType{
        FILM,
        EPISODE
    }

    public UUID getContentId(){
        if (this.episode != null){
            return this.episode.getId();
        } else if (this.film != null) {
            return this.film.getId();
        }
        // empty uuid
        else return new UUID(0L, 0L);
    }
}
