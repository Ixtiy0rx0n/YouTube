package com.example.controller;
import com.example.config.CustomUserDetails;
import com.example.dto.AuthDTO;
import com.example.dto.ProfileDTO;
import com.example.enums.AppLanguage;
import com.example.service.AuthService;
import com.example.service.ProfileService;
import com.example.util.SpringSecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "Authorization Api list", description = "Api list for Authorization")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    @Operation( summary = "Api for login", description = "this api used for authorization")
    public ResponseEntity<ProfileDTO> login(@RequestHeader(value = "Accept-Language",defaultValue = "uz") AppLanguage language,
                                            @RequestBody AuthDTO auth){
        log.info("Login {}",auth.getEmail());
        ProfileDTO autht = authService.auth(auth,language);
        return ResponseEntity.ok(autht);
    }

    @PutMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable String jwt) {
        String s = profileService.emailVerification(jwt,AppLanguage.eng);
        return ResponseEntity.ok(s);
    }
}
