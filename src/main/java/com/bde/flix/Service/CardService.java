package com.bde.flix.Service;

import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.UUID;

@Service
public class CardService {
    CardRepository cardRepo;
    @Autowired
    public void GenerateCardService(CardRepository cardRepo){this.cardRepo = cardRepo;}

    public Card createCard(UUID user,
                           long card_number,
                           int cvv,
//                           YearMonth expire_date,
                           String CardHolder){
        Card instance = new Card();
        //TODO: instance.setUser(user);
        instance.setCard_number(card_number);
        instance.setCvv(cvv);
        //TODO: Cast String to YearMonth
//        instance.setExpire_date(expire_date);
        instance.setCardHolder(CardHolder);
        cardRepo.save(instance);
        return instance;
    }
}

