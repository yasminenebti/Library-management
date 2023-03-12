package com.example.biblio.service;

import com.example.biblio.entity.FileData;
import com.example.biblio.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileDataService {

    private final String Folder = "C:\\Users\\MSI\\Downloads\\biblio\\biblio\\BooksImages";

    private final FileRepository fileRepository;

    public FileDataService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileData uploadImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        String filePath = Folder+ File.separator+fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath));

        FileData newFile = new FileData();
        newFile.setFileName(fileName);
        newFile.setFileType(fileType);
        newFile.setFilePath(filePath);

        return fileRepository.save(newFile);


    }
}
