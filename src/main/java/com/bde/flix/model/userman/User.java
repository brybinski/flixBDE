package com.bde.flix.model.userman;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;


@Entity
public class User extends Account {

    private int sub_type;
    private LocalDate sub_end;
    @OneToOne
    private Card cardInfo;

}
