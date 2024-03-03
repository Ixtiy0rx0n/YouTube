package com.example.service;

import com.example.dto.VideoTagDTO;
import com.example.entity.VideoTagEntity;
import com.example.repository.VideoRepository;
import com.example.repository.VideoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;
    public String created(VideoTagDTO dto) {
        VideoTagEntity entity=new VideoTagEntity();
        entity.setTagId(dto.getTag_id());
        entity.setVideoId(dto.getVideo_id());
        entity.setCreatedDate(LocalDateTime.now());
        videoTagRepository.save(entity);
        return "created videoTag";
    }

    public String deleted(VideoTagDTO dto) {
        List<VideoTagEntity>videoTagEntityList=videoTagRepository.findAllByVideoIdAndTagId(dto.getVideo_id(),dto.getTag_id());
        videoTagRepository.deleteAll(videoTagEntityList);
        return "deleted tag";

    }


    public List<VideoTagDTO> getVideoTagList(UUID videoId) {
        List<VideoTagEntity> allByVideoId = videoTagRepository.findAllByVideoId(videoId);
        List<VideoTagDTO>videoTagDTOList=new ArrayList<>();
        for (VideoTagEntity entity:allByVideoId){
            videoTagDTOList.add(tagDTO(entity));
        }
        return videoTagDTOList;
    }
    public VideoTagDTO tagDTO(VideoTagEntity entity){
        VideoTagDTO dto=new VideoTagDTO();
        dto.setVideo_id(entity.getVideoId());
        dto.setTag_id(entity.getTagId());
        return dto;
    }
}
