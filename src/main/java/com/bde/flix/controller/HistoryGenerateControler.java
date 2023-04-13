package com.bde.flix.controller;

import com.bde.flix.Service.HistoryService;
import com.bde.flix.model.entity.History;
import com.bde.flix.model.entity.content.TestEntity;
import com.bde.flix.model.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InvalidAttributeValueException;
import java.sql.Timestamp;
import java.util.UUID;

@RestController
public class HistoryGenerateControler {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/hist-create")
    public  <T> T genHist(@RequestParam(required = true) UUID content_uuid,
                      @RequestParam(required = true) UUID acc_uuid,
                      @RequestParam(required = true) boolean episode,
                      @RequestParam(required = true) int watchtime){

        try {
            History.ContentType ct = History.ContentType.EPISODE;

            if (!episode){
                ct = History.ContentType.FILM;
            }
            History entity = historyService.createHistory(content_uuid, acc_uuid, episode, watchtime);


            return (T) new HistoryGenerateRecord(entity.getId().toString(),
                    entity.getAccount().getId().toString(),
                    entity.getContentId().toString(),
                    ct
            );
        }
        catch (InvalidAttributeValueException e){
            return (T) new ErrorRecord(e, new Timestamp(System.currentTimeMillis()));
        }

    }
}