package com.gmail.hvorostenko.service.model;

import liquibase.pro.packaged.L;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProfileDTO {

    private Long id;
    @NotNull
    @Size(min=2, max=20)
    private String name;
    @NotNull
    @Size(min=2, max=40)
    private String surname;
    @NotNull
    @Size(min=2, max=20)
    private String telephone;
    @NotNull
    @Size(min=2, max=50)
    private String address;
}
