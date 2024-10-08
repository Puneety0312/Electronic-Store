package com.electronic.store.controllers;

import com.electronic.store.dto.ApiMessage;
import com.electronic.store.dto.UserDto;
import com.electronic.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //create
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto user = userService.create(userDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int userId, @RequestBody UserDto userDto){
        UserDto userDto1 = userService.updateUser(userDto, userId);
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiMessage> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        ApiMessage message= ApiMessage.builder()
                 .message("User deleted successfully!!")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //get All
    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(value="pageNumber",defaultValue = "0" ,required  = false) int pageNumber,
            @RequestParam(value="pageSize", defaultValue = "0", required =false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = "name" ,required  = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required =false) String sortDir
    ){
        List<UserDto> listOfUsers = userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
    //getSingle
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }
    //getUserByEmail
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String userEmail){
        return new ResponseEntity<>(userService.getUserByEmail(userEmail),HttpStatus.OK);
    }
    //searchUser
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
        return new ResponseEntity<>(userService.search(keywords), HttpStatus.OK);
    }
}
