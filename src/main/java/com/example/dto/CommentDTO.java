package com.example.dto;

import com.example.entity.CommentEntity;
import com.example.entity.ProfileEntity;
import com.example.entity.VideoEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer profileId;

    private UUID videoId;

    private String content;

    private Integer replyId;

    private Integer like_count;

    private Integer dislike_count;

}
