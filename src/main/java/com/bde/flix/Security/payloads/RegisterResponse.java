package com.bde.flix.Security.payloads;

import java.util.UUID;

public record RegisterResponse(UUID id, String email, String Message) {
}
