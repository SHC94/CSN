package com.csn.csn.session.controller;

import com.csn.csn.session.service.SessionService;
import com.csn.csn.session.vo.SessionRequestVo;
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

    private final SessionService sessionService;

    /**
     * 세션 연결
     * @param model
     * @param session
     * @param request
     * @param response
     * @param loginRequestVo
     */
    @RequestMapping(value = "/connectionSession", method = RequestMethod.POST)
    public void connectionSession(Model model, HttpSession session,
                               HttpServletRequest request, HttpServletResponse response,
                               SessionRequestVo loginRequestVo) {

        sessionService.connectionSession(session, loginRequestVo);

    }//end sessionRequest()

    /**
     * 세션 종료
     * @param model
     * @param session
     * @param request
     * @param response
     */
    @RequestMapping(value = "/disConnectionSession", method = RequestMethod.POST)
    public void disConnectionSession(Model model, HttpSession session,
                                     HttpServletRequest request, HttpServletResponse response) {
        sessionService.disConnectionSession(session);
    }//end disConnectionSession()

}//end class()
