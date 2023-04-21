package com.bde.flix.controller.Generate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

public record SeasonGenerateRecord(UUID id, String description) {

}