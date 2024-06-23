package com.misha.booknetwork.services;

import com.misha.booknetwork.dto.RegistrationRequest;
import com.misha.booknetwork.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final RoleRepository roleRepository;
    // 1 sign role to user
    //2 Create User object
    //3 Email validation
    //4 Send message through java mail
    public void register(RegistrationRequest request) {

        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("ROLE USER wasnt initialized"));





    }
}
