package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.content.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, UUID>
{
    void deleteById(UUID id);

    Episode getById(UUID id);

    List<Episode> findBySeason(Season reference);
}
