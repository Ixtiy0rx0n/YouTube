package com.example.dto;

import com.example.enums.ChannelStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String name;
    private String photoId;
    private String description;
    private ChannelStatus status;
    private String bannerId;
    private Integer profileId;
    private Integer subscribeCount=0;
}
