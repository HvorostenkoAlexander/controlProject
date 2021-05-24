package com.gmail.hvorostenko.service.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String uuid;
    private String name;
    private BigDecimal price;
    private String summary;
}
