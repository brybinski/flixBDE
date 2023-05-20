package com.bde.flix.controller;

import com.fasterxml.jackson.databind.JsonNode;
import net.minidev.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
public class PosterController {
    public String posterUrl = "cld-sample-5.jpg";
//    public String posterUrl = "https://res.cloudinary.com/dg2xgkd9r/image/upload/v1684593017/cld-sample-5.jpg";
//    @GetMapping("/poster")
//    public Poster showPoster(
//            @RequestParam(required = false, defaultValue = "https://res.cloudinary.com/dg2xgkd9r/image/upload/v1684593017/cld-sample-5.jpg")String link
//    ){
//        return new Poster(link);
//    }


//    @GetMapping("/poster")
//    @ResponseBody
//    public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam(value = "jpg") boolean jpg) {
//        MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
//
//        File file = null;
//        try {
//            file = new ClassPathResource("sample-pictures/cld-sample-5.jpg").getFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        InputStream in = getClass()
//                .getResourceAsStream(file.getName());
//        return ResponseEntity.ok()
//                .contentType(contentType)
//                .body(new InputStreamResource(in));
//    }



    private static String encodeFileToBase64(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }


    @RequestMapping(value = "/poster", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getImage() throws IOException {

        var imgFile = new ClassPathResource("sample-pictures/cld-sample-5.jpg");
        File obrazDoZakodowania = new ClassPathResource("sample-pictures/cld-sample-5.jpg").getFile();

        String kod64ZObrazu = encodeFileToBase64(obrazDoZakodowania);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(imgFile.getInputStream()));
    }

    @GetMapping("/poster2")
    public String justBase64FromImage(){

        File obrazDoZakodowania = null;
        try {
            obrazDoZakodowania = new ClassPathResource("sample-pictures/cld-sample-5.jpg").getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return encodeFileToBase64(obrazDoZakodowania);

    }


        @GetMapping(path = "/poster3", produces=MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Object> sayHello()
        {
            //Get data from service layer into entityList.

            File obrazDoZakodowania = null;
            try {
                obrazDoZakodowania = new ClassPathResource("sample-pictures/cld-sample-5.jpg").getFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String zakodowany = encodeFileToBase64(obrazDoZakodowania);

            List<JSONObject> entities = new ArrayList<JSONObject>();
            JSONObject entity = new JSONObject();
            entity.put("aa", "bb");
            entity.put("obrazek", zakodowany);
            entities.add(entity);

            return new ResponseEntity<Object>(entities, HttpStatus.OK);
        }





}
