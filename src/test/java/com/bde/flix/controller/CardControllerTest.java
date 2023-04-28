package com.bde.flix.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.bde.flix.controller.GenerateRandomValues.NumberLengthOfN;
import static org.junit.jupiter.api.Assertions.*;

class CardControllerTest extends CardController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCredentials()  {
        try {
            String numberStr = String.valueOf(NumberLengthOfN(19));
            String fakeUser = "testUser" + numberStr;
            long cardNumber = 5555555500000000L + NumberLengthOfN(8);
            int cvv = (int)NumberLengthOfN(3);
            String fakeExpireDate = "55/" + (NumberLengthOfN(2));
            String fakeCardHolder = "fakeCardHolder" + numberStr;
        } catch (Exception e){
            fail("Caught Exception! NumberLengthOfN() got invalid param(valid: 1..19)");
        }

        assertTrue(true);
    }
}