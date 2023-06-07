package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.userman.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    void deleteById(UUID id);

    User getById(UUID id);
}
