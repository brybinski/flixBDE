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
    @GetMapping(value = "/api/card")
    public CardRecord getCredentials(@RequestParam(required = true) String user,
                                     @RequestParam(required = true) long cardNumber,
                                     @RequestParam(required = true) int cvv,
                                     @RequestParam(required = true) YearMonth expireDate,
                                     @RequestParam(required = true) String cardHolder)
    {
        Card entity = cardService.createCard(
                UUID.fromString(user),
                cardNumber,
                cvv,
                expireDate,
                cardHolder);

        return new CardRecord(
                entity.getUser().toString(),
                entity.getCardNumber(),
                entity.getCvv(),
                entity.getExpireDate(),
                entity.getCardHolder());
    }
}
