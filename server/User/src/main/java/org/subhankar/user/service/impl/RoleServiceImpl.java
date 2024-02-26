package org.subhankar.user.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.user.config.JwtUtil;
import org.subhankar.user.model.DO.Role;
import org.subhankar.user.model.DO.User;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.RoleChangeRequestDTO;
import org.subhankar.user.repository.RoleRepository;
import org.subhankar.user.repository.UserRepository;
import org.subhankar.user.service.RoleService;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final JwtUtil jwtProvider;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Result getAllRoles() {
        return Result.builder()
                .code("200")
                .message("Success")
                .data(roleRepository.findAll())
                .build();
    }

    @Override
    public Result getRoleById(String id) {

        return Result
                .builder()
                .code("200")
                .message("Success")
                .data(roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found")))
                .build();
    }

    @Override
    public void createDefaultRoles() {
        Role userRole = new Role();
        userRole.setCode("ROLE_USER");
        userRole.setName("User");
        userRole.setDescription("Default role for all end users");
        roleRepository.save(userRole);
        Role adminRole = new Role();
        adminRole.setCode("ROLE_ADMIN");
        adminRole.setName("Admin");
        adminRole.setDescription("Admin User");
        roleRepository.save(adminRole);
        Role deliveryPartner = new Role();
        deliveryPartner.setCode("ROLE_DELIVERY_PARTNER");
        deliveryPartner.setName("Delivery Partner");
        deliveryPartner.setDescription("Delivery Partner User");
        roleRepository.save(deliveryPartner);
        Role restaurantPartner = new Role();
        restaurantPartner.setCode("RESTAURANT_PARTNER");
        restaurantPartner.setName("Restaurant Owner");
        restaurantPartner.setDescription("Default role for restaurant owners");
        roleRepository.save(restaurantPartner);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public Result changeRole(RoleChangeRequestDTO roleChangeRequestDTO, HttpServletRequest request) {

        String token = request.getCookies()[0].getValue();
        if(!jwtProvider.hasRole(token,"Admin")){
            return Result.builder()
                    .code("401")
                    .message("Unauthorized")
                    .build();
        }
        Role role = roleRepository.findByName(roleChangeRequestDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
        User user = userRepository.findById(roleChangeRequestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return Result.builder()
                .code("200")
                .message("Role changed successfully")
                .data(userRepository.save(user))
                .build();

    }
}
