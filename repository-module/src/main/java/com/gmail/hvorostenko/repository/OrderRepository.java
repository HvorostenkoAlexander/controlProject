package com.gmail.hvorostenko.repository;

import com.gmail.hvorostenko.repository.model.Article;
import com.gmail.hvorostenko.repository.model.Order;

import java.util.List;

public interface OrderRepository  extends GenericRepository<Long, Order>{
    List<Order> findAll(Integer pageCurrent);
    List<Order> findAll(Integer pageCurrent, String nameUser);
}
