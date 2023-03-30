package com.bde.flix.model.content;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class Content {
    @Id
    private long id;
    private String title;
    private int duration;
    private String description;
    private LocalDate releaseDate;
    private String poster;
    private String director;


}
