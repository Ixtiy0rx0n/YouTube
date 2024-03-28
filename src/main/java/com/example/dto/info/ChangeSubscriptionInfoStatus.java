package com.example.dto.info;

import com.example.enums.SubscriptionStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ChangeSubscriptionInfoStatus {
   private SubscriptionStatus status;
   private UUID channel_id;
}
