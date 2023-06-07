package com.bde.flix.model.entity.userman;
import com.bde.flix.model.entity.content.Content;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends Account
{
    private int sub_type;
    private LocalDate sub_end;
    @OneToMany
    private Set<Content> favoriteList;
    @OneToMany
    private Set<Content> history;
}
