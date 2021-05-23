package com.gmail.hvorostenko.service.model;

import com.gmail.hvorostenko.repository.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ArticleDTO {
    private Long id;
    private String date;
    private String name;
    private String summary;
    private String content;
    private String nameAuthor;
    private String surnameAuthor;
    private Set<Comment> comments = new HashSet<>();
}
