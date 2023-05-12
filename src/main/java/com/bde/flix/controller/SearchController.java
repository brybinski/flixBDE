package com.bde.flix.controller;

import com.bde.flix.model.entity.content.Content;
import com.bde.flix.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class SearchController
{
    @Autowired
    private ContentService contService;

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("api/search/{title}")
    public ResponseEntity<List<Content>> SearchContentByTitle(@PathVariable("title") String title)
    {
        List<Content> result = contService.getContentByTitle(title);
        if (result != null && !result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/search/tags")
    public List<Content> SearchContentByTags(@RequestParam(required = true) Set<String> tags)
    {
//        FIXME: search using multiple tags
        List<Content> result = contService.getContentWithTags(String.join(", ", tags));
        return result;
    }

    @PostMapping("api/search/")
    public ResponseEntity<List<Content>> SearchContentByPart(@RequestParam(required = false) String part)
    {
        try
        {
            List<Content> result = new ArrayList<Content>();
            if (part == null)
                contService.getAllContent().forEach(result::add);
            else
                contService.getContentContainingPart(part).forEach(result::add);

            if (result.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}