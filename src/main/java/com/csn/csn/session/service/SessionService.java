package com.csn.csn.session.service;

import com.csn.csn.session.vo.SessionRequestVo;

import javax.servlet.http.HttpSession;

public interface SessionService {
    void connectionSession(HttpSession session, SessionRequestVo loginRequestVo);

    void disConnectionSession(HttpSession session);

}
