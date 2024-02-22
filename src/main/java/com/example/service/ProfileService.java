package com.example.service;

import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.repository.ProfileRepository;
import com.example.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    public String created(RegistrationDTO registrationDTO) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(registrationDTO.getEmail());
        if (optional.isPresent()) {
            return null;
        }
        // create admin
        ProfileEntity admin = new ProfileEntity();
        admin.setName(registrationDTO.getName());
        admin.setSurname(registrationDTO.getSurname());
        admin.setEmail(registrationDTO.getEmail());
        admin.setStatus(registrationDTO.getStatus());
        admin.setRole(registrationDTO.getProfileRole());
        admin.setPassword(MDUtil.encode(registrationDTO.getPassword()));
        profileRepository.save(admin);
        return admin.getPassword();
    }
}
