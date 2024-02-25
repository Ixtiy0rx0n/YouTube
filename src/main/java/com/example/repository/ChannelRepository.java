package com.example.repository;

import com.example.entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChannelRepository extends JpaRepository<ChannelEntity, UUID> {
      List<ChannelEntity>findByProfileId(Integer profileId);
}
