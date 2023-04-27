package com.bde.flix.controller;

import com.bde.flix.service.AdminService;
import com.bde.flix.model.entity.userman.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest extends AdminController {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    private AdminService testAdminService;
    @Test
    void testAdmin() {
        double number = Math.random();
        int numberInt = (int)Math.floor(number * 100);
        String numberStr = Double.toString(number);
        String fakeName = "testName" + numberStr;
        String fakeSurname = "testSurname" + numberStr;
        String fakeMail = "testMail" + numberStr;
        String fakePasswd = "testPasswd" + numberStr;
        String fakeWorkid = "555555" + numberInt;
        Admin testEntity = testAdminService.createadmin(
                fakeName,
                fakeSurname,
                fakeMail,
                fakePasswd,
                fakeWorkid
        );
        assertTrue(true);
    }
}