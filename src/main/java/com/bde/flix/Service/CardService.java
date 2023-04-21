package com.bde.flix.Service;

import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.CardRepository;
import com.bde.flix.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.UUID;

@Service
public class CardService
{
    CardRepository cardRepo;
    UserRepository userRepo;

    @Autowired
    public void GenerateCardService(CardRepository cardRepo, UserRepository userRepo)
    {
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
    }

    public Card createCard(
            UUID user,
            long card_number,
            int cvv,
//          YearMonth expire_date,
            String CardHolder
    )
    {
        Card instance = new Card();
        instance.setUser(userRepo.getReferenceById(user));
        instance.setCard_number(card_number);
        instance.setCvv(cvv);
        //TODO: Cast String to YearMonth
//        instance.setExpire_date(expire_date);
        instance.setCardHolder(CardHolder);
        return cardRepo.save(instance);
    }
}

