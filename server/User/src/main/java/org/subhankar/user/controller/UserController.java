package org.subhankar.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.subhankar.user.config.JwtUtil;
import org.subhankar.user.model.DO.Address;
import org.subhankar.user.model.DO.User;
import org.subhankar.user.model.DTO.FetchUserDTO;
import org.subhankar.user.model.DTO.Result;
import org.subhankar.user.model.DTO.SearchUserDTO;
import org.subhankar.user.model.DTO.CreateUserRequestDTO;
import org.subhankar.user.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtProvider;

    //add user
    @PostMapping("/new")
    public Result addUser(@RequestBody CreateUserRequestDTO userDTO){
        return userService.createUser(userDTO);
    }

    //get user
    @GetMapping("/id")
    public Result getUser(HttpServletRequest request){
        return userService.getUser(request);
    }

    //get all users
    @GetMapping()
    public Result getAllUser(@RequestParam(required = false,defaultValue = "0") int page,
                             @RequestParam(required = false,defaultValue = "10") int size,
                             @RequestParam(required = false,defaultValue = "name") String sortBy,
                             @RequestParam(required = false,defaultValue = "asc") String sortOrder){
        return userService.getAllUser(page, size, sortBy, sortOrder);
    }

    //update user
    @PutMapping()
    public Result updateUser(@RequestBody CreateUserRequestDTO userDTO, HttpServletRequest request){

        return userService.updateUser(userDTO,request);
    }

    //delete user
    @DeleteMapping()
    public Result deleteUser(HttpServletRequest request, HttpServletResponse response){
        return userService.deleteUser(request,response);
    }

    //search user
    @GetMapping("/search")
    public Result searchUser(@RequestBody SearchUserDTO searchUserDTO){
        return userService.searchUser(searchUserDTO);
    }

    @PostMapping("/email")
    public User getUserByEmail(@RequestBody FetchUserDTO fetchUserDTO){
        return userService.getUserByEmail(fetchUserDTO.getEmail());
    }
}
