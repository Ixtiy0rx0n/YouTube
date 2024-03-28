package com.example.dto.info;

import com.example.enums.NotificationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class SubscriptionInfoCreatedDate {
   private SubscriptionInfo info;
    private LocalDateTime created_date;
}
