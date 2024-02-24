package org.subhankar.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    //add user
    @PostMapping
    public Result addUser(@RequestBody CreateUserRequestDTO userDTO){
        return userService.createUser(userDTO);
    }

    //get user
    @GetMapping("/{id}")
    public Result getUser(@PathVariable String id){
        return userService.getUser(id);
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
    @PutMapping("/{id}")
    public Result updateUser(@PathVariable String id, @RequestBody CreateUserRequestDTO userDTO){
        return userService.updateUser(id, userDTO);
    }

    //delete user
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable String id){
        return userService.deleteUser(id);
    }

    //search user
    @GetMapping("/search")
    public Result searchUser(@RequestBody SearchUserDTO searchUserDTO){
        return userService.searchUser(searchUserDTO);
    }

    @PostMapping("/address")
    public Result addAddress(@RequestBody Address address){
        return userService.addAddress(address);
    }

    @PostMapping("/email")
    public User getUserByEmail(@RequestBody FetchUserDTO fetchUserDTO){
        return userService.getUserByEmail(fetchUserDTO.getEmail());
    }
}
