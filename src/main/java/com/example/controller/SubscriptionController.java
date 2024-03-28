package com.example.controller;

import com.example.config.CustomUserDetails;
import com.example.dto.SubscriptionDTO;
import com.example.dto.info.ChangeSubscriptionInfoNotificationType;
import com.example.dto.info.ChangeSubscriptionInfoStatus;
import com.example.dto.info.SubscriptionInfo;
import com.example.dto.info.SubscriptionInfoCreatedDate;
import com.example.service.SubscriptionService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>created(@RequestBody SubscriptionDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.created(dto,customUserDetails.getId()));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>changeSubscription(@RequestBody ChangeSubscriptionInfoStatus info){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.changeSubscription(info,customUserDetails.getId()));
    }

    @PutMapping("/updateNotificationType")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>changeSubscriptionNotificationType(@RequestBody
                                                                        ChangeSubscriptionInfoNotificationType type){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.changeSubscriptionNotificationType(customUserDetails.getId(),type));
    }

    @GetMapping("/getusersubscription")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<List<SubscriptionInfo>>getusersubscription(){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.getUserSubscription(customUserDetails.getId()));
    }

    @GetMapping("/getusersubscriptionadmin/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<SubscriptionInfoCreatedDate>>getUserSubscriptionAdmin(@PathVariable Integer userId){
//        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(subscriptionService.getUserSubscriptionAdmin(userId));
    }


}
