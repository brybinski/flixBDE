package com.bde.flix.controller.Payload;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public record FilmRecord(
        String title,
        int duration,
        String description,
        Date releaseDate,
        String poster,
        String director,
        Set<String> actors_cast,
        Set<String> genreTag)
{
}
