package org.subhankar.user.service;

import jakarta.servlet.http.HttpServletRequest;
import org.subhankar.user.model.DO.Role;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.RoleChangeRequestDTO;

public interface RoleService {
//    Result createNewRole(Role role);
//    Result updateRole(Role role);
//    Result deleteRole(String id);
    Result getAllRoles();
    Result getRoleById(String id);

    void createDefaultRoles();

    Role getRoleByName(String name);

    Result changeRole(RoleChangeRequestDTO roleChangeRequestDTO, HttpServletRequest request);
}
