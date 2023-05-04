package com.bde.flix.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController
{
    private static final String ID = "id";
    private static final String DESCRIPTION = "description";
    private static final String DIRECTOR = "director";
    private static final String DURATION = "duration";
    private static final String POSTER = "poster";
    private static final String RELEASE_DATE = "release_date";
    private static final String TITLE = "title";

    @CrossOrigin(origins = "http://localhost:8080")
    /*
    @GetMapping("/Search")
    public Search search(){
        return new Search(ID, DESCRIPTION, DIRECTOR, DURATION, POSTER, RELEASE_DATE, TITLE);
    }
    */

    @PostMapping(path = "search",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public Search search()
    {
        return new Search(ID, DESCRIPTION, DIRECTOR, DURATION, POSTER, RELEASE_DATE, TITLE);
    }
}




/*
String ID;
String DESCRIPTION;
String DIRECTOR;
String DURATION;
String POSTER;
String RELEASE_DATE;
String TITLE
 */
