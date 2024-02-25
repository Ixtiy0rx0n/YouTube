package com.example.repository;

import com.example.dto.TagDTO;
import com.example.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity,Integer> {

}
