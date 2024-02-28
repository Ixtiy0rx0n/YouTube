package com.example.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "video_entity")
public class VideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
