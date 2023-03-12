package com.example.biblio.controller;

import com.example.biblio.dto.BookRequest;
import com.example.biblio.entity.Book;
import com.example.biblio.service.BookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="api/book")
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(Slf4j.class);

    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @PostMapping
    public ResponseEntity<Object> crateBook(@Valid @RequestParam BookRequest book , @RequestPart(value = "images", required = false) List<MultipartFile> images) throws InterruptedException, IOException {
        logger.info("Method crating book calling");
        Book newBook = bookService.addBook(book , images);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);


    }


}
