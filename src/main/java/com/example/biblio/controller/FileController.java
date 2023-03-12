package com.example.biblio.controller;

import com.example.biblio.dto.BookRequest;
import com.example.biblio.entity.Book;
import com.example.biblio.entity.FileData;
import com.example.biblio.service.FileDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    public final FileDataService fileDataService;

    @Autowired
    public FileController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<FileData> imageUpload(@RequestParam("image")MultipartFile image)  {

        try {
            FileData savedFile = fileDataService.uploadImage(image);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


    }


}
