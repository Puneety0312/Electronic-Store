package com.electronic.store.controllers;

import com.electronic.store.dto.ApiMessage;
import com.electronic.store.dto.ImageResponse;
import com.electronic.store.dto.PageableResponse;
import com.electronic.store.dto.UserDto;
import com.electronic.store.services.FileService;
import com.electronic.store.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private  String imagePath;

    //private PageableResponse<UserDto> pageableResponse;
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
    public ResponseEntity<ApiMessage> deleteUser(@PathVariable int userId) throws IOException {
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
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value="pageNumber",defaultValue = "0" ,required  = false) int pageNumber,
            @RequestParam(value="pageSize", defaultValue = "1", required =false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = "name" ,required  = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = "asc", required =false) String sortDir
    ){
        PageableResponse<UserDto> listOfUsers = userService.getAllUser(pageNumber,pageSize,sortBy,sortDir);
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
    //upload image
    @PostMapping("/userImage/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam("userImage")MultipartFile image, @PathVariable int userId) throws IOException {
        String imgName = fileService.uploadFile(image,imagePath);
        UserDto user = userService.getUserById(userId);
        user.setImageName(imgName);
        userService.updateUser(user,userId);
        ImageResponse imageResponse= ImageResponse
                .builder()
                .imageName(imgName)
                .message("Successfully Uploaded!!")
                .success(true)
                .status(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
    }

    //serve Image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable int userId, HttpServletResponse response) throws IOException {
        UserDto user = userService.getUserById(userId);
        String imgName = user.getImageName();
        InputStream file = fileService.getFile(imagePath,imgName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(file, response.getOutputStream());
    }
}
