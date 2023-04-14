package com.bde.flix.controller;

import com.bde.flix.Service.CardService;
import com.bde.flix.model.entity.userman.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
public class CardControler {

    @Autowired
    private CardService cardService;
    @GetMapping(value = "/card")
    public CardRecord GetCredentials(@RequestParam(required = true) String user,
                                     @RequestParam(required = true) long card_number,
                                     @RequestParam(required = true) int cvv,
                                     @RequestParam(required = true) String expire_date,
                                     @RequestParam(required = true) String cardHolder) {
        Card entity = cardService.createCard(UUID.fromString(user),
                                            card_number,
                                            cvv,
//                                          expire_date
                                            cardHolder);
        return new CardRecord(HttpStatus.OK);
    }
}
