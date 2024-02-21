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
    private String id;
    @NotBlank(message = "name  is required")
    private String name;
    @NotBlank(message = "username  is required")
    private String username;
    @NotBlank(message = "description is required")
    private String description;
    private ChannelStatus status;
    private String banner;
    @NotNull(message = "profile id is required")
    private Integer profileId;
    private Integer subscribeCount=0;

}
