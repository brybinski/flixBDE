package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Content;
import com.bde.flix.model.entity.content.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film, UUID>
{
    void deleteById(UUID id);

    Film getById(UUID id);

    List<Film> findByTitleContaining(String infix);
}
