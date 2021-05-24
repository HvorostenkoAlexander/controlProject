package com.gmail.hvorostenko.web.controller;


import com.gmail.hvorostenko.service.ItemService;
import com.gmail.hvorostenko.service.model.ItemDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {


    private final ItemService itemService;

    @GetMapping
    public String getItemsPage(Model model, @RequestParam(name = "page", defaultValue = "1") Integer pageCurrent) {
        PageDTO<ItemDTO> pageDTO = itemService.findAllSortName(pageCurrent);
        model.addAttribute("page", pageDTO);
        return "items";
    }

    @GetMapping("/id")
    public String getItemPage(@RequestParam("id") String id, Model model) {
        ItemDTO item = itemService.findById(id);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping("/delete")
    public RedirectView deleteItem(@RequestParam("idCheckedDelete") List<String> idItems) {
        if (!idItems.isEmpty()) {
            itemService.deleteItems(idItems);
        }
        return new RedirectView("");
    }

    @GetMapping("/add-copy")
    public RedirectView addCopyArticle(@RequestParam("idCheckedCopy") List<String> idItems) {
        if (!idItems.isEmpty()) {
            itemService.addCopy(idItems);
        }
        return new RedirectView("");
    }
}
