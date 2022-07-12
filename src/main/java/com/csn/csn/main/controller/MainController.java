package com.csn.csn.main.controller;

import com.csn.csn.Item.entity.NewsItem;
import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.service.MainService;
import com.csn.csn.main.vo.LoginForm;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.entity.Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
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
        log.info("MainController mainHome start ===================");
        List<Tab> tabList = mainService.selectTab(); 
        List<NewsItem> newsList = mainService.selectNewsList();

        model.addAttribute("tabList"    , tabList);             //탭 메뉴 조회
        model.addAttribute("newsList"   , newsList);           //뉴스 스탠드 조회
        model.addAttribute("loginForm"  , new LoginForm());   //로그인 정보

        return "home";
    }//end mainHome()

    /**
     * 로그인
     * @param loginForm
     * @param result
     * @return
     */
    @RequestMapping(value="/loginMember", method= RequestMethod.POST)
    @ResponseBody
    public String login(@Valid LoginForm loginForm, BindingResult result, HttpSession session) {
        log.info("MainController login start ======================");
        log.info(loginForm.toString());

        //가입여부 조회
        boolean membershipResult = mainService.membershipFind(loginForm, session);
        String resultMsg = "";

        if(membershipResult) resultMsg = "<script>alert('로그인 성공');location.href='/'</script>";
        else resultMsg = "<script>alert('가입되지 않은 회원입니다.');location.href='/'</script>";

        return resultMsg;
    }//end login()

    /**
     * 최근 검색어 조회
     * @param searchParam
     * @param model
     * @return
     */
    //responseBody 자바 객체를 HTTP 응답 몸체로 전송한다. + 자바 객체를 HTTP 요청의 BODY로 매핑
    @PostMapping("/selectSearchList")
    public @ResponseBody List<Search> selectSearchList(@ModelAttribute SearchParam searchParam, Model model) {
        log.info("MainController selectSearchList start");
        log.info("searchParam = " + searchParam.toString());

        ModelAndView mv = new ModelAndView();

        List<Search> searchList = mainService.selectSearchList(searchParam);

        for (Search search : searchList) {
            log.info("============================================");
            log.info("{} " + search.getId());
        }//end for()

        return searchList;
    }//end selectSearchList()

}//end class()
