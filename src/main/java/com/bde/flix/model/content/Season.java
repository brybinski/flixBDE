package com.bde.flix.model.content;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Season {
    @Id
    @GeneratedValue
    private long id;
    private int number;
    @Column(nullable = true, length = 256)
    private String description;

    @ManyToOne
    private Episode episode;

}
