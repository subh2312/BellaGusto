package org.subhankar.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.service.RoleService;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping()
    public Result getAllRoles() {
        return roleService.getAllRoles();
    }
}
