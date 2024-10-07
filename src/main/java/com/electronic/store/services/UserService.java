package com.electronic.store.services;

import com.electronic.store.dto.UserDto;
import com.electronic.store.entities.User;

import java.util.List;

public interface UserService {
    UserDto create(UserDto user);

    //update
    UserDto updateUser(UserDto userDto, int id);

    //delete
    void deleteUser(int id);

    //getAllUser
    List<UserDto> getAllUser(int pageNumber,int pageSize);

    //getSingleUser by id
    UserDto getUserById(int id);

    //getSingleUser by email;
    UserDto getUserByEmail(String email);

    //search
    List<UserDto> search(String keyword);
}
