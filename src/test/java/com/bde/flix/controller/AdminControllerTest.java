package com.bde.flix.controller;

import com.bde.flix.model.repository.AdminRepository;
import com.bde.flix.service.AdminService;
import com.bde.flix.model.entity.userman.Admin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    @Autowired
    AdminRepository testAdminRepo;

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
        String fakeWorkId = "555" + number; // That string can have about 8 to 9 digits max, its int limitation
        Admin testEntity = testAdminService.createadmin(
                fakeName,
                fakeSurname,
                fakeMail,
                fakePasswd,
                fakeWorkId
        );
        List<Admin> testAdminRepoInstance = testAdminRepo.findAll();
        int testIndex = testAdminRepoInstance.size()-1; // Newest possible index

        assertEquals(fakeName, testAdminRepoInstance.get(testIndex).getName());
        assertEquals(fakeSurname, testAdminRepoInstance.get(testIndex).getSurname());
        assertEquals(fakeMail, testAdminRepoInstance.get(testIndex).getEmail());
        assertEquals(fakePasswd, testAdminRepoInstance.get(testIndex).getHash());
        assertEquals(fakeWorkId, String.valueOf(testAdminRepoInstance.get(testIndex).getWork_id()));
        // This checks if values that are put into the db are corresponding with what we generated.
        // The RNG is not bulletproof, but with 5 digits we have 10k possibilities, I'm not really expecting collisions.
    }
}