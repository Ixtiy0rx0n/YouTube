package com.example.service;

import com.example.dto.ChangeDTO;
import com.example.dto.JwtDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.AppLanguage;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.exp.AppBadException;
import com.example.repository.EmailSendHistoryRepository;
import com.example.repository.ProfileRepository;
import com.example.util.JWTUtil;
import com.example.util.MDUtil;
import com.example.util.RandomUtil;
import io.jsonwebtoken.JwtException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendHistoryRepository emailSendHistoryRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private ResourceBundleService resourceBundleService;
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
        admin.setPhone(registrationDTO.getPhone());
        admin.setPassword(MDUtil.encode(registrationDTO.getPassword()));
        profileRepository.save(admin);
        return "created profile";
    }

    public String changePassword(String parol, Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        byId.get().setPassword(MDUtil.encode(parol));
        profileRepository.save(byId.get());
        return "change password";
    }

    public String changeEmail(ChangeDTO dto,Integer profileId) {

        Optional<ProfileEntity> byId = profileRepository.findById(profileId);
        ProfileEntity profileEntity = byId.get();
        String jwt = JWTUtil.encodeForSpringSecurity2(dto.getEmail(), profileEntity.getRole(), profileId);
        String text = "<h1 style=\"text-align: center\">Hello %s</h1>\n" +
                "<p style=\"background-color: indianred; color: white; padding: 30px\">To complete registration please link to the following link</p>\n" +
                "<a style=\" background-color: #f44336;\n" +
                "  color: white;\n" +
                "  padding: 14px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\" href=\"http://localhost:8080/api/auth/verification/email/%s\n" +
                "\">Click</a>\n" +
                "<br>\n";
        text = String.format(text,"Hello Ozod", jwt);
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
        return "true";
    }

    public String updateNameSurname(Integer id, ChangeDTO dto) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        byId.get().setName(dto.getName());
        byId.get().setSurname(dto.getSurName());
        profileRepository.save(byId.get());
        return "update name surname";
    }

    public String emailVerification(String jwt, AppLanguage language) {
        JwtDTO jwtDTO = JWTUtil.decodeForSpringSecurity2(jwt);
        Optional<ProfileEntity> byId = profileRepository.findById(jwtDTO.getId());
        if (byId.isEmpty()){
            log.warn("Profile not found {}", jwtDTO.getId());
            throw new AppBadException(resourceBundleService.getMessage("profile not found",language));
        }
        ProfileEntity profileEntity = byId.get();
        profileRepository.updateEmail(profileEntity.getId(), jwtDTO.getEmail());
        return "update email";
    }


    public List<ProfileDTO> getProfileDetail() {
        List<ProfileEntity> all = profileRepository.findAll();
        List<ProfileDTO>profileDTOList=new ArrayList<>();
        for (ProfileEntity profileEntity:all){
            ProfileDTO profileDTO=new ProfileDTO();
            profileDTO.setId(profileEntity.getId());
            profileDTO.setName(profileEntity.getName());
            profileDTO.setSurname(profileEntity.getSurname());
            profileDTO.setEmail(profileEntity.getEmail());
            if (profileEntity.getPhotoId()!=null){

            }
            profileDTO.setAttachUrl("null");
            profileDTOList.add(profileDTO);
        }
        return profileDTOList;
    }
}
