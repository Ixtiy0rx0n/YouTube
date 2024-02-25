package com.example.service;

import com.example.dto.ChannelDTO;
import com.example.entity.ChannelEntity;
import com.example.exp.AppBadException;
import com.example.repository.ChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    public String create(ChannelDTO dto, Integer id) {
        ChannelEntity channelEntity=new ChannelEntity();
        channelEntity.setName(dto.getName());
        channelEntity.setPhotoId(dto.getPhotoId());
        channelEntity.setBannerId(dto.getBannerId());
        channelEntity.setStatus(dto.getStatus());
        channelEntity.setDescription(dto.getDescription());
        channelEntity.setProfileId(id);
        channelRepository.save(channelEntity);
        return "created channel";
    }

    public String update(Integer ownerId, ChannelDTO dto, UUID uuid) {
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(uuid);
        if (optionalChannel.isEmpty()){
           log.warn("chanel xatolik");
           throw new AppBadException("Bunday channel mavjud emas");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(ownerId)){
            throw new AppBadException("Bu kanal sizga tegishli emas");
        }
        if (dto.getBannerId()!=null){
            channelEntity.setBannerId(dto.getBannerId());
        }
        if (dto.getPhotoId()!=null){
            channelEntity.setPhotoId(dto.getPhotoId());
        }
        if (dto.getDescription()!=null){
            channelEntity.setDescription(dto.getDescription());
        }
        if (dto.getName()!=null){
            channelEntity.setName(dto.getName());
        }
        if (dto.getProfileId()!=null){
            channelEntity.setProfileId(dto.getProfileId());
        }
        if (dto.getStatus()!=null){
            channelEntity.setStatus(dto.getStatus());
        }
        channelRepository.save(channelEntity);
        return "update channel";
    }

    public String updatePhoto(Integer profileId, ChannelDTO dto, UUID channelId) {
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(channelId);
        if (optionalChannel.isEmpty()){
            log.warn("not found channel");
            throw new AppBadException("not found channel");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(profileId)){
            throw new AppBadException("Sizda bunday channel mavjud emas");
        }
        channelEntity.setPhotoId(dto.getPhotoId());
        channelRepository.save(channelEntity);
        return "update channel";
    }

    public String updateBanner(Integer profileId, ChannelDTO dto, UUID channelId) {
        Optional<ChannelEntity> optionalChannel = channelRepository.findById(channelId);
        if (optionalChannel.isEmpty()){
            log.warn("not found channel");
            throw new AppBadException("not found channel");
        }
        ChannelEntity channelEntity = optionalChannel.get();
        if (!channelEntity.getProfileId().equals(profileId)){
            throw new AppBadException("Sizda bunday channel mavjud emas");
        }
        channelEntity.setBannerId(dto.getBannerId());
        channelRepository.save(channelEntity);
        return "update channel";

    }

    public PageImpl<ChannelDTO> getPaginationAdmin(Integer page, Integer size) {

    }
}
