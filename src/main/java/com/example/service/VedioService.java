package com.example.service;

import org.springframework.stereotype.Service;

@Service
public class VedioService {

//    public PageImpl<VideoListPaginationDTO> getVideoListForAdmin(Integer page, Integer size, AppLanguage language) {
//        Pageable pageable = PageRequest.of(page - 1, size);
//        Page<VideoShortInfoPaginationMapper> entityPage = videoRepository.getVideoListForAdmin(pageable);
//
//        List<VideoShortInfoPaginationMapper> entityList = entityPage.getContent();
//        long totalElements = entityPage.getTotalElements();
//
//        List<VideoListPaginationDTO> dtoList = new LinkedList<>();
//        for (VideoShortInfoPaginationMapper entity : entityList) {
//            VideoDTO videoDTO = new VideoDTO();
//            videoDTO.setId(entity.getId());
//            videoDTO.setTitle(entity.geTitle());
//            if (entity.getPreviewAttachId() != null) {
//                videoDTO.setPreviewAttach(attachService.getURL(entity.getPreviewAttachId()));
//            }
//            videoDTO.setPublishedDate(entity.getPublishedDate());
//
//            ChannelDTO channelDTO = new ChannelDTO();
//            channelDTO.setId(entity.getChannelId());
//            channelDTO.setName(entity.getChannelName());
//            channelDTO.setPhotoId(entity.getPhotoId());
//            videoDTO.setChannel(channelDTO);
//
//            ProfileDTO profileDTO = new ProfileDTO();
//            profileDTO.setId(entity.getProfileId());
//            profileDTO.setName(entity.getProfileName());
//            profileDTO.setSurname(entity.getProfileSurname());
//            videoDTO.setOwner(profileDTO);
//
//            videoDTO.setPlayListJson(entity.getPlayListJson());
//        }
//        return new PageImpl<>(dtoList, pageable, totalElements);
//    }
}
