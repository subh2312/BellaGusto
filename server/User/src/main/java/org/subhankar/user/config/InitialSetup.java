package org.subhankar.user.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.subhankar.user.service.RoleService;

@Component
@RequiredArgsConstructor
public class InitialSetup implements CommandLineRunner {

    private final RoleService roleService;
    @Override
    public void run(String... args) throws Exception {
//        roleService.createDefaultRoles();
    }
}
