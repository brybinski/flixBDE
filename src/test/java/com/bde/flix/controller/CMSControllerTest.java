package com.bde.flix.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CMSControllerTest extends CMSController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCms() {
        CMS cmsTestObject = new CMS(true);
        assertTrue(cmsTestObject.ack());
    }
}