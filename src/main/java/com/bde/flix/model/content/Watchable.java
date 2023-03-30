package com.bde.flix.model.content;

import jakarta.persistence.Embeddable;

@Embeddable
public interface Watchable {
    public int getDuration();
    public void setDuration(int dur);
}
