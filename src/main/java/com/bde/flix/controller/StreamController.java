package com.bde.flix.controller;

import com.bde.flix.model.entity.content.Content;
import com.bde.flix.service.ContentService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class StreamController {
    @Autowired
    private ContentService contService;
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("api/stream")
    public ResponseEntity<JSONObject> ServeContent(@RequestParam(required = true) UUID id){
        try
        {
            List<Content> everything = contService.getAllContent();
            for(Content content : everything)
            {
                if(content.getId().equals(id))
                {
                    JSONObject entity = new JSONObject();
                    entity.put("sourceLink",content.getSourceLink());
                    return new ResponseEntity<>(entity, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
