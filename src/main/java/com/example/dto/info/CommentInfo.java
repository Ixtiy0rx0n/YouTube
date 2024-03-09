package com.example.dto.info;

import com.example.entity.CommentEntity;
import com.example.entity.ProfileEntity;
import com.example.entity.VideoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CommentInfo {

    private Integer id;

    private String content;

    private LocalDateTime created_date;

    private Integer like_count;

    private Integer dislike_count;

    private Integer profileId;

    private String profileName;

    private String profileSurname;

    private String profilePhotoId;

    private String profileUrl;

}
