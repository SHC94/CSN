package com.csn.csn.news.controller;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.Item.repository.ItemRepository;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final ItemRepository itemRepository;
    private final SearchService searchService;

//    @GetMapping("/enter")
//    public String enter(@ModelAttribute SearchDto searchDto) {
//        return "news/news";
//    }

    @ResponseBody
    @PostMapping("/search")
    public List<Item> search(@ModelAttribute SearchParam searchParam) {
        List<Item> newsList = searchService.doSearch(searchParam);
        return newsList;
    }
}
