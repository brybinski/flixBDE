package com.bde.flix.model.repository;

import com.bde.flix.model.entity.content.Episode;
import com.bde.flix.model.entity.content.Season;
import com.bde.flix.model.entity.content.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeasonRepository extends JpaRepository<Season, UUID>
{

    void deleteById(UUID id);

    Season getById(UUID id);

    List<Season> findBySeries(Series reference);
}