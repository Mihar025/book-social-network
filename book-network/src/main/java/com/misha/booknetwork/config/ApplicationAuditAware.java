package com.misha.booknetwork.config;


import com.misha.booknetwork.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Optional;


public class ApplicationAuditAware implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authenticationn = SecurityContextHolder.getContext().getAuthentication();
        if(authenticationn == null ||
            !authenticationn.isAuthenticated() ||
                    authenticationn instanceof AnonymousAuthenticationToken){
            return Optional.empty();
        }
        User userPrincipal = (User) authenticationn.getPrincipal();
        return Optional.ofNullable(userPrincipal.getId());


    }






}
