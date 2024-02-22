package com.example.controller;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.enums.AppLanguage;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "Authorization Api list", description = "Api list for Authorization")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    @Operation( summary = "Api for login", description = "this api used for authorization")
    public ResponseEntity<ProfileDTO> login(@RequestHeader(value = "Accept-Language",defaultValue = "uz") AppLanguage language,
                                            @RequestBody AuthDTO auth){
        log.trace("Login In Trace");
        log.debug("Login In Debug");
        log.info("Login {}",auth.getEmail());
        log.warn("Login {}",auth.getEmail());
        log.error("Login {}",auth.getEmail());
        ProfileDTO autht = authService.auth(auth,language);
        return ResponseEntity.ok(autht);
    }

}
