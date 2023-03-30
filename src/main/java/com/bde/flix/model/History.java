package com.bde.flix.model;

import com.bde.flix.model.content.Watchable;
import com.fasterxml.jackson.core.SerializableString;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class History{

    @Id
    private long id;
    private int content_id;

    private int watch_time;
}
