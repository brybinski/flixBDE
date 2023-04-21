package com.bde.flix.model.entity.content;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Film extends com.bde.flix.model.entity.content.Content implements Watchable {

}
