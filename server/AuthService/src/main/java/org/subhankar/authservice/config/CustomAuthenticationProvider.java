package org.subhankar.authservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.subhankar.authservice.integration.UserIntegration;
import org.subhankar.authservice.model.DO.Role;
import org.subhankar.authservice.model.DO.User;
import org.subhankar.authservice.model.DTO.FetchUserDTO;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserIntegration userIntegration;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userIntegration.getUserByEmail(FetchUserDTO.builder().email(email).build());
        if (user == null) {
            return null;
        }
        String salt = user.getSalt();
        String pepper = "F00|)--|)3L!V3RY";
        String hashedPass = BCrypt.hashpw(password + pepper, salt);
        if (!hashedPass.equals(user.getPassword())) {
            System.out.println("Password is incorrect");
            System.out.println("Password :"+ user.getPassword());
            System.out.println("Hashed Password :"+ hashedPass);
            return null;
        }
        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());
        return new UsernamePasswordAuthenticationToken(email, hashedPass, authorities);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
