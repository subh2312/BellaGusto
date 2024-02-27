package org.subhankar.user.service.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.subhankar.user.config.JwtUtil;
import org.subhankar.user.exception.ResourceNotFoundException;
import org.subhankar.user.integration.AddressIntegration;
import org.subhankar.user.model.DO.Address;
import org.subhankar.user.model.DO.Role;
import org.subhankar.user.model.DO.User;
import org.subhankar.user.model.DTO.CreateUserResponseDTO;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.CreateUserRequestDTO;
import org.subhankar.user.model.DTO.SearchUserDTO;
import org.subhankar.user.repository.RoleRepository;
import org.subhankar.user.repository.UserRepository;
import org.subhankar.user.service.UserService;

import java.util.*;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final JwtUtil jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressIntegration addressIntegration;

    @Override
    public Result createUser(CreateUserRequestDTO userDTO) {
        Role role = roleRepository.findByName(userDTO.getRole()).orElseThrow(() -> new ResourceNotFoundException("Role","name", userDTO.getRole()));

        User user = userRepository.save(User
                .builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .phone(userDTO.getPhone())
                .gender(userDTO.getGender())
                .dob(userDTO.getDob())
                .roles(Set.of(role))
                .build());
        roleRepository.save(role);
        CreateUserResponseDTO createResponseDTO = CreateUserResponseDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .dob(userDTO.getDob())
                .build();
        return Result
                .builder()
                .code("FDAS-0001")
                .message("User created successfully")
                .data(createResponseDTO)
                .build();
    }

    @Override
    public Result updateUser(CreateUserRequestDTO userDTO,HttpServletRequest request) {
        String id=jwtUtils.getIdFromToken(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found")));

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User","id", id);
        }
        User existingUser = userOptional.get();
        updatePropertyIfNotEmpty(userDTO.getName(), existingUser::setName);
        updatePropertyIfNotEmpty(userDTO.getEmail(), existingUser::setEmail);
        updatePropertyIfNotEmpty(userDTO.getGender(), existingUser::setGender);
        updatePropertyIfNotEmpty(userDTO.getPhone(), existingUser::setPhone);
        updatePropertyIfNotEmpty(userDTO.getDob(), existingUser::setDob);
        userRepository.save(existingUser);
        return Result
                .builder()
                .code("FDAS-0002")
                .message("User updated successfully")
                .data(existingUser)
                .build();
    }

    @Override
    public Result deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String id=jwtUtils.getIdFromToken(Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found")));

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User","id", id);
        }
        userRepository.deleteById(id);
        response.setHeader(HttpHeaders.SET_COOKIE, "token=; Max-Age=0; Path=/; HttpOnly; SameSite=None; Secure");
        return Result.builder()
                .code("FDAS-003")
                .message("User deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public Result getUser(HttpServletRequest request) {
        String token = Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("token")).map(Cookie::getValue).findFirst().orElseThrow(() -> new RuntimeException("Token not found"));
        String id=jwtUtils.getIdFromToken(token);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User","id", id);
        }
        List<Address> addresses = addressIntegration.getAddressByIdentifier(id);
        User user = userOptional.get();
        if (addresses.isEmpty()) {
            user.setAddressList(new ArrayList<>());
        }else{
            List<String> addressIds = new ArrayList<>();
            for(Address address: addresses){
                addressIds.add(address.getId());
            }
            user.setAddressList(addressIds);


        }

        return Result.builder()
                .code("FDAS-0004")
                .message("User found successfully")
                .data(user)
                .build();
    }

    @Override
    public Result searchUser(SearchUserDTO searchUserDTO) {
        List<User> users = userRepository.searchUser(searchUserDTO.getName(),
                searchUserDTO.getEmail(),
                searchUserDTO.getDob(),
                searchUserDTO.getPhone());

        return Result.builder()
                .code("FDAS-0005")
                .message("User found successfully")
                .data(users)
                .build();
    }

    @Override
    public Result getAllUser(int page, int size, String sortBy, String sortOrder) {
        Sort sort = null;
        if (sortBy != null){
            if (sortOrder.equalsIgnoreCase("asc")){
                sort = Sort.by(sortBy).ascending();
            }else {
                sort = Sort.by(sortBy).descending();
            }
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> userPage = userRepository.findAll(pageable);
        Map<String, Object> response = Map.of(
                "data", userPage.getContent(),
                "totalPages", userPage.getTotalPages(),
                "totalElements", userPage.getTotalElements(),
                "currentPage", userPage.getNumber(),
                "pageSize", userPage.getSize(),
                "pageNo", userPage.getNumber()
        );
        return Result.builder()
                .code("FDAS-0006")
                .message("User found successfully")
                .data(response)
                .build();
    }



    @Override
    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()){
            throw new RuntimeException("User not found!!");
        }
        return userOptional.get();
    }

    private static void updatePropertyIfNotEmpty(String newValue, Consumer<String> setter) {
        if (newValue != null && !newValue.isEmpty()) {
            setter.accept(newValue);
        }
    }

}
