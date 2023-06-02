package com.bde.flix.model.repository;
import com.bde.flix.model.entity.content.Film;
import com.bde.flix.model.entity.content.Season;
import com.bde.flix.model.entity.content.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SeriesRepository extends JpaRepository<Series, UUID>
{
    void deleteById(UUID id);

    Series getById(UUID id);

    List<Series> findByTitleContaining(String infix);

}
