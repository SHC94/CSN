package com.csn.csn.encyclopedia.controller;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.encyclopedia.service.EncyclopediaService;
import com.csn.csn.main.vo.SearchParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Dictionary;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/encyclopedia")
public class EncyclopediaController {

    private final EncyclopediaService encyclopediaService;

    /**
     * 백과사전 화면 진입
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/enter")
    public String encyclopediaEnter(Model model, HttpSession session) {
        log.info("EncyclopediaController::encyclopediaEnter start");

        //백과사전 데이터 조회
        List<DictionaryItem> dictionaryList = encyclopediaService.selectDictionaryList();

        model.addAttribute("dictionaryList", dictionaryList);
        return "encyclopedia/encyclopedia";
    }//end encyclopediaEnter()


    /**
     * 백과사전 데이터 조회
     * @return
     */
    @PostMapping("/search")
    public @ResponseBody List<DictionaryItem> selectSearchDict(@ModelAttribute SearchParam searchParam, Model model) throws Exception {
        log.info("EncyclopediaController::selectSearchDict start");
        log.info("searchParam = " + searchParam.toString());

        //백과사전 데이터 조회
        List<DictionaryItem> dictionaryList = encyclopediaService.selectSearchDict(searchParam);

        return dictionaryList;
    }//end selectSearchDict()

}//end class()
