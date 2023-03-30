package com.bde.flix.model.content;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Series extends Content{


    @ManyToOne
    private Season season;

}
