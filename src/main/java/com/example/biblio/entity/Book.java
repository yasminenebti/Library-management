package com.example.biblio.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String Description;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "book_author" , referencedColumnName = "id")
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "book_genres",
            joinColumns = {
              @JoinColumn(name = "book_id" , referencedColumnName = "id" , nullable = false)
    },
            inverseJoinColumns = {
            @JoinColumn(name = "genre_id" , referencedColumnName = "id",nullable = false)
            }
            )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<FileData> images;

}
