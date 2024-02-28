package com.example.dto;

import com.example.enums.PlaylistStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlaylistDTO {

    private Integer id;

    private UUID channelId;

    private String name;

    private String description;

    private PlaylistStatus status;

    private Integer orderNumber;
}
