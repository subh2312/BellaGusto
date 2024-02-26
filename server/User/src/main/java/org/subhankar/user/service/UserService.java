package org.subhankar.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.subhankar.user.model.DO.Address;
import org.subhankar.user.model.DO.User;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.SearchUserDTO;
import org.subhankar.user.model.DTO.CreateUserRequestDTO;

public interface UserService {
    Result createUser(CreateUserRequestDTO userDTO);

    Result updateUser(CreateUserRequestDTO userDTO, HttpServletRequest request);

    Result deleteUser(HttpServletRequest request, HttpServletResponse response);
    Result getUser(HttpServletRequest request);
    Result searchUser(SearchUserDTO searchUserDTO);

    Result getAllUser(int page, int size, String sortBy, String sortOrder);

    User getUserByEmail(String email);

}
