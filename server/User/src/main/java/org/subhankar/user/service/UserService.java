package org.subhankar.user.service;

import org.subhankar.user.model.DO.Address;
import org.subhankar.user.model.DO.User;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.SearchUserDTO;
import org.subhankar.user.model.DTO.CreateUserRequestDTO;

public interface UserService {
    Result createUser(CreateUserRequestDTO userDTO);

    Result updateUser(String id, CreateUserRequestDTO userDTO);

    Result deleteUser(String id);
    Result getUser(String id);
    Result searchUser(SearchUserDTO searchUserDTO);

    Result getAllUser(int page, int size, String sortBy, String sortOrder);

    Result addAddress(Address address);

    User getUserByEmail(String email);
}
