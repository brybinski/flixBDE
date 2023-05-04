package com.bde.flix.controller;

import com.bde.flix.service.AdminService;
import com.bde.flix.model.entity.userman.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Component;

import static com.bde.flix.controller.GenerateRandomValues.NumberLengthOfN;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminControllerTest extends AdminController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    AdminService testAdminService;
    @Test
    void testAdmin() {
        long number = 0L;
        try {
            number = NumberLengthOfN(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String numberStr = Long.toString(number);
        String fakeName = "testName" + numberStr;
        String fakeSurname = "testSurname" + numberStr;
        String fakeMail = "testMail" + numberStr;
        String fakePasswd = "testPasswd" + numberStr;
        String fakeWorkId = "555" + Long.toString(number);
        Admin testEntity = testAdminService.createadmin(
                fakeName,
                fakeSurname,
                fakeMail,
                fakePasswd,
                fakeWorkId
        );
        assertTrue(true);
    }
}