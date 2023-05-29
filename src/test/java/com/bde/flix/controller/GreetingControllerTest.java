package com.bde.flix.controller;

import com.bde.flix.controller.Payload.Greeting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingControllerTest extends GreetingController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGreeting() {
        final String testText = "Testing";
        final String differentTestText = "HoHo! Then come as close as you like";

        Greeting testResult = greeting(testText);
        assertEquals(1, testResult.id());
        assertEquals("Hello, Testing!", testResult.content());

        testResult = greeting(differentTestText);
        assertEquals(2, testResult.id());
        assertEquals("Hello, HoHo! Then come as close as you like!", testResult.content());
    }
}