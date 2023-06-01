package com.bde.flix.service;

import com.bde.flix.model.entity.userman.Account;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.AccountRepository;
import com.bde.flix.model.repository.UserRepository;
import com.bde.flix.security.Account.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{

    @Autowired
    UserRepository userRepo;

    @Autowired
    AccountRepository accrepo;
    @Autowired
    public UserService(UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    @Transactional
    public User elevateUser(UUID uuid){
        User usr = userRepo.getReferenceById(uuid);
        List<Role> roles = usr.getRoles();
        for (Role rle:
             roles) {
            if(rle == Role.ROLE_ADMIN){
                return usr;
            }
        }
        roles.add(Role.ROLE_ADMIN);
        usr.setRoles(roles);
        return userRepo.save(usr);
    }

    public void changePasswd(String mail, String passwd){
        Optional<Account> usr = accrepo.findByEmail(mail);

        if(usr.isEmpty()){
         throw new InvalidParameterException("no email found");
        }
        else {
            @SuppressWarnings("unchecked") // checked in previous if
            Account acc = usr.get();

            //here I get the reference instead of copy
            Account ref = accrepo.getReferenceById(acc.getId());
            ref.setHash(passwd);
            accrepo.save(ref);
        }
    }
    public User createuser(String mail,
                           String passwd)
    {
        User instance = new User();
        instance.setEmail(mail);
        instance.setRoles(Collections.singletonList(Role.ROLE_USER));
        instance.setHash(passwd);
        return userRepo.save(instance);
    }

}
