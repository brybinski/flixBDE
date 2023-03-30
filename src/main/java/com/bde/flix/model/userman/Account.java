package com.bde.flix.model.userman;

import com.bde.flix.model.converters.StringListConverter;
import com.bde.flix.model.History;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Convert;

import java.util.ArrayList;

@Entity
public abstract class Account {
    @Id
    private long id;
    private String email;
    private String hash;
    private boolean availSub;

    @ElementCollection
    private ArrayList<Integer> hist;
}
