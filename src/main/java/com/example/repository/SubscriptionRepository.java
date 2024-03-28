package com.example.repository;

import com.example.entity.SubscriptionEntity;
import com.example.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubscriptionRepository
        extends JpaRepository<SubscriptionEntity,Integer> {
    List<SubscriptionEntity> findAllByProfileId(Integer profileId);
    List<SubscriptionEntity>findByChannelIdAndStatus(UUID channelId, SubscriptionStatus status);
    List<SubscriptionEntity>findAllByChannelId(UUID channelId);
}
