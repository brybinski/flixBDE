package com.bde.flix.model.repository;

import com.bde.flix.model.entity.userman.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Object> findByEmail(String username);
}