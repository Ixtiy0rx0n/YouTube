package com.example.dto.info;

import com.example.entity.ChannelEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.NotificationType;
import com.example.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class SubscriptionInfo {
    private Integer id;
    private UUID channelId;
    private String channelName;
    private String channelPhotoId;
    private String channelPhotoUrl;
    private NotificationType notificationType;
}
