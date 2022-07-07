package com.csn.csn.login.controller;

import com.csn.csn.login.vo.LoginRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/session")
public class SessionController {

    @RequestMapping(value = "/saveSession", method = RequestMethod.POST)
    public void sessionRequest(Model model, HttpSession session,
                               HttpServletRequest request, HttpServletResponse response,
                               LoginRequestVo loginRequestVo) {

        log.info("sessionRequest Start ==========================");
        log.info("loginRequestVo = " + loginRequestVo.toString());

        // 세션값 설정
        session.setAttribute("user"      , loginRequestVo);

        // 세션 유지시간 설정(초단위)
        // 60 * 30 = 30분
        session.setMaxInactiveInterval(30*60);

        // 세션 시간을 무한대로 설정
        session.setMaxInactiveInterval(-1);

        // 세션에 저장된 값 가져오기
        LoginRequestVo loginVo = (LoginRequestVo) session.getAttribute("user");
        log.info("loginVo age = " + loginVo.getAge());
        log.info("loginVo id = " + loginVo.getId());
        log.info("loginVo name = " + loginVo.getName());

        // 세션값 삭제
        session.removeAttribute("user_id");

        // 세션 전체 제거, 무효화
        session.invalidate();

    }//end sessionRequest()

}//end class()
