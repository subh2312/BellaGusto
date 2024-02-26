package org.subhankar.authservice.integration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.subhankar.authservice.model.DO.Role;
import org.subhankar.authservice.model.DTO.CreateUserDTO;
import org.subhankar.authservice.model.DTO.FetchUserDTO;
import org.subhankar.authservice.model.DO.User;
import org.subhankar.authservice.model.DTO.Result;

@FeignClient(name = "USER-SERVICE")
public interface UserIntegration {

    @PostMapping("/user/email")
    User getUserByEmail(@RequestBody FetchUserDTO fetchUserDTO);
    @GetMapping("/user/:email/role")
    String getRoleByEmail(@RequestBody FetchUserDTO fetchUserDTO);
    @PostMapping("/user/new")
    Result addUser(CreateUserDTO user);
    @GetMapping("/role/name/{name}")
    Role getRoleByName(String name);
}
