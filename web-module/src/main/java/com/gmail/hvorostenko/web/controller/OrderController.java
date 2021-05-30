package com.gmail.hvorostenko.web.controller;

import com.gmail.hvorostenko.service.OrderService;
import com.gmail.hvorostenko.service.model.OrderDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrdersPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        PageDTO<OrderDTO> orderDTO = orderService.findAllSortDate(pageCurrent);
        model.addAttribute("page", orderDTO);
        return "orders";
    }

    @GetMapping("/user")
    public String getOrdersUserPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent,
                                    Principal principal) {
        String nameUser = principal.getName();
        PageDTO<OrderDTO> orderDTO = orderService.findAllUserSortDate(pageCurrent, nameUser);
        model.addAttribute("page", orderDTO);
        return "orders";
    }

    @GetMapping("/id")
    public String getOrderPage(@RequestParam("id") String id, Model model) {
        OrderDTO order = orderService.findById(id);
        model.addAttribute("order", order);
        return "order";
    }

    @PostMapping("/update")
    public RedirectView updateStatus(@RequestParam("status") String status,
                                     @RequestParam("idOrder") String idOrder,
                                     final RedirectAttributes redirectAttributes) {
        if (!StringUtils.isBlank(status) && !StringUtils.isBlank(idOrder)) {
            orderService.updateStatus(status, idOrder);
        }
        return new RedirectView("");
    }

    @PostMapping("/add")
    public String addOrder(@RequestParam("idItem") String idItem,
                           @RequestParam("count") String count,
                           Principal principal) {
        String nameUser = principal.getName();
        orderService.add(count, idItem, nameUser);
        return "redirect:/items";
    }
}
