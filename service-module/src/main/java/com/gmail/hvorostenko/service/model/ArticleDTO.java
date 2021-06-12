package com.gmail.hvorostenko.service.model;

import com.gmail.hvorostenko.repository.model.Comment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ArticleDTO {
    private Long id;
    @NotNull(message = "Date should not be empty")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private String date;
    @NotNull
    @Size(min=2, max=100)
    private String name;
    private String summary;
    @NotNull
    @Size(min=2, max=1000)
    private String content;
    private String nameAuthor;
    private String surnameAuthor;
    private Set<Comment> comments = new HashSet<>();
}
