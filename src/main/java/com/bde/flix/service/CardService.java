package com.bde.flix.service;

import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.model.repository.CardRepository;
import com.bde.flix.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Optional;
import java.util.UUID;

@Service
public class CardService
{
    CardRepository cardRepo;
    UserRepository userRepo;

    @Autowired
    public void GenerateCardService(
            CardRepository cardRepo,
            UserRepository userRepo)
    {
        this.cardRepo = cardRepo;
        this.userRepo = userRepo;
    }

    public Card createCard(
        UUID user,
        long cardNumber,
        int cvv,
        YearMonth expireDate,
        String CardHolder)
    {
        Card instance = new Card();
        instance.setUser(userRepo.getReferenceById(user));
        instance.setCardNumber(cardNumber);
        instance.setCvv(cvv);
        instance.setExpireDate(expireDate);
        instance.setCardHolder(CardHolder);
        return cardRepo.save(instance);
    }
    public void deleteCardById(UUID id){
        cardRepo.deleteById(id);
    }
    public boolean isExistCard(UUID id){
        return cardRepo.existsById(id);
    }
    public void updateCard(UUID id, Card update){
        Optional<Card> data = getCardById(id);
        if(data.isPresent()){
            Card card = data.get();
            card.setUser(update.getUser());
            card.setCardNumber(update.getCardNumber());
            card.setCvv(update.getCvv());
            card.setExpireDate(update.getExpireDate());
            card.setCardHolder(update.getCardHolder());
            cardRepo.save(card);
        }
    }
    public Optional<Card> getCardById(UUID id){
        return cardRepo.findById(id);
    }
}

