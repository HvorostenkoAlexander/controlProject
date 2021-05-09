package com.gmail.hvorostenko.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String date;
    private String content;
    private String nameAuthor;
    private String surnameAuthor;
}
