package com.bde.flix.controller;

import com.bde.flix.service.AdminService;
import com.bde.flix.model.entity.userman.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.stereotype.Component;

import static com.bde.flix.controller.GenerateRandomValues.NumberLengthOfN;
import static org.junit.jupiter.api.Assertions.*;

@Component
@TestConfiguration
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
        System.out.println("data");
        System.out.println("data");
        double number = Math.random();
        long numberLong = 0L;
        try {
            numberLong = NumberLengthOfN(5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        int numberInt = (int)Math.floor(number * 100);
        String numberStr = Double.toString(number);
        String fakeName = "testName" + numberStr;
        String fakeSurname = "testSurname" + numberStr;
        String fakeMail = "testMail" + numberStr;
        String fakePasswd = "testPasswd" + numberStr;
        String fakeWorkid = "555555" + numberLong;
        //TODO: repair instantiating testEntity
        //test fails on this line vv
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