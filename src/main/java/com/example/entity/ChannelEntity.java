package com.example.entity;

import com.example.enums.ChannelStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
@Table(name = "channel")
public class ChannelEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "status",nullable = false)
    private ChannelStatus status;

    @Column(name = "profile_id", nullable = false)
    private Integer profileId;

    @ManyToOne
    @Column(name = "profile_id", nullable = false)
    private ProfileEntity profile;
    @Column(name = "subscribe_count")
    private Integer subscribeCount;

}
