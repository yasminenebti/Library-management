package com.example.biblio.dto;

import com.example.biblio.entity.FileData;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookRequest {
    @NotBlank(message = "Book title not blank")
    @Size(max = 1000)
    private String title;
    @NotBlank(message = "Book description not blank")
    @Size(max = 200)
    private String Description;

    @NotNull(message = "missing authorId")
    private Long authorId;
    @NotEmpty(message = "One genre is required at least")
    private Set<Long> genresId;

    //private List<MultipartFile> images;
}
