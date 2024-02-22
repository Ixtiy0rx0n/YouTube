//package com.example.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import java.time.LocalDateTime;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "attach")
//public class AttachEntity {
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    private Integer id;
//
//    @Column(name = "original_name")
//    private String originalName;
//    @Column(name = "size")
//    private Long size;
//
//    @Column(name = "type")
//    private String type;
//
//    @Column(name = "path")
//    private String path;
//
//    @Column(name = "created_date")
//    private LocalDateTime createdDate;
//
//    @Column(name = "visible")
//    private Boolean visible = true;
//
//    @Column(name = "duration", nullable = false)
//    private Integer duration;
//}
