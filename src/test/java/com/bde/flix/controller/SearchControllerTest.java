package com.bde.flix.controller;

import com.bde.flix.controller.Payload.Search;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchControllerTest extends SearchController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSearch() {
        assertTrue(new Search(
                "testId",
                "testDescription",
                "testDirector",
                "testDuration",
                "testPoster",
                "testRelease_date",
                "testTitle") instanceof Search);
    }
}

