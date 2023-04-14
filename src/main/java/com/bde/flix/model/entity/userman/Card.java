package com.bde.flix.model.entity.userman;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;


import java.time.YearMonth;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private UUID id;

//    FIXME: ADD INHERITANCE
//    @OneToOne(orphanRemoval = true)
//    private User user;

    private long card_number;
    private int cvv;
    private YearMonth expire_date;
    
    @Column(nullable = false, length = 64)
    private String cardHolder;


}
