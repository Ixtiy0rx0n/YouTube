package com.example.dto;

import com.example.entity.ChannelEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.NotificationType;
import com.example.enums.SubscriptionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionDTO {

    private Integer id;

    private Integer profileId;

    private UUID channelId;

    private LocalDateTime createdDate;

    private LocalDateTime unsubscribeDate;

    private SubscriptionStatus status;

    private NotificationType notificationType;
}
