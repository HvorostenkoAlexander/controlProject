package com.gmail.hvorostenko.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ReviewDTO {
    private Long id;
    @NotNull
    @Size(min=2, max=200)
    private String comment;
    private String dateAdded;
    private Boolean statusShow;
    private String nameAuthor;
    private String surnameAuthor;
    private String patronymicAuthor;
}
