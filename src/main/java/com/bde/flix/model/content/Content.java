package com.bde.flix.model.content;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public abstract class Content {
    private long id;
    private String title;
    private int duration;
    private String description;
    private LocalDate releaseDate;
    private String poster;
    private String director;
    private String[] cast;
}
