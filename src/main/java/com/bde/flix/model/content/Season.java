package com.bde.flix.model.content;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Season {
    @Id
    @UuidGenerator
    @Column(unique = true)
    private String uuid;
    private int number;
    @Column(nullable = true, length = 256)
    private String description;

    @ManyToOne
    private Episode episode;

}
