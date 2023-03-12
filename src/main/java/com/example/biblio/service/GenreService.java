package com.example.biblio.service;

import com.example.biblio.entity.Author;
import com.example.biblio.entity.Genre;
import com.example.biblio.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public void createGenre(Genre genre){
        genreRepository.save(genre);
    }

    public Genre getById(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            throw  new IllegalStateException("genre with id " + id + " does not exist");
        }
        return genre.get();
    }
}
