package com.bde.flix.model.entity.content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


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
    private LocalDate releaseDate;
    @Column(nullable = true, length = 128)
    private String poster;
    @Column(nullable = true, length = 128)
    private String director;
    @ElementCollection
    private Set<String> actorsCast;
    @ElementCollection
    private Set<String> genreTag;

}
