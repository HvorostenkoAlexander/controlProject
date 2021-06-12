package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.ItemRepository;
import com.gmail.hvorostenko.repository.OrderRepository;
import com.gmail.hvorostenko.repository.UserRepository;
import com.gmail.hvorostenko.repository.model.*;
import com.gmail.hvorostenko.service.OrderService;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.converter.OrderConvertor;
import com.gmail.hvorostenko.service.model.OrderDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final PageService<Order> pageService;
    private final OrderRepository orderRepository;
    private final OrderConvertor orderConvertor;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PageDTO<OrderDTO> findAllSortDate(Integer pageCurrent) {
        PageDTO<OrderDTO> pageDTO = new PageDTO();
        List<Order> orders = orderRepository.findAll(pageCurrent);
        List<OrderDTO> orderDTO = orderConvertor.convert(orders);
        pageDTO.getEntityList().addAll(orderDTO);
        List<Integer> pageNumbers = pageService.countPage(new Order());
        pageDTO.getPageNumbers().addAll(pageNumbers);
        return pageDTO;
    }

    @Override
    @Transactional
    public OrderDTO findById(String id) {
        Order order = orderRepository.findById(Long.parseLong(id));
        return orderConvertor.convert(order);
    }

    @Override
    @Transactional
    public void updateStatus(String status, String idOrder) {
        Order order = orderRepository.findById(Long.parseLong(idOrder));
        order.setStatus(OrderStatusEnum.valueOf(status));
        order.setDate(new Date());
        orderRepository.merge(order);
    }

    @Override
    @Transactional
    public void add(String count, String idItem, String nameUser) {
        Item item = itemRepository.findById(Long.parseLong(idItem));
        Order order = new Order();
        order.setItem(item);
        order.setCount(Integer.parseInt(count));
        Float totalPrice = item.getPrice().floatValue()*Integer.parseInt(count);
        order.setPrice(BigDecimal.valueOf(totalPrice));
        order.setDate(new Date());
        User user = userRepository.getUserByEmail(nameUser);
        order.setUser(user);
        order.setStatus(OrderStatusEnum.NEW);
        orderRepository.persist(order);
    }

    @Override
    public PageDTO<OrderDTO> findAllUserSortDate(Integer pageCurrent, String nameUser) {
        PageDTO<OrderDTO> pageDTO = new PageDTO();
        List<Order> orders = orderRepository.findAll(pageCurrent, nameUser);
        List<OrderDTO> orderDTO = orderConvertor.convert(orders);
        pageDTO.getEntityList().addAll(orderDTO);
        List<Integer> pageNumbers = pageService.countPage(new Order());
        pageDTO.getPageNumbers().addAll(pageNumbers);
        return pageDTO;
    }
}
