package com.bde.flix.model.content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;


@Getter
@Setter

@MappedSuperclass
public abstract class Content {
    @Id
    @UuidGenerator
    @Column(unique = true)
    private String uuid;
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
