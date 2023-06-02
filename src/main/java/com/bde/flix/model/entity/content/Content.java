package com.bde.flix.model.entity.content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Content
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 64)
    private String title;
//    @Column(nullable = false, length = 256)
//    private String sourceLink;
    private int duration;
    @Column(nullable = true, length = 256)
    private String description;
    private Date releaseDate;
    @Column(nullable = true, length = 128)
    private String poster;
    @Column(nullable = true, length = 128)
    private String director;
//    @Column(nullable = false)
//    boolean isFilm;
    @ElementCollection
    private Set<String> actorsCast;
    @ElementCollection
    private Set<String> genreTag;

}
