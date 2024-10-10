package com.electronic.store.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

   private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {

        String orignalFile = file.getOriginalFilename();
        logger.info("Filename : {}",orignalFile);

        String filename = UUID.randomUUID().toString();
        String ext = orignalFile.substring(filename.lastIndexOf("."));
        String newFileName = filename+ext;
        String fullPath = path+ File.separator+newFileName;
        if(ext.equalsIgnoreCase(".png") || ext.equalsIgnoreCase(".jpg") || ext.equalsIgnoreCase(".jpeg")){
            File folder = new File(path);
            if(!folder.exists()){
                folder.mkdirs();
            }
            Files.copy(file.getInputStream(), Paths.get(fullPath));
        }
        else{
            throw new IOException();
        }

        return fullPath;
    }

    @Override
    public InputStream getFile(String path, String name) throws FileNotFoundException {
        String filePath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(filePath);
        return inputStream;
    }
}
