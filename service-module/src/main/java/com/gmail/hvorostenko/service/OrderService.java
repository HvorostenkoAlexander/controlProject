package com.gmail.hvorostenko.service;

import com.gmail.hvorostenko.service.model.OrderDTO;
import com.gmail.hvorostenko.service.model.PageDTO;

public interface OrderService {
    PageDTO<OrderDTO> findAllSortDate(Integer pageCurrent);
    OrderDTO findById(String id);
    void updateStatus(String status, String idOrder);
    void add(String count, String idItem,String nameUser);
    PageDTO<OrderDTO> findAllUserSortDate(Integer pageCurrent, String nameUser);
}
