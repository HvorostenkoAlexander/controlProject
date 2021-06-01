package com.gmail.hvorostenko.service.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {
    private Long id;
    private String status;
    private String nameItem;
    private String nameUser;
    private String telephoneUser;
    private String count;
    private BigDecimal price;
}
