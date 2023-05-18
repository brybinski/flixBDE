package com.bde.flix.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bde.flix.controller.GenerateRandomValues.NumberLengthOfN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest extends UserController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testUser() {
        long testingNumber = 0;
        try {
            testingNumber = NumberLengthOfN(5);
        } catch (Exception e){
            fail("NumberLengthOfN() got invalid param(valid: 1..19)");
        }
        String testMail = "testing" + testingNumber + "@testbde.test";
        String testPasswd = "testing" + testingNumber + "password";
//        assertEquals(user(testMail, testPasswd).mail(), testMail);
//        assertEquals(user(testMail, testPasswd).passwd(), testPasswd);
//        assertNotNull(user(testMail, testPasswd).id());
        UserRecord testingUserInstance = user(testMail, testPasswd);
        assertEquals(testMail, testingUserInstance.mail());
        assertEquals(testPasswd, testingUserInstance.passwd());
    }
}