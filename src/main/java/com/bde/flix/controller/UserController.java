package com.bde.flix.controller;

//import com.bde.flix.FlixApplication;
import com.bde.flix.Service.UserService;
import com.bde.flix.model.entity.userman.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{

    @Autowired
    private UserService userservice;

    @GetMapping("/user")
    public UserRecord user(
            @RequestParam(required = true, defaultValue = "defaultUserMail") String mail,
            @RequestParam(required = true, defaultValue = "defaultUserPasswd") String passwd
                     )
    {
        User entity = userservice.createuser(
                mail,
                passwd
        );

        return new UserRecord(
                entity.getId(),
                entity.getEmail(),
                entity.getHash());


        //tutaj potrzeba typow generycznych zeby to dobrze dzialalo
        //zostawie na razie bez flagi debug
        //odkomentuj tez import
        //if(FlixApplication.debug){
        //    return new UserRecord(mail, passwd);
        //}
        //return HttpStatus.OK;
    }
}
