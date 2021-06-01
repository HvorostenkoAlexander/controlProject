package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.*;
import com.gmail.hvorostenko.service.model.ArticleDTO;
import com.gmail.hvorostenko.service.model.ItemDTO;
import com.gmail.hvorostenko.service.model.OrderDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.hvorostenko.service.constant.ArticleConvertorConst.SUMMARY_BEGIN_INDEX_CONST;
import static com.gmail.hvorostenko.service.constant.ArticleConvertorConst.SUMMARY_END_INDEX_CONST;

@Component
public class OrderConvertor {
    public Order convert(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setDate(new Date());
        order.setStatus(OrderStatusEnum.valueOf(orderDTO.getStatus()));
        order.setCount(Integer.parseInt(orderDTO.getCount()));
        order.setPrice(orderDTO.getPrice());
        order.getItem().setName(orderDTO.getNameItem());
        order.getUser().setName(orderDTO.getNameUser());
        order.getUser().getUserInfo().setTelephone(orderDTO.getTelephoneUser());
        return order;
    }

    public List<OrderDTO> convert(List<Order> orders) {
        return orders.stream().map(order  -> {
            return convert(order);
        }).collect(Collectors.toList());
    }

    public OrderDTO convert(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus().name());
        orderDTO.setCount(order.getCount().toString());
        orderDTO.setCount(order.getCount().toString());
        orderDTO.setPrice(order.getPrice());
        if(order.getUser()!=null){
            orderDTO.setNameUser(order.getUser().getName());
        }
        if(order.getUser().getUserInfo()!=null){
            orderDTO.setTelephoneUser(order.getUser().getUserInfo().getTelephone());
        }
        if(order.getItem()!=null){
            orderDTO.setNameItem(order.getItem().getName());
        }
        return orderDTO;
    }
}
