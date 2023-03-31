package com.bde.flix.model.content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Episode implements Watchable {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 64)
    private String title;
    private int number;     // number of what?
    @Column(nullable = true, length = 256)
    private String description;
    private String path;   // path to where?
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
