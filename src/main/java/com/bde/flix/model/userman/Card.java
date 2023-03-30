package com.bde.flix.model.userman;
import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.time.YearMonth;

@Entity

public class Card {
    @Id
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)

    private User user;

    private long card_number;
    private int cvv;
    private YearMonth expire_date;
    private String cardHolder;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
