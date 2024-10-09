package com.electronic.store.services;

import com.electronic.store.config.ProjectConfig;
import com.electronic.store.dto.PageableResponse;
import com.electronic.store.dto.UserDto;
import com.electronic.store.entities.User;
import com.electronic.store.repositories.UserRepository;
import jakarta.persistence.Entity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectConfig config;

    @Autowired
    private PageableResponse<UserDto> pageableResponse;

    @Override
    public UserDto create(UserDto userDto) {
        //generate user id
        int userId = config.getUniqueId();
        userDto.setId(userId);
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        UserDto newDto = entityToDto(savedUser);
        return newDto;
    }
    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        user.setPhone(userDto.getPhone());
        //save
        User updatedUser = userRepository.save(user);
        UserDto newUserDto = entityToDto(updatedUser);
        return newUserDto;
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize, sort);
        Page<User> page = userRepository.findAll(pageable);
        List<User> users = page.getContent();
        List<UserDto> userDtos = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        pageableResponse.setContent(userDtos);
        pageableResponse.setPageNumber(pageNumber);
        pageableResponse.setPageSize(pageSize);
        pageableResponse.setTotalElements(page.getTotalElements());
        pageableResponse.setTotalPages(page.getTotalPages());
        pageableResponse.setLastPage(page.isLast());
        return pageableResponse;
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return entityToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> search(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> userDtos = users.stream().map((user) -> entityToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    //class methods
    private UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .id(savedUser.getId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .phone(savedUser.getPhone())
//                .imageName(savedUser.getImageName())
//                .build();
        return mapper.map(savedUser, UserDto.class);
    }

    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .id(userDto.getId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .phone(userDto.getPhone())
//                .imageName(userDto.getImageName())
//                .build();
        return mapper.map(userDto, User.class);
    }
}
