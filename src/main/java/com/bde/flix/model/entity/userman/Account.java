package com.bde.flix.model.entity.userman;
import com.bde.flix.Security.Role;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;
    @Column(nullable = false, length = 320)
    private String email;
    @Column(nullable = false, length = 64)

    private Role role;
    private String hash;
    private boolean availSub;

}
