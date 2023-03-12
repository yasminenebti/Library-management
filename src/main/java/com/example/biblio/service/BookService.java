package com.example.biblio.service;


import com.example.biblio.dto.BookRequest;
import com.example.biblio.entity.Author;
import com.example.biblio.entity.Book;
import com.example.biblio.entity.FileData;
import com.example.biblio.entity.Genre;
import com.example.biblio.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;


@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    private FileDataService fileDataService;

   @Autowired
   private GenreService genreService;
    @Autowired
    private AuthorService authorService;


    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long Id) {
        Optional<Book> book = bookRepository.findById(Id);
        if(book.isEmpty()) {
            throw  new IllegalStateException("book with id " + Id + " does not exist");
        }
        return book.get();
    }

    public Book addBook(BookRequest bookRequest, List<MultipartFile> images) throws IOException {

        Author author = authorService.getById(bookRequest.getAuthorId());
        System.out.println(author);

        Set<Genre> genres = new HashSet<>();
        if (bookRequest.getGenresId() != null) { // add null check here
            for (Long genreId : bookRequest.getGenresId()) {
                genres.add(genreService.getById(genreId));
            }
        }

        Book book = new Book();
        book.setAuthor(author);
        book.setDescription(bookRequest.getDescription());
        book.setTitle(bookRequest.getTitle());
        book.setGenres(genres);

        /*List<FileData> images = new ArrayList<>();
        if (bookRequest.getImages()!=null) {
            for(MultipartFile image : bookRequest.getImages()) {
                FileData fileData = fileDataService.uploadImage(image) ;
                fileData.setBook(book);
                images.add(fileData);
            }
            book.setImages(images);
        }*/
        if (images != null && !images.isEmpty()) {
            List<FileData> bookImages = new ArrayList<>();
            for(MultipartFile image : images) {
                FileData fileData = fileDataService.uploadImage(image);
                fileData.setBook(book);
                bookImages.add(fileData);
            }
            book.setImages(bookImages);
        }

        System.out.println("hello"+book);
        return bookRepository.save(book);

    }
}
