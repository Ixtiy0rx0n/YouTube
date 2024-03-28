package com.example.service;

import com.example.dto.SubscriptionDTO;
import com.example.dto.info.ChangeSubscriptionInfoNotificationType;
import com.example.dto.info.ChangeSubscriptionInfoStatus;
import com.example.dto.info.SubscriptionInfo;
import com.example.dto.info.SubscriptionInfoCreatedDate;
import com.example.entity.SubscriptionEntity;
import com.example.enums.ProfileStatus;
import com.example.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    public String created(SubscriptionDTO dto, Integer id) {
        SubscriptionEntity entity=new SubscriptionEntity();
        entity.setChannelId(dto.getChannelId());
        entity.setProfileId(id);
        entity.setStatus(dto.getStatus());
        entity.setNotificationType(dto.getNotificationType());
        entity.setCreatedDate(LocalDateTime.now());
        subscriptionRepository.save(entity);
        return "created subscription";
    }

    public String changeSubscription(ChangeSubscriptionInfoStatus info, Integer id) {
        List<SubscriptionEntity> allByProfileId = subscriptionRepository.findAllByProfileId(id);
        for (SubscriptionEntity entity:allByProfileId){
            entity.setStatus(info.getStatus());
            entity.setChannelId(info.getChannel_id());
        }
        for (SubscriptionEntity entity:allByProfileId){
            subscriptionRepository.save(entity);
        }
        return "change subscription status";
    }

    public String changeSubscriptionNotificationType(Integer id, ChangeSubscriptionInfoNotificationType type) {
        List<SubscriptionEntity> allByChannelId = subscriptionRepository.findAllByChannelId(type.getChannel_id());
        for (SubscriptionEntity entity:allByChannelId){
            if (entity.getProfileId().equals(id)){
                entity.setNotificationType(type.getType());
            }
            subscriptionRepository.save(entity);
        }
       return "change subscription notification type";
    }

    public List<SubscriptionInfo> getUserSubscription(Integer id) {
        List<SubscriptionEntity> allByProfileId = subscriptionRepository.findAllByProfileId(id);
        List<SubscriptionInfo>infoList=new ArrayList<>();
        for (SubscriptionEntity entity:allByProfileId){
            infoList.add(getSubscriptionInfo(entity));
        }
        return infoList;
    }


    public List<SubscriptionInfoCreatedDate> getUserSubscriptionAdmin(Integer userId) {
        List<SubscriptionEntity> allByProfileId = subscriptionRepository.findAllByProfileId(userId);
        List<SubscriptionInfoCreatedDate>infoCreatedDates=new ArrayList<>();
        for (SubscriptionEntity entity:allByProfileId){
            if (entity.getProfileEntity().getStatus().equals(ProfileStatus.ACTIVE)){
                SubscriptionInfoCreatedDate infoCreatedDate=new SubscriptionInfoCreatedDate();
                infoCreatedDate.setInfo(getSubscriptionInfo(entity));
                infoCreatedDate.setCreated_date(entity.getCreatedDate());
                infoCreatedDates.add(infoCreatedDate);
            }
        }
        return infoCreatedDates;
    }
    public SubscriptionInfo getSubscriptionInfo(SubscriptionEntity entity){
        SubscriptionInfo subscriptionInfo=new SubscriptionInfo();
        subscriptionInfo.setId(entity.getId());
        subscriptionInfo.setChannelId(entity.getChannelId());
        // subscriptionInfo.setChannelName(entity.getChannelEntity().getName());
        subscriptionInfo.setNotificationType(entity.getNotificationType());
        //subscriptionInfo.setChannelPhotoId(entity.getChannelEntity().getPhotoId());
        //subscriptionInfo.setChannelPhotoUrl(entity.getChannelEntity().getAttachEntity().getPath());
        return subscriptionInfo;
    }
}
