package com.bde.flix.controller;

import com.bde.flix.controller.Payload.CardRecord;
import com.bde.flix.service.CardService;
import com.bde.flix.model.entity.userman.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.Date;
import java.util.UUID;


@RestController
public class CardController
{

    @Autowired
    private CardService cardService;
    @PostMapping(value = "/api/card")
    public CardRecord getCredentials(@RequestBody CardRecord card)
    {
        Card entity = cardService.createCard(
                card.user(),
                card.cardNumber(),
                card.cvv(),
                card.expireDate(),
                card.cardHolder());

        return new CardRecord(
                entity.getUser().getId(),
                entity.getCardNumber(),
                entity.getCvv(),
                entity.getExpireDate(),
                entity.getCardHolder());
    }
}
