package com.bde.flix.service;

import com.bde.flix.model.entity.content.Content;
import com.bde.flix.model.entity.userman.Account;
import com.bde.flix.model.entity.userman.User;
import com.bde.flix.model.repository.AccountRepository;
import com.bde.flix.model.repository.ContentRepository;
import com.bde.flix.model.repository.UserRepository;
import com.bde.flix.security.Account.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;

@Service
public class UserService
{
    UserRepository userRepo;
    AccountRepository accRepo;
    ContentRepository contentRepo;
    @Autowired
    public UserService(UserRepository userRepo, AccountRepository accRepo, ContentRepository contentRepo)
    {
        this.userRepo = userRepo;
        this.accRepo = accRepo;
        this.contentRepo = contentRepo;
    }

    public void changePasswd(String mail, String passwd){
        Optional<Account> usr = accRepo.findByEmail(mail);

        if(usr.isEmpty()){
         throw new InvalidParameterException("no email found");
        }
        else {
            @SuppressWarnings("unchecked") // checked in previous if
            Account acc = usr.get();

            //here I get the reference instead of copy
            Account ref = accRepo.getReferenceById(acc.getId());
            ref.setHash(passwd);
            accRepo.save(ref);
        }
    }

    public User createuser(String mail,
                           String passwd)
    {
        User instance = new User();
        instance.setEmail(mail);
        instance.setRole(Role.ROLE_USER);
        instance.setHash(passwd);
        return userRepo.save(instance);
    }
    public boolean isExistUser(UUID id)
    {
        return userRepo.existsById(id);
    }

    public void deleteUser(UUID id)
    {
        userRepo.deleteById(id);
    }

    public Optional<User> getUser(UUID id)
    {
        return userRepo.findById(id);
    }

    public List<User> getUsers()
    {
        return userRepo.findAll();
    }

    public void addToFavorites(UUID contentId, UUID userId)
    {
        Optional<User> data = getUser(userId);
        if (data.isPresent())
        {
            User _usr = data.get();
            _usr.getFavoriteList().add(contentRepo.getReferenceById(contentId));
            userRepo.save(_usr);
        }
    }

    public void removeFromFavorites(UUID contentId, UUID userId)
    {
        Optional<User> data = getUser(userId);
        if (data.isPresent())
        {
            User _usr = data.get();
            _usr.getFavoriteList().remove(contentRepo.getReferenceById(contentId));
            userRepo.save(_usr);
        }
    }

    public Set<Content> getFavorites(UUID userId)
    {
        return userRepo.getById(userId).getFavoriteList();
    }

    public void addToHistory(UUID contentId, UUID userId)
    {
        Optional<User> data = getUser(userId);
        if (data.isPresent())
        {
            User _usr = data.get();
            _usr.getHistory().add(contentRepo.getReferenceById(contentId));
            userRepo.save(_usr);
        }
    }

    public void removeFromHistory(UUID contentId, UUID userId)
    {
        Optional<User> data = getUser(userId);
        if (data.isPresent())
        {
            User _usr = data.get();
            _usr.getHistory().remove(contentRepo.getReferenceById(contentId));
            userRepo.save(_usr);
        }
    }

    public Set<Content> getHistory(UUID userId)
    {
        return userRepo.getById(userId).getHistory();
    }
}
