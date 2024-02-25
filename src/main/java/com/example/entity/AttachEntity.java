package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "attach_entity")
public class AttachEntity {
    @Id
    private String id;
    @Column(name = "origin_name")
    private String originName;
    @Column(name = "size")
    private Long size;
    @Column(name = "extension")
    private String extension;
    @Column(name = "path")
    private String path;
    @Column(name = "created_date")
    private LocalDateTime createdData;
}
