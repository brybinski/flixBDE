package com.bde.flix.model.content;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Season {
    @Id
    private long id;
    private int number;
    private String description;

    @ManyToOne
    private Episode episode;

}
