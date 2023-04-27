package com.bde.flix.Security.Account;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AccountDetailsService {
    AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
