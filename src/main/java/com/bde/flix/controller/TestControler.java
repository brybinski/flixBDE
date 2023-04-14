package com.bde.flix.controller;


import com.bde.flix.Service.TestService;
import com.bde.flix.model.entity.content.TestEntity;
import com.bde.flix.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControler {

    @Autowired
    private TestService testService;
    @Autowired
    private TestRepository testRepo;

    @GetMapping("/test-create")
    public  Test test(@RequestParam(required = true, defaultValue = "10") int num) {
        TestEntity entity = testService.createTest(num);


        return new Test(entity.getId().toString(), entity.getNumber());
    }
}
