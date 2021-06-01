package com.gmail.hvorostenko.service.impl;

import com.gmail.hvorostenko.repository.ItemRepository;
import com.gmail.hvorostenko.repository.model.Item;
import com.gmail.hvorostenko.service.ItemService;
import com.gmail.hvorostenko.service.PageService;
import com.gmail.hvorostenko.service.converter.ItemConvertor;
import com.gmail.hvorostenko.service.model.ItemDTO;
import com.gmail.hvorostenko.service.model.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PageService<Item> pageService;
    private final ItemRepository itemRepository;
    private final ItemConvertor itemConvertor;

    @Override
    @Transactional
    public PageDTO<ItemDTO> findAllSortName(Integer pageCurrent) {
        PageDTO<ItemDTO> pageDTO = new PageDTO();
        List<Item> items = itemRepository.findAll(pageCurrent);
        List<ItemDTO> itemDTO = itemConvertor.convert(items);
        pageDTO.getEntityList().addAll(itemDTO);
        List<Integer> pageNumbers = pageService.countPage(new Item());
        pageDTO.getPageNumbers().addAll(pageNumbers);
        return pageDTO;
    }

    @Override
    @Transactional
    public ItemDTO findById(String id) {
        Item item = itemRepository.findById(Long.parseLong(id));
        return itemConvertor.convert(item);
    }

    @Override
    @Transactional
    public int deleteItems(List<String> idItems) {
        int resultDelete = itemRepository.delete(idItems);
        return resultDelete;
    }

    @Override
    @Transactional
    public void addCopy(List<String> idItems) {
        for (String id : idItems) {
            Item item = itemRepository.findById(Long.parseLong(id));
            Item itemCopy = new Item();
            itemCopy.setName("Copy " +item.getName());
            itemCopy.setSummary(item.getSummary());
            itemCopy.setPrice(item.getPrice());
            itemCopy.setUuid(UUID.randomUUID().toString());
            itemRepository.persist(itemCopy);
        }
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepository.findAll();
        return itemConvertor.convert(items);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id);
        itemRepository.remove(item);
    }

    @Override
    @Transactional
    public void add(ItemDTO itemDTO) {
        Item item = itemConvertor.convert(itemDTO);
        itemRepository.persist(item);
    }
}
