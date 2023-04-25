package com.bde.flix.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.bde.flix.controller.HelloController.hardcodedTemplate;
import static org.junit.jupiter.api.Assertions.*;

class HelloControllerTest {
    @Test
    @DisplayName("Test HelloController")
    public void TestHelloController() {
//        assertTrue(true);
//        assertEquals(5,5);
        assertTrue(hardcodedTemplate);

    }

}