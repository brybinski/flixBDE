package com.bde.flix.model.content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;


@Entity
public class Episode implements Watchable {
    @Id
    @UuidGenerator
    @Column(unique = true)
    private String uuid;
    private int number;
    private String description;
    private String path;
    private int duration;

    public int getDuration(){
        return this.duration;
    }

    public void setDuration(int dur){
        if(dur < 0)
            throw new IllegalArgumentException("Duration must be greater than 0");

        this.duration = dur;
    }
}
