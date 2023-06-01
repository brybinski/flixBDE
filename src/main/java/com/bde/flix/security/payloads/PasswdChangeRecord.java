package com.bde.flix.security.payloads;

public record PasswdChangeRecord(String email, String passwd, String token) {
}
