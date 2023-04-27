package com.bde.flix.model.entity.userman;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends Account
{
    private int sub_type;
    private LocalDate sub_end;
}
