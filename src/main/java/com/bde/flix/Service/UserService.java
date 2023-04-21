package com.bde.flix.Service;

import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    UserRepository userRepo;
    @Autowired
    public UserService(UserRepository userRepo)
    {
        this.userRepo = userRepo;
    }

    public User createuser(String mail,
                           String passwd)
    {
        User instance = new User();
        instance.setEmail(mail);
        // TODO: Funkcja hashujaca
        instance.setHash(passwd);
        return userRepo.save(instance);
    }

}
