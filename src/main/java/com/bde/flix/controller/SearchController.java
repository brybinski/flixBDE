package com.bde.flix.controller;

import com.bde.flix.controller.Payload.IdRecord;
import com.bde.flix.controller.Payload.SearchRecord;
import com.bde.flix.controller.Payload.SeasonRecord;
import com.bde.flix.controller.Payload.TagsRecord;
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


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/search/")
    public ResponseEntity<List<Content>> SearchContentByTitle(@RequestBody SearchRecord record)
    {
        try
        {
            List<Content> result = new ArrayList<Content>();
            if (record.q()!= null && !record.q().isEmpty())
                result.addAll(contService.getContentByTitle(record.q()));
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("api/search/tags")
    public ResponseEntity<List<Content>> SearchContentByTags(@RequestBody TagsRecord record)
    {
        try
        {
            List<Content> result = new ArrayList<Content>();
            if (record.q() != null && !record.q().isEmpty())
                result.addAll(contService.getContentWithTags(record.q()));
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);


        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("api/search/")
    public ResponseEntity<List<Content>> SearchContentByPart(@RequestBody SearchRecord record)
    {
        try
        {
            List<Content> result = new ArrayList<Content>();
            if (record.q() == null)
                result.addAll(contService.getAllContent());
            else
                result.addAll(contService.getContentContainingPart(record.q()));

            if (!result.isEmpty())
                return new ResponseEntity<>(result, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}