package com.bde.flix.Security.payloads;

import java.util.List;
import java.util.UUID;

public record SignInResponse(UUID id, String email, List<String> role) {
}
