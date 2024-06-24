package com.misha.booknetwork.services;

import com.misha.booknetwork.dto.RegistrationRequest;
import com.misha.booknetwork.ema.EmailService;
import com.misha.booknetwork.emails.EmailService;
import com.misha.booknetwork.repository.RoleRepository;
import com.misha.booknetwork.repository.TokenReposiotry;
import com.misha.booknetwork.repository.UserRepository;
import com.misha.booknetwork.user.Token;
import com.misha.booknetwork.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private TokenReposiotry tokenReposiotry;
    private final EmailService emailService;

    // 1 sign role to user
    //2 Create User object
    //3 Email validation
    //4 Send message through java mail
    public void register(RegistrationRequest request) {

        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER wasnt initialized"));

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);



    }

    private void sendValidationEmail(User user) {
        var newToken = generateAndSaveActivationToken(user);
        //send email;
    }

    private String generateAndSaveActivationToken(User user) {
        //1 generate token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenReposiotry.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i =0; i< length; i++){
            int random = secureRandom.nextInt(characters.length()); //0 .. 9
            codeBuilder.append(characters.charAt(random));
        }
        return null;
    }
}
