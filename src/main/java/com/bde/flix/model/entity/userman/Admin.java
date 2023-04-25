package com.bde.flix.model.entity.userman;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Admin extends Account
{
    private String name;
    private String surname;
    private int work_id;
//    set AvailSub to always true
}
