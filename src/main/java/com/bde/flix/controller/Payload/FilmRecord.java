package com.bde.flix.controller.Payload;

import java.util.ArrayList;
import java.util.Set;

public record FilmRecord(String title,
                         int duration,
                         String description,
                         String releaseDate,
                         String poster,
                         String director,
                         Set<String> actors_cast,
                         Set<String> genreTag)
{
}
