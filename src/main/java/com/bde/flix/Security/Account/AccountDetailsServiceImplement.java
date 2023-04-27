package com.bde.flix.Security.Account;

import com.bde.flix.model.entity.userman.Account;
import com.bde.flix.model.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsServiceImplement implements UserDetailsService {

    @Autowired
    AccountRepository accRepo;

    @Override
    @Transactional
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = (Account) accRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username:" + username));

        return AccountDetails.build(user);
    }

}
