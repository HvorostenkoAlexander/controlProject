package com.gmail.hvorostenko.service;

import java.util.List;

public interface PageService <T>{
     List<Integer> countPage(T entity);
}
