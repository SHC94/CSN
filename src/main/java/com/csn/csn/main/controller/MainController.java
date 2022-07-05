package com.csn.csn.main.controller;

import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final MainService mainService;

    /**
     * 메인 화면 진입
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String mainHome(Model model) {
        log.info("mainHome start ===================");
        
        //탭 메뉴 조회
        List<Tab> tabList = mainService.selectTab(); 
        model.addAttribute("tabList", tabList);
        
        return "home";
    }//end mainHome()
}//end class()
