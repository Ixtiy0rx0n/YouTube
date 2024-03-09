package com.example.controller;

import com.example.config.CustomUserDetails;
import com.example.dto.CommentDTO;
import com.example.dto.info.CommentInfo;
import com.example.dto.info.CommentProfileInfo;
import com.example.enums.ProfileRole;
import com.example.service.CommentService;
import com.example.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    //1
    @PostMapping("/created")
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<String>created(@RequestBody CommentDTO dto){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(commentService.created(dto,customUserDetails.getId()));
    }
    //2
    @PutMapping("/update/{commentId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>update(@RequestBody CommentDTO dto,
                                        @PathVariable Integer commentId){

        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        if (customUserDetails.getRole().equals(ProfileRole.ROLE_OWNER)){
            return ResponseEntity.ok(commentService.updateAdmin(dto,commentId));
        }
        return ResponseEntity.ok(commentService.updateUser(dto,commentId,customUserDetails.getId()));
    }
//3
   @DeleteMapping("/deleted/{commentId}")
   @PreAuthorize("hasAnyRole('USER','OWNER','ADMIN')")
   public ResponseEntity<String>delete(@PathVariable Integer commentId){
       return ResponseEntity.ok(commentService.deleted(SpringSecurityUtil.getCurrentUser(),commentId));
   }
//4
   @GetMapping("/getCommitPagination")
   @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageImpl<CommentDTO>>getPage(@RequestParam(value = "page")Integer page,
                                                       @RequestParam(value = "size")Integer size){
        return ResponseEntity.ok(commentService.getPage(page,size));
   }
   //5
   @GetMapping("/getCommitListProfileId/{profileId}")
   @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<CommentProfileInfo>getCommitListProfileId(@PathVariable Integer profileId){
        return ResponseEntity.ok(commentService.getCommitListProfileId(profileId));
   }
   //6
    @GetMapping("/getCommitListUser")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<CommentProfileInfo>getCommitListUser(){
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(commentService.getCommitListProfileId(customUserDetails.getId()));
    }
    //7
    @GetMapping("/getCommitVideoId/{videoId}")
    public ResponseEntity<List<CommentInfo>>getCommitVideoId(@PathVariable UUID videoId){
        return ResponseEntity.ok(commentService.getCommitVideoId(videoId));
    }


}
