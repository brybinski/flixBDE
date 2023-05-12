package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<Content, UUID> {

    List<Content> findByTitleContaining(String infix);

    List<Content> findByGenreTagContaining(String infix);

    List<Content> findByTitleContainingOrDescriptionContaining(String infix, String infix1);
}