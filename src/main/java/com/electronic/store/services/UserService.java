package com.electronic.store.services;

import com.electronic.store.dto.PageableResponse;
import com.electronic.store.dto.UserDto;
import com.electronic.store.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    UserDto create(UserDto user);

    //update
    UserDto updateUser(UserDto userDto, int id);

    //delete
    void deleteUser(int id) throws IOException;

    //getAllUser
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //getSingleUser by id
    UserDto getUserById(int id);

    //getSingleUser by email;
    UserDto getUserByEmail(String email);

    //search
    List<UserDto> search(String keyword);
}
