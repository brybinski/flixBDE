package com.bde.flix.controller;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationControllerTest extends RegistrationController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInfo() {
        String testEmail = "testEmail";
        String testPassword = "testPassword";
        String testName = "testName";
        String testSurname = "testSurname";
        Registration testStatus = GetInfo(testEmail, testPassword, testName, testSurname);
        //We check first if the object returned is of the correct type;
        //Then to see if it is the correct response.
        assertTrue(testStatus.status() instanceof HttpStatus);
        assertSame(testStatus.status(), HttpStatus.OK);
        //fail();
    }
}