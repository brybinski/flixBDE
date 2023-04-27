package com.bde.flix.Security.Account;


import com.bde.flix.model.entity.userman.Account;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
@Getter
public class AccountDetails implements UserDetails {
    private UUID id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public AccountDetails(UUID id, String email, String password,
                          Collection<? extends GrantedAuthority> authorities) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static AccountDetails build(Account user) {

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()));

        return new AccountDetails(user.getId(),
                user.getEmail(),
                user.getHash(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
