package com.bde.flix.model.userman;


import com.bde.flix.model.History;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    private long id;
    private String email;
    private String hash;
    private boolean availSub;

}
