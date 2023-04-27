package com.bde.flix.service;

import com.bde.flix.model.entity.content.TestEntity;
import com.bde.flix.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    TestRepository testRepo;

    @Autowired
    public TestService(TestRepository test){
            this.testRepo = test;
    }

    public TestEntity createTest(int num){
        TestEntity instance = new TestEntity();
        instance.setNumber(num);

        return testRepo.save(instance);
    }
}
