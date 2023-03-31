package com.bde.flix.model.content;

import jakarta.persistence.Entity;

@Entity
public class Film extends Content implements Watchable{

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