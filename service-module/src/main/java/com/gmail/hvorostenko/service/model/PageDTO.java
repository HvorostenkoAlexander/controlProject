package com.gmail.hvorostenko.service.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PageDTO <T>{
    private List<T> entityList = new ArrayList<>();
    List<Integer> pageNumbers = new ArrayList<>();
}
