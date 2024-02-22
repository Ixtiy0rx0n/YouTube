package com.example.controller;

import com.example.dto.RegistrationDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
     @PostMapping("/create")
     @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<String>creted(@RequestBody RegistrationDTO registrationDTO){
       return ResponseEntity.ok(profileService.created(registrationDTO));
     }

}
