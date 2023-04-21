package com.bde.flix.model.entity.content;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Episode implements Watchable
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;

    private int number;
    private String description;
    private String path;
    private int duration;

    @ManyToOne
    private com.bde.flix.model.entity.content.Season season;
    public int getDuration()
    {
        return this.duration;
    }

    @Override
    public void setDuration(int dur)
    {
        if(dur < 0)
            throw new IllegalArgumentException("Duration must be greater than 0");

        this.duration = dur;
    }
}
