package com.example.service;

import com.example.dto.AuthDTO;
import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.AppLanguage;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadException;
import com.example.repository.ProfileRepository;
import com.example.util.JWTUtil;
import com.example.util.MDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;

    public ProfileDTO auth(AuthDTO profile, AppLanguage language) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));

        if (optional.isEmpty()) {
            //   String message = resourceBundleMessageSource.getMessage("email.password.wrong", null, new Locale(language.name()));

            log.warn("Email or Password is wrong{}",profile.getEmail());
            throw new AppBadException(resourceBundleService.getMessage("email.password.wrong",language));
        }

        ProfileEntity entity = optional.get();

        if(!entity.getStatus().equals(ProfileStatus.ACTIVE)){
            throw new AppBadException("Profile not active");
        }
        ProfileDTO dto = new ProfileDTO();
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setJwt(JWTUtil.encode(entity.getEmail()

                ,entity.getRole()));
        return dto;
    }

    public String emailVerification(String jwt) {
        JwtDTO decode =
                JWTUtil.decode(jwt);


    }
}
