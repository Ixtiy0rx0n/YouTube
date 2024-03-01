package com.example.service;

import com.example.dto.TagDTO;
import com.example.entity.TagEntity;
import com.example.exp.AppBadException;
import com.example.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    public String create(TagDTO tagDTO) {
        TagEntity tagEntity=new TagEntity();
        tagEntity.setName(tagDTO.getName());
        tagEntity.setCreatedDate(LocalDateTime.now());
        tagRepository.save(tagEntity);
        return "created tag";
    }

    public String update(Integer id, TagDTO tagDTO) {
        Optional<TagEntity> optionalTag = tagRepository.findById(id);
        if (optionalTag.isEmpty()){
            log.warn("update tage");
            throw new AppBadException("Bunday tag yoq");
        }
        TagEntity tagEntity = optionalTag.get();
        tagEntity.setName(tagDTO.getName());
        tagEntity.setUpdatedDate(LocalDateTime.now());
        tagRepository.save(tagEntity);
        return "update tag";
    }

    public String delete(Integer id) {
        tagRepository.deleteById(id);
        return "deleted tag";
    }

    public List<TagDTO> getAll() {
        List<TagEntity> repositoryAll = tagRepository.findAll();
        List<TagDTO>tagDTOList=new ArrayList<>();
        for (TagEntity tagEntity:repositoryAll){
            TagDTO tagDTO=new TagDTO();
            tagDTO.setName(tagEntity.getName());
            tagDTO.setId(tagEntity.getId());
            tagDTOList.add(tagDTO);
        }
        return tagDTOList;
    }
}
