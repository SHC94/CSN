package com.csn.csn.session.service;

import com.csn.csn.session.vo.SessionRequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class SessionServiceImpl implements SessionService {

    @Override
    public void connectionSession(HttpSession session, SessionRequestVo sessionRequestVo) {

        // 세션값 설정
        session.setAttribute("user"             , sessionRequestVo);                        //전체 정보
        session.setAttribute("age"              , sessionRequestVo.getAge());               //나이
        session.setAttribute("birthday"         , sessionRequestVo.getBirthday());          //생일 일월
        session.setAttribute("email"            , sessionRequestVo.getEmail());             //이메일
        session.setAttribute("gender"           , sessionRequestVo.getGender());            //성별
        session.setAttribute("id"               , sessionRequestVo.getId());                //아이디
        session.setAttribute("name"             , sessionRequestVo.getName());              //이름
        session.setAttribute("nickname"         , sessionRequestVo.getNickname());          //별명
        session.setAttribute("profile_image"    , sessionRequestVo.getProfile_image());     //프로필 사진
        session.setAttribute("loginWay"         , sessionRequestVo.getLoginWay());          //로그인 수단


        // 세션 유지시간 설정(초단위)
        // 60 * 30 = 30분
        //session.setMaxInactiveInterval(30*60);

        // 세션 시간을 무한대로 설정
        session.setMaxInactiveInterval(-1);

        // 세션에 저장된 값 가져오기
        SessionRequestVo loginVo = (SessionRequestVo) session.getAttribute("user");
        log.info("loginVo age = " + loginVo.getAge());
        log.info("loginVo id = " + loginVo.getId());
        log.info("loginVo name = " + loginVo.getName());

        String id = (String) session.getAttribute("id");
        log.info("id = " + id);



    }//end connectionSession()

    @Override
    public void disConnectionSession(HttpSession session) {
        // 세션값 삭제
        // session.removeAttribute("user");

        // 세션 전체 제거, 무효화
        session.invalidate();
    }//end disConnectionSession()


}//end class()
