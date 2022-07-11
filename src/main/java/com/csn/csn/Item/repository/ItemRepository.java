package com.csn.csn.Item.repository;

import com.csn.csn.Item.entity.Item;

import java.util.List;

public interface ItemRepository {

    Long save(Item item);

    Item find(Long itemId);

    List<Item> findAllSpecificType(String type);

    List<Item> findAll();

    void delete(Item item);
}
