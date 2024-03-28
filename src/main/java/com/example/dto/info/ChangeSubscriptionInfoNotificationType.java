package com.example.dto.info;

import com.example.enums.NotificationType;
import com.example.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class ChangeSubscriptionInfoNotificationType {
    private NotificationType type;
    private UUID channel_id;
}
