package com.csn.csn.Item.service;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.Item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Long save(Item item) {
        return itemRepository.save(item);
    }

}
