package com.bde.flix;

import com.bde.flix.model.entity.userman.Account;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.AccountRepository;
import com.bde.flix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class FlixApplication {

    public static Boolean DEBUG = true;
    public static void main(String[] args) {
        SpringApplication.run(FlixApplication.class, args);

    }

    @Autowired
    private UserService usrSrvc;
    @Autowired
    private AccountRepository usrRepo;
    @Autowired
    PasswordEncoder passEnc;
    @EventListener(ApplicationReadyEvent.class)
    public void mainAdmin() throws InterruptedException {
        Optional<Account> adm = usrRepo.findByEmail("admin@bde.flix.com");
        if(adm.isEmpty()){
            //Normaly I would add master Admin Account that can elevate and degrade users to admin
            User admin = usrSrvc.createuser("admin@bde.flix.com", passEnc.encode("toor"));
            usrSrvc.elevateUser(admin.getId());
            System.out.println("ADMIN ACCOUNT CREATED, CHANGE DEFAULT PASSWORD");
        }
    }



//    @Autowired
//    private EmailSenderService sender;
//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        sender.sendEmail("bartoszrybinski@outlook.com",
//                         "Sup",
//                         "I is working");
//    }
}
