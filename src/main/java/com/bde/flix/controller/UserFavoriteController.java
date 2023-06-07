package com.bde.flix.controller;

import com.bde.flix.service.FilmService;
import com.bde.flix.service.SeriesService;
//import com.bde.flix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@RestController
public class UserFavoriteController {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private FilmService filmService;
//    @Autowired
//    private SeriesService seriesService;
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("api/user/addFavorite")
//    public ResponseEntity<HttpStatus> addFavoriteContent(@RequestBody UUID content, UUID user)
//    {
//        try
//        {
//            if (userService.isExistUser(user) && (seriesService.isExistSeries(content) || filmService.isExistFilm(content)))
//            {
//                userService.addToFavorites(content,user);
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//            else
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("api/user/deleteFavorite")
//    public ResponseEntity<HttpStatus> deleteFavoriteContent(@RequestBody UUID content, UUID user)
//    {
//        try
//        {
//            if (userService.isExistUser(user) && (seriesService.isExistSeries(content) || filmService.isExistFilm(content)))
//            {
//                userService.removeFromFavorites(content,user);
//                return new ResponseEntity<>(HttpStatus.OK);
//            }
//            else
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("api/user/favorite")
//    public ResponseEntity<Set<Optional>> getFavoriteContent(@RequestBody UUID user)
//    {
//        try {
//            Set<Optional> favoriteContent = userService.getFavorites(user);
//            if (!favoriteContent.isEmpty()) {
//                return new ResponseEntity<>(favoriteContent, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
