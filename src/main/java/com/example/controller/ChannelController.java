package com.example.controller;
import com.example.config.CustomUserDetails;
import com.example.dto.ChannelDTO;
import com.example.service.ChannelService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/channel")
public class ChannelController {
   @Autowired
   private ChannelService channelService;
   //1
    @PostMapping("/created")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>created(@RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.create(dto,customUserDetails.getId()));
    }
    //2
    @PutMapping("/update/{channelId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>update(@PathVariable(name = "channelId") UUID channelId,@RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.update(customUserDetails.getId(),dto,channelId));
    }

    //3
    @PutMapping("/updatePhoto/{channelId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>updatePhoto(@PathVariable(name = "channelId") UUID channelId,
                                             @RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.updatePhoto(customUserDetails.getId(),dto,channelId));
    }
    //4
    @PutMapping("/updateBanner/{channelId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>updateBanner(@PathVariable(name = "channelId") UUID channelId,
                                             @RequestBody ChannelDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(channelService.updateBanner(customUserDetails.getId(),dto,channelId));
    }
    //5
    @GetMapping("/getPagination")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<ChannelDTO>>getPaginationAdmin(@RequestParam(value = "page")Integer page,
                                                             @RequestParam(value = "size")Integer size){
        PageImpl<ChannelDTO> paginationAdmin = channelService.getPaginationAdmin(page, size);
        return ResponseEntity.ok(paginationAdmin);
    }

}




















