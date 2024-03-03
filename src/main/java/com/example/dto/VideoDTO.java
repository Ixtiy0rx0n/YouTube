package com.example.dto;

import com.example.enums.VideoStatus;
import com.example.enums.VideoType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO {
    private UUID id;
    private String previewAttachId;
    private String title;
    private Integer categoryId;
    private String attachId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private VideoStatus status;
    private VideoType type;
    private Integer viewCount;
    private Integer sharedCount;
    private String description;
    private UUID channelId;
    private Integer likeCount;
    private Integer dislikeCount;
}