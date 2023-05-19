package com.bde.flix.controller;

import com.bde.flix.model.entity.userman.Card;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.CardRepository;
import com.bde.flix.model.repository.UserRepository;
import com.bde.flix.service.CardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.YearMonth;
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
    @Autowired
    UserRepository testUserRepo;

    //to import user() we need to instance it from UserController
    @Autowired
    UserController testUserEntity = new UserController();


    @Test
    void testGetCredentials() {
        User fakeUser = new User();
//        UUID fakeUserUUID = UUID.randomUUID();
        long fakeCardNumber = 0L;
        int fakeCvv = 0;
//        YearMonth fakeExpireDate = YearMonth.of(2055, 5);
        String fakeCardHolder = "";
        try {
            fakeCardNumber = 5555555500000000L + NumberLengthOfN(8);
            fakeCvv = (int) NumberLengthOfN(3);
//            fakeExpireDate = "55/" + StringLengthOfN(2);
            fakeCardHolder = "fakeCardHolder" + StringLengthOfN(15);
        } catch (Exception e) {
            fail("NumberLengthOfN() got invalid param");
        }

        long testingNumber = 0;
        try {
            testingNumber = NumberLengthOfN(5);
        } catch (Exception e){
            fail("NumberLengthOfN() got invalid param(valid: 1..19)");
        }
        String testMail = "testing" + testingNumber + "@testbde.test";
        String testPasswd = "testing" + testingNumber + "password";
        //creating repo to put data in db and to get this data in return
        UserRecord testingUserInstance;
        testingUserInstance = testUserEntity.user(testMail, testPasswd);

        fakeUser.setId(testingUserInstance.id());
        Card testEntity = testEntityCardService.createCard(
                fakeUser.getId(),
                fakeCardNumber,
                fakeCvv,
//                fakeExpireDate,
                fakeCardHolder);

        List<Card> testCardRepoInstance = testCardRepo.findAll();
        int testIndex = testCardRepoInstance.size()-1; // Newest possible index
        //pulling the same data from database
        assertEquals(testingUserInstance.id(), testCardRepoInstance.get(testIndex).getUser().getId());
        assertEquals(fakeCardNumber, testCardRepoInstance.get(testIndex).getCard_number());
        assertEquals(fakeCvv, testCardRepoInstance.get(testIndex).getCvv());
        //ExpireDate is left out, because createCard wants String, but classes
        //down the line want YearMonth
//        assertEquals(fakeExpireDate, testCardRepoInstance.get(testIndex).getExpire_date());
        assertEquals(fakeCardHolder, testCardRepoInstance.get(testIndex).getCardHolder());


//        System.out.println("#############################################");
//        System.out.println("fakeUser.getId():" + fakeUser.getId());
//        System.out.println("fakeCardNumber:" + fakeCardNumber);
//        System.out.println("fakeCvv:" + fakeCvv);
//        System.out.println("fakeExpireDate:" + fakeExpireDate);
//        System.out.println("fakeCardHolder:" + fakeCardHolder);
//        System.out.println("testCardRepoInstance:" + testCardRepoInstance);
//        System.out.println("#############################################");
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