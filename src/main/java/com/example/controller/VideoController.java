package com.example.controller;

import com.example.dto.VideoDTO;
import com.example.dto.info.VideoShortInfo;
import com.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
   @PostMapping("/create")
   @PreAuthorize("hasAnyRole('USER','OWNER')")
   public ResponseEntity<String>create(@RequestBody VideoDTO dto){
       return ResponseEntity.ok(videoService.create(dto));
   }
  @PutMapping("/update/{videoId}")
  @PreAuthorize("hasAnyRole('USER','OWNER')")
  public ResponseEntity<String>update(@RequestBody VideoDTO dto,
                                        @PathVariable UUID videoId){
       return ResponseEntity.ok(videoService.update(dto,videoId));
  }
    @PutMapping("/updateChanche/{videoId}")
    @PreAuthorize("hasAnyRole('USER','OWNER')")
    public ResponseEntity<String>updateChangeStatus(@RequestBody VideoDTO dto,
                                                    @PathVariable UUID videoId){
      return ResponseEntity.ok(videoService.updateChangeStatus(dto,videoId));
    }
   @GetMapping("/getViewCount/{videoId}")
    public ResponseEntity<VideoDTO>getViewCount(@PathVariable UUID videoId){
      return ResponseEntity.ok(videoService.getViewCount(videoId));
   }

   @GetMapping("/getPaginationCategoryId")
    public ResponseEntity<PageImpl<VideoShortInfo>>getPaginationCategoryId1(@RequestParam(value = "page")Integer page,
                                                                          @RequestParam(value = "size")Integer size,
                                                                          @RequestParam(value = "categoryId") Integer categoryId){
       return ResponseEntity.ok(videoService.getPaginationCategoryId(page,size,categoryId));
   }


}
