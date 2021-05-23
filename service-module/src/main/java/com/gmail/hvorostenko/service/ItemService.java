package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.ItemDTO;
import com.gmail.hvorostenko.service.model.PageDTO;

import java.util.List;

public interface ItemService {
    PageDTO<ItemDTO> findAllSortName(Integer pageCurrent);
    ItemDTO findById(String id);
    int deleteItems(List<String> idItems);
    void addCopy(List<String> idItems);
    List<ItemDTO> findAll();
    void delete(Long id);
    void add(ItemDTO itemDTO);
}
