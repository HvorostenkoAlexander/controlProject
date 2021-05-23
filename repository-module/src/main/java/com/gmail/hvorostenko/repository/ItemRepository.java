package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.Item;

import java.util.List;

public interface ItemRepository extends GenericRepository<Long, Item> {
    List<Item> findAll(Integer pageCurrent);

    int delete(List<String> idItems);
}
