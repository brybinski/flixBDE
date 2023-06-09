package com.bde.flix.controller;

import com.bde.flix.controller.Payload.CardRecord;
import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.service.CardService;
import com.bde.flix.model.entity.userman.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
public class CardController
{

    @Autowired
    private CardService cardService;
    @PostMapping(value = "/api/card")
    public CardRecord createCard(@RequestBody CardRecord card)
    {
        Card entity = cardService.createCard(

                card.id(),
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
    @DeleteMapping("/api/card")
    public ResponseEntity<HttpStatus> deleteCardById(@RequestBody IdRecord card){
        try {
            cardService.deleteCardById(card.id());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
//            System.out.println(card.id());
//            System.out.println(e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/card")
    public ResponseEntity<HttpStatus> updateCard(@RequestBody CardRecord card){
        if(cardService.isExistCard(card.id()) && cardService.getCardById(card.id()).isPresent()){
            Card karta = new Card();
            karta.setUser(cardService.getCardById(card.id()).get().getUser());
            karta.setCardNumber(card.cardNumber());
            karta.setCvv(card.cvv());
            karta.setExpireDate(card.expireDate());
            karta.setCardHolder(card.cardHolder());
            cardService.updateCard(card.id(), karta);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
