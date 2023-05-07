package com.bde.flix.security.payloads;

import java.util.List;

public record SignInResponse(String email, List<String> role) {
}
