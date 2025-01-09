package com.quizzapp.demo.Controller;

import org.hibernate.Session.LockRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Authentication {
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LockRequest loginRequest) {
        
        return ResponseEntity.ok().build();
    }
}
