package com.bde.flix.security.payloads;

import java.util.List;
import java.util.UUID;

public record SignInResponse(UUID id, String email, List<String> role) {
}
