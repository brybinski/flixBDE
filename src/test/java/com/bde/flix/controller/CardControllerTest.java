package com.bde.flix.controller;

import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.CardRepository;
import com.bde.flix.service.CardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static com.bde.flix.controller.GenerateRandomValues.NumberLengthOfN;
import static com.bde.flix.controller.GenerateRandomValues.StringLengthOfN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardControllerTest extends CardController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Autowired
    CardRepository testCardRepo;
    @Autowired
    CardService testEntityCardService;

    @Test
    void testGetCredentials() {
        User fakeUser = new User();
        UUID fakeUserUUID = UUID.randomUUID();
        long fakeCardNumber = 0L;
        int fakeCvv = 0;
        String fakeExpireDate = "";
        String fakeCardHolder = "";
        try {
            fakeCardNumber = 5555555500000000L + NumberLengthOfN(8);
            fakeCvv = (int) NumberLengthOfN(3);
            fakeExpireDate = "55/" + StringLengthOfN(2);
            fakeCardHolder = "fakeCardHolder" + StringLengthOfN(15);
        } catch (Exception e) {
            fail("NumberLengthOfN() got invalid param");
        }

        fakeUser.setId(fakeUserUUID);
        //FIXME: check the table constraints
        //it tries to put something and db cant let it through
        //it fails here vv
        Card testEntity = testEntityCardService.createCard(
                fakeUser.getId(),
                fakeCardNumber,
                fakeCvv,
//                fakeExpireDate,
                fakeCardHolder);
        List<Card> testCardRepoInstance = testCardRepo.findAll();
        System.out.println("#############################################");
        System.out.println("fakeUser.getId():" + fakeUser.getId());
        System.out.println("fakeCardNumber:" + fakeCardNumber);
        System.out.println("fakeCvv:" + fakeCvv);
        System.out.println("  fakeExpireDate:" + fakeExpireDate);
        System.out.println("fakeCardHolder:" + fakeCardHolder);
        System.out.println("testCardRepoInstance:" + testCardRepoInstance);
        System.out.println("#############################################");
        //assertTrue(true);
    }
}
/*
UUID   user
long   cardNumber
int    cvv
String expireDate
String cardHolder
 */