package com.bde.flix.model.repository;

import com.bde.flix.model.entity.userman.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
}