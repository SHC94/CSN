package com.csn.csn.main.service;

import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.vo.LoginForm;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MainService {

    public List<Tab> selectTab();

    public boolean membershipFind(LoginForm loginForm, HttpSession session);

}//end interface()
