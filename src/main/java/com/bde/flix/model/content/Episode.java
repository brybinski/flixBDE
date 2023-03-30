package com.bde.flix.model.content;

public class Episode implements Watchable {
    private long id;
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
