package com.bde.flix.controller;

public record FilmRecord(String title,
                         int duration,
                         String description,
                         String releaseDate,
                         String poster,
                         String director,
                         String actors_cast)
{
}
