package com.bde.flix.model.userman;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, length = 320)
    private String email;
    @Column(nullable = false, length = 64)

    private String hash;
    private boolean availSub;

}
