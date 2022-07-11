package com.csn.csn.Item.repository;

import com.csn.csn.Item.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Long save(Item item);

    Optional<Item> find(Long itemId);

    List<Item> findAllSpecificType(String type);

    List<Item> findAll();

    void delete(Item item);
}
