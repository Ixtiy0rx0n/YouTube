package com.example.service;

import com.example.dto.VideoDTO;
import com.example.dto.info.VideoShortInfo;
import com.example.entity.VideoEntity;
import com.example.exp.AppBadException;
import com.example.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    public String create(VideoDTO dto) {
        videoRepository.save(toEntity(dto));
        return "created video";
    }

    public String update(VideoDTO dto, UUID uuid) {
        Optional<VideoEntity> optionalVideo = videoRepository.findById(uuid);
        if (optionalVideo.isEmpty()){
            throw new AppBadException("Not found video");
        }
        VideoEntity entity = optionalVideo.get();
        entity.setTitle(dto.getTitle());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setViewCount(dto.getViewCount());
        entity.setSharedCount(dto.getSharedCount());
        entity.setDislikeCount(dto.getDislikeCount());
        entity.setLikeCount(dto.getLikeCount());
        entity.setDescription(dto.getDescription());
        videoRepository.save(entity);
        return "update video";
    }
    public VideoEntity toEntity(VideoDTO dto){
        VideoEntity entity=new VideoEntity();
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setTitle(dto.getTitle());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPublishedDate(LocalDateTime.now());
        entity.setStatus(dto.getStatus());
        entity.setType(dto.getType());
        entity.setViewCount(dto.getViewCount());
        entity.setSharedCount(dto.getSharedCount());
        entity.setDislikeCount(dto.getDislikeCount());
        entity.setLikeCount(dto.getLikeCount());
        entity.setChannelId(dto.getChannelId());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public String updateChangeStatus(VideoDTO dto, UUID videoId) {

        Optional<VideoEntity> optionalVideo = videoRepository.findById(videoId);
        if (optionalVideo.isEmpty()){
            throw new AppBadException("not found video");
        }
        VideoEntity entity = optionalVideo.get();
        entity.setStatus(dto.getStatus());
        videoRepository.save(entity);
        return "change status video";
    }

    public VideoDTO getViewCount(UUID videoId) {
        Optional<VideoEntity> optionalVideo = videoRepository.findById(videoId);
        if (optionalVideo.isEmpty()){
            throw new AppBadException("not found video");
        }
        VideoEntity entity = optionalVideo.get();
        VideoDTO dto=new VideoDTO();
        dto.setViewCount(entity.getViewCount());
        return dto;
    }

    public PageImpl<VideoShortInfo> getPaginationCategoryId(Integer page, Integer size, Integer categoryId) {
        Pageable pageable= PageRequest.of(page-1,size);
        Page<VideoEntity>page1=videoRepository.findAll(pageable);
        List<VideoEntity>videoEntityList=page1.getContent();
        List<VideoShortInfo>videoShortInfoList=new ArrayList<>();
        for (VideoEntity entity:videoEntityList){
            videoShortInfoList.add(toVideoShortInfo(entity));
        }
        Long totalSize=page1.getTotalElements();
        return new PageImpl<>(videoShortInfoList,pageable,totalSize);
    }
    public VideoShortInfo toVideoShortInfo(VideoEntity entity){
         VideoShortInfo info=new VideoShortInfo();
         info.setVideoId(entity.getId());
         info.setVideoTitle(entity.getTitle());
         info.setPreview_attachId(entity.getPreviewAttachId());
         info.setPreview_attachUrl(entity.getPrevieiwAttachEntity().getPath());
         info.setPublished_date(entity.getPublishedDate());
         info.setChannelId(entity.getChannelId());
         info.setChannelName(entity.getChannelEntity().getName());
         info.setChannelPhotoUrl(entity.getChannelEntity().getAttachEntity().getPath());
         info.setVideo_view_count(entity.getViewCount());
         info.setDuration(entity.getAttachEntity().getDuration());
         return info;
    }
}
