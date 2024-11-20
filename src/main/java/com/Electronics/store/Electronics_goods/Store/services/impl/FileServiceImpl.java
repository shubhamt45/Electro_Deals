package com.Electronics.store.Electronics_goods.Store.services.impl;




import com.Electronics.store.Electronics_goods.Store.exceptions.BadApiRequestException;
import com.Electronics.store.Electronics_goods.Store.services.FileService;



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

        //abc.png
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename : {}", originalFilename);
        String filename = UUID.randomUUID().toString();
        //UUID => stands for the Universally unique identifier
        // UUID.randomUUID() =>assigns the unique id number to the filename, so that we can uniquely identify thetwo files
        //toString() => it converts the uniquely assign number(id) to String

        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //Above line of the code extracts the extension , basically it returns everything after the dot
        String fileNameWithExtension = filename + extension;  //abc  + png
        String fullPathWithFileName = path  + fileNameWithExtension; // these line tells the system where to store the file

        logger.info("full image path: {} ", fullPathWithFileName);
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {

            //file save
            logger.info("file extension is {} ", extension);
            File folder = new File(path);
            if (!folder.exists()) {
                //If the folder will not exsist then will,create the folder
                folder.mkdirs();

            }

            //upload
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
            return fileNameWithExtension;

        } else {
            throw new BadApiRequestException("File with this " + extension + " not allowed !!");
        }


    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path + File.separator + name;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }


}
