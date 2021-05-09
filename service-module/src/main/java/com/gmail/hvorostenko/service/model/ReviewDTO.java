package com.gmail.hvorostenko.service.model;

import com.gmail.hvorostenko.repository.model.Review;
import com.gmail.hvorostenko.repository.model.Role;
import com.gmail.hvorostenko.repository.model.RoleEnum;
import com.gmail.hvorostenko.repository.model.User;
import liquibase.pro.packaged.S;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private String comment;
    private String dateAdded;
    private Boolean statusShow;
    private String nameAuthor;
    private String surnameAuthor;
    private String patronymicAuthor;
}
