package com.electronic.store.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    //upload file
     String uploadFile(MultipartFile file, String path) throws IOException;
    //get file
    InputStream getFile(String path, String name) throws FileNotFoundException;
}
