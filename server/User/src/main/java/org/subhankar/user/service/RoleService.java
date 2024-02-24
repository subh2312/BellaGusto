package org.subhankar.user.service;

import org.subhankar.user.model.DO.Role;
import org.subhankar.user.model.DTO.Result;

public interface RoleService {
//    Result createNewRole(Role role);
//    Result updateRole(Role role);
//    Result deleteRole(String id);
    Result getAllRoles();
    Result getRoleById(String id);

    void createDefaultRoles();
}
