package com.bde.flix.model.content;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class Content {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 64)
    private String title;
    private int duration;
    @Column(nullable = true, length = 256)
    private String description;
    private LocalDate releaseDate;
    @Column(nullable = true, length = 64)
    private String poster;
    @Column(nullable = true, length = 64)
    private String director;
    @Column(nullable = true, length = 128)
    private String cast;


}
