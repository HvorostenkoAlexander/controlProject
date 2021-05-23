package com.gmail.hvorostenko.service.converter;

import com.gmail.hvorostenko.repository.model.Item;
import com.gmail.hvorostenko.service.model.ItemDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemConvertor {
    public Item convert(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setUuid(itemDTO.getUuid());
        item.setName(itemDTO.getName());
        item.setSummary(itemDTO.getSummary());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    public List<ItemDTO> convert(List<Item> items) {
        return items.stream().map(item -> {
            return convert(item);
        }).collect(Collectors.toList());
    }

    public ItemDTO convert(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setUuid(item.getUuid().toString());
        itemDTO.setName(item.getName());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setSummary(item.getSummary());
        return itemDTO;
    }

}
