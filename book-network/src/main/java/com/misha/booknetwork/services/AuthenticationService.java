package com.misha.booknetwork.services;

import com.misha.booknetwork.dto.RegistrationRequest;
import com.misha.booknetwork.repository.RoleRepository;
import com.misha.booknetwork.repository.UserRepository;
import com.misha.booknetwork.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


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
        return
    }

    private String generateActivationCode(int i) {
    }
}
