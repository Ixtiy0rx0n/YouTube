package com.example.dto;

import com.example.enums.VideoStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO {
    private String id;
    private Integer preview_attach_id;
    private String title;
    private Integer categoryId;
    private String attachId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private VideoStatus status;
    private String type="video";//video or short
    private Integer viewCount=0;
    private Integer sharedCount=0;
    private String description;
    private String channelId;
    private Integer likeCount=0;
    private Integer dislikeCount=0;
}
