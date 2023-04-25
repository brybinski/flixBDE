package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Season;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID>
{
}