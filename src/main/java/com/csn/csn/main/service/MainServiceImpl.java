package com.csn.csn.main.service;

import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.repository.MainRepository;
import com.csn.csn.main.vo.LoginForm;
import com.csn.csn.member.entity.Member;
import com.csn.csn.session.service.SessionService;
import com.csn.csn.session.vo.SessionRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService {

    private final MainRepository mainRepository;
    private final SessionService sessionService;

    /**
     * 탭 메뉴 조회
     * @return
     */
    @Override
    public List<Tab> selectTab() {
        return mainRepository.selectTab();
    }//end selectTab()

    /**
     * 가입 회원 조회
     * @param   loginForm
     * @return  boolean
     */
    @Override
    public boolean membershipFind(LoginForm loginForm, HttpSession session) {
        List<Member> member = mainRepository.membershipFind(loginForm);
        boolean result      = false;

        //세선 정보 저장
        if(!CollectionUtils.isEmpty(member)) {
            SessionRequestVo sessionRequestVo = new SessionRequestVo();
            sessionRequestVo.setId(loginForm.getLoginId());             //아이디
            sessionRequestVo.setName(member.get(0).getName());          //이름
            sessionRequestVo.setEmail(member.get(0).getEmail());        //이메일
            sessionRequestVo.setLoginWay("Normal");                     //로그인 수단

            sessionService.connectionSession(session, sessionRequestVo);
            result = true;
        }//end if()

        return result;
    }//end membershipFind()


}//end class()
