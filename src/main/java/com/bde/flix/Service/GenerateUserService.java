package com.bde.flix.Service;

import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateUserService {
    UserRepository userrepo;
    @Autowired
    public GenerateUserService(UserRepository userrepo){
        this.userrepo = userrepo;
    }

    public User createuser(String mail, String passwd){
        User instance = new User();
        instance.setEmail(mail);
        // TODO: Funkcja hashujaca
        instance.setHash(passwd);
        return userrepo.save(instance);
    }

}
