package com.example.dto;

import com.example.enums.PlaylistStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistDTO {
    private String id;
    @NotNull(message = "channel id is required")
    private Integer channelId;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "description is required")
    @Size(min = 5,max = 255, message = "description is min=5 and max=255")
    private String description;
    private PlaylistStatus status;
    @NotNull(message = "order number is required")
    private Integer orderNumber;
}
