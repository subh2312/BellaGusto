package org.subhankar.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.subhankar.user.model.DO.Role;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.RoleChangeRequestDTO;
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

    @GetMapping("/name/{name}")
    public Role getRoleByName(@PathVariable String name) {
        return roleService.getRoleByName(name);
    }

    @PutMapping("/change")
    public Result changeRole(@RequestBody RoleChangeRequestDTO role, HttpServletRequest request) {
        return roleService.changeRole(role,request);
    }
}
