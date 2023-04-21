package com.bde.flix.controller;

import com.bde.flix.Service.CardService;
import com.bde.flix.model.entity.userman.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class CardController
{

    @Autowired
    private CardService cardService;
    @GetMapping(value = "/card")
    public CardRecord getCredentials(@RequestParam(required = true) String user,
                                     @RequestParam(required = true) long cardNumber,
                                     @RequestParam(required = true) int cvv,
                                     @RequestParam(required = true) String expireDate,
                                     @RequestParam(required = true) String cardHolder)
    {
        Card entity = cardService.createCard(
                UUID.fromString(user),
                cardNumber,
                cvv,
//              expireDate
                cardHolder
        );
        return new CardRecord(
                entity.getUser().toString(),
                entity.getCard_number(),
                entity.getCvv(),
                "00.00.0000",
//              entity.getExpire_date(),
                entity.getCardHolder()
        );
    }
}
