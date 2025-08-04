package com.projetospring.chatonline.core.security;

import com.projetospring.chatonline.core.exceptions.AuthenticationValidationException;
import com.projetospring.chatonline.infrastructure.security.UserDetailsImpl;
import com.projetospring.chatonline.modules.user.domain.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserAuthenticationService {

    public UserEntity getUserFromPrincipal(Principal principal) {
        if (principal == null) {
            throw new AuthenticationValidationException("User don't authentication");
        }

        if (principal instanceof UsernamePasswordAuthenticationToken authToken) {
            Object principalObj = authToken.getPrincipal();
            if (principalObj instanceof UserDetailsImpl userDetails) {
                return userDetails.getUser();
            }
        }

        throw new AuthenticationValidationException("User don't authentication");

    }


}
