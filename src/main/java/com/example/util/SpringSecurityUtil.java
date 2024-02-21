package com.example.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {

    public static SpringSecurityUtil getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName(); // username
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SpringSecurityUtil userDetails = (SpringSecurityUtil) authentication.getPrincipal();
        return userDetails;
    }
}
