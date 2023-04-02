package com.bde.flix.model.userman;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Card {
    @Id
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)

    private User user;

    private long card_number;
    private int cvv;
    private YearMonth expire_date;
    
    @Column(nullable = false, length = 64)
    private String cardHolder;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
