package com.pmp.mock_service;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class StatusCheckController {

    @GetMapping("/200")
    ResponseEntity<String> response200String() {
        return ResponseEntity.ok().body("200");
    }

    @GetMapping("/500")
    ResponseEntity<String> response500String() {
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("/503")
    ResponseEntity<String> response503String() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
