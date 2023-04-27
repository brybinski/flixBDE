package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, UUID>
{
}
