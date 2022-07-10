package com.csn.csn.main.controller;

import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.service.MainService;
import com.csn.csn.main.vo.LoginForm;
import com.csn.csn.session.service.SessionService;
import com.csn.csn.session.vo.SessionRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

        //로그인 정보
        model.addAttribute("loginForm", new LoginForm());


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

}//end class()
