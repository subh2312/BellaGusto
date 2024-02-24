package org.subhankar.authservice.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.subhankar.authservice.integration.UserIntegration;
import org.subhankar.authservice.model.DO.User;
import org.subhankar.authservice.model.DTO.FetchUserDTO;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserIntegration userIntegration;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userIntegration.getUserByEmail(FetchUserDTO.builder().email(username).build());
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return user;
    }
}
