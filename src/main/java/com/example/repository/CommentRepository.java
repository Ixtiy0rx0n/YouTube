package com.example.repository;

import com.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository extends
        JpaRepository<CommentEntity,Integer> {
    Optional<CommentEntity>findByProfileId(Integer profileId);
    List<CommentEntity>findByVideoId(UUID videoId);
}
