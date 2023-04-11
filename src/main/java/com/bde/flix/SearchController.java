package com.bde.flix;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
    private static final String ID = "ID".toLowerCase().toLowerCase();
    private static final String DESCRIPTION = "DESCRIPTION".toLowerCase();
    private static final String DIRECTOR = "DIRECTOR".toLowerCase();
    private static final String DURATION = "DURATION".toLowerCase();
    private static final String POSTER = "POSTER".toLowerCase();
    private static final String RELEASE_DATE = "RELEASE_DATE".toLowerCase();
    private static final String TITLE = "TITLE".toLowerCase();

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
    public Search search() {
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
