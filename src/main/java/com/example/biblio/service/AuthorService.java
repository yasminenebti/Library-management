package com.example.biblio.service;

import com.example.biblio.entity.Author;
import com.example.biblio.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public void createAuthor(Author author){

        authorRepository.save(author);
    }

    public Author getById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            throw  new IllegalStateException("author with id " + id + " does not exist");
        }
        return author.get();
    }
}
