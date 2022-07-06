package com.csn.csn.main.controller;

import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.service.MainService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    public String mainHome(Model model, HttpSession session) {
        log.info("mainHome start ===================");
        
        //탭 메뉴 조회
        List<Tab> tabList = mainService.selectTab(); 
        model.addAttribute("tabList", tabList);

        return "home";
    }//end mainHome()

}//end class()
