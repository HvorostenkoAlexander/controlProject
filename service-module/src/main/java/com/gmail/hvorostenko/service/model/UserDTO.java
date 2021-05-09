package com.gmail.hvorostenko.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
public class UserDTO {
    private Long id;
    @NotNull
    @Size(min=2, max=20)
    private String name;
    @NotNull
    @Size(min=2, max=40)
    private String surname;
    @NotNull
    @Size(min=2, max=20)
    private String patronymic;
    @NotNull
    @Email
    @Size(min=2, max=50)
    private String email;
    @NotNull
    @Size(min=2, max=20)
    private String telephone;
    @NotNull
    @Size(min=2, max=50)
    private String address;
    private String role;

}
