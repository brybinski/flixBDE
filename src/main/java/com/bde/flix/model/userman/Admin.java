package com.bde.flix.model.userman;

import jakarta.persistence.Entity;

@Entity
public class Admin extends Account {
    private String name;
    private String surname;
    private int work_id;

//    set AvailSub to always true
}
