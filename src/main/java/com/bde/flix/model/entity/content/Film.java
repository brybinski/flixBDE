package com.bde.flix.model.entity.content;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Film extends com.bde.flix.model.entity.content.Content implements Watchable
{
    @Column
    private String path;
    @Override
    public void setDuration(int dur)
    {
        if(dur < 0)
            throw new IllegalArgumentException("Duration must be greater than 0");

        super.setDuration(dur);
    }
}
