package com.misha.booknetwork.files;

import com.misha.booknetwork.book.Book;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.file.uploads.photos-output-path}")
    private String  fileUploadPath;

    public String saveFile(@NonNull MultipartFile sourceFile,
                           @NonNull Book book,
                           @NonNull Integer id) {
        final String fileUploadSubPath = "user" + separator + id;

        return uploadFile(sourceFile, fileUploadPath);
    }

    private String uploadFile(@NonNull MultipartFile sourceFile,
                              @NonNull String fileUploadPath) {

        final String finalUploadFile = fileUploadPath + separator + fileUploadPath;
        File targetFolder = new File(fileUploadPath);
        if(!targetFolder.exists()){
            boolean folderCreated = targetFolder.mkdirs();
            if(!folderCreated){
                            log.warn("Fail to create the target folder");
                            return null;
            }
        }
        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename());

        String targetFilePath = finalUploadFile + separator + currentTimeMillis() + "." + fileExtension;
        Path targetPath = Paths.get(targetFilePath);

        try{
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File saved to " + targetFilePath);
            return targetFilePath;

        }catch (IOException e){
            log.error("Files wasn't saved", e);
        }
        return null;
    }


    private String getFileExtension(String filename) {
        if(filename == null || filename.isEmpty()){
            return  "";
        }

        int lastDotIndex = filename.lastIndexOf(".");
        if(lastDotIndex == -1){
            return "";
        }
        return filename.substring(lastDotIndex + 1).toLowerCase();
    }
}
