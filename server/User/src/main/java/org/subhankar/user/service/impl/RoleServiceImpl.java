package org.subhankar.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.subhankar.user.model.DO.Role;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.repository.RoleRepository;
import org.subhankar.user.service.RoleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

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
}
