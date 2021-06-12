package com.gmail.hvorostenko.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String date;
    private String content;
    private String nameAuthor;
    private String surnameAuthor;
}
