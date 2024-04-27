package com.luisbicho.dscatalog.services;

import com.luisbicho.dscatalog.dto.EmailDTO;
import com.luisbicho.dscatalog.dto.NewPasswordDTO;
import com.luisbicho.dscatalog.entities.PasswordRecover;
import com.luisbicho.dscatalog.entities.User;
import com.luisbicho.dscatalog.repositories.PasswordRecoverRepository;
import com.luisbicho.dscatalog.repositories.UserRepository;
import com.luisbicho.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @Transactional
    public void createRecoverToken(EmailDTO emailDTO) {

        User user = userRepository.findByEmail(emailDTO.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(emailDTO.getEmail());
        entity.setToken(UUID.randomUUID().toString());
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity = passwordRecoverRepository.save(entity);

        String text = "Access the link to set your new password: " + recoverUri + entity.getToken() + ". Valid for " + tokenMinutes + " minutes";

        emailService.sendEmail(emailDTO.getEmail(), "Password recover", text);


    }

    @Transactional
    public void saveNewPassword(NewPasswordDTO dto) {

        List<PasswordRecover> result = passwordRecoverRepository.searchValidTokens(dto.getToken(), Instant.now());
        if (result.size() == 0) {
            throw new ResourceNotFoundException("Token not found");
        }
        User user = userRepository.findByEmail(result.get(0).getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
    }

}
