package com.example.dto;

import com.example.enums.PlaylistStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlayListShortInfo {
    private Integer id;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNumber;
    private UUID channelId;
    private String channelName;
    private String channelPhotoId;
    private String channelPhotoUrl;
    private Integer profileId;
    private String profileName;
    private String profileSurname;
    private String profilePhotoId;
    private String profilePhotoUrl;

}
