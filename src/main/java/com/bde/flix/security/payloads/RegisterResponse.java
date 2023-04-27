package com.bde.flix.security.payloads;

import java.util.UUID;

public record RegisterResponse(UUID id, String email, String Message) {
}
