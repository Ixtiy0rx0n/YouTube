package com.example.service;

import com.example.dto.ChangeDTO;
import com.example.dto.JwtDTO;
import com.example.dto.RegistrationDTO;
import com.example.entity.ProfileEntity;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private  String fromAccount;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendHistoryRepository emailSendHistoryRepository;
    @Autowired
    private MailSenderService mailSenderService;
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
        return "created profile";
    }

    public String changePassword(String parol, Integer id) {
        Optional<ProfileEntity> byId = profileRepository.findById(id);
        byId.get().setPassword(MDUtil.encode(parol));
        profileRepository.save(byId.get());
        return "change password";
    }

    public String changeEmail(ChangeDTO dto,Integer profileId) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                profileRepository.delete(optional.get());
            } else {
                throw new AppBadException("Email exists");
            }
        }
        LocalDateTime from=LocalDateTime.now().minusMinutes(1);
        LocalDateTime to=LocalDateTime.now();
        if (emailSendHistoryRepository.countSendEmail(dto.getEmail(), from, to) >= 3) {
            throw new AppBadException("To many attempt. Please try after 1 minute.");
        }
       //        // create
//        ProfileEntity entity = new ProfileEntity();
//        entity.setName(dto.getName());
//        entity.setSurname(dto.getSurname());
//        entity.setEmail(dto.getEmail());
//        entity.setPassword(MDUtil.encode(dto.getPassword()));
//        entity.setStatus(ProfileStatus.REGISTRATION);
//        entity.setRole(ProfileRole.ROLE_USER);
//        entity.setPhone(dto.getPhone());
//        profileRepository.save(entity);
        String jwt= JWTUtil.encodeForEmail(dto.getId());

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
        text = String.format(text,dto.getName(), jwt);
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

}
