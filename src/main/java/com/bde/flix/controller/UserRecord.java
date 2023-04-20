package com.bde.flix.controller;

//import org.springframework.http.HttpStatus;

import java.util.UUID;

public record UserRecord(UUID id, String mail, String passwd) {
}
//public record UserRecord(HttpStatus status) {
//}

//zakomentowane do szybszej implementacji