package com.example.biblio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "book_genres",
            joinColumns = {
                    @JoinColumn(name = "genre_id" , referencedColumnName = "id" , nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "book_id" , referencedColumnName = "id",nullable = false)
            }
    )
    private Set<Book> books = new HashSet<>();


}
