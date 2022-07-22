package com.csn.csn.auth.service;

import javax.servlet.http.HttpSession;

public interface EmailAuthService {

    public void generateAuthCodeEmail(HttpSession httpSession);

}
