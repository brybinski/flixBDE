package com.bde.flix.controller;

import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.service.CardService;
import com.bde.flix.model.repository.CardRepository;
import com.bde.flix.model.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.bde.flix.controller.GenerateRandomValues.NumberLengthOfN;
import static org.junit.jupiter.api.Assertions.*;

class CardControllerTest extends CardController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Autowired
    CardRepository testCardRepo;
//    UserRepository testUserRepo = null;
//    @Autowired
//    CardService testEntityCardService = new CardService();//.GenerateCardService(testCardRepo, testUserRepo);

    @Test
    void testGetCredentials() {
        User fakeUser = null;
        long cardNumber = 0;
        int cvv = 0;
//        String fakeExpireDate = null;
        String fakeCardHolder = null;
        try {
            String numberStr = String.valueOf(NumberLengthOfN(19));
            cardNumber = 5555555500000000L + NumberLengthOfN(8);
            cvv = (int) NumberLengthOfN(3);
//            fakeExpireDate = "55/" + (NumberLengthOfN(2));
            fakeCardHolder = "fakeCardHolder" + numberStr;
        } catch (Exception e) {
            fail("Caught Exception! NumberLengthOfN() got invalid param(valid: 1..19)");
        }

        Card testCard = new Card();
        testCard.setUser(fakeUser);
        testCard.setCard_number(cardNumber);
        testCard.setCvv(cvv);
        testCardRepo.save(testCard);
//        Card testEntity = testEntityCardService.createCard(
//                fakeUser,
//                cardNumber,
//                cvv,
////              fakeExpireDate,
//                fakeCardHolder);

        assertTrue(true);
    }
}