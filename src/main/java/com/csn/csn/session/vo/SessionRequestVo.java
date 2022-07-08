package com.csn.csn.session.vo;

import lombok.Data;

/**
 * 네이버 로그인 프로필 조회 (세션 관리)
 */
@Data
public class SessionRequestVo {

    private String age;             //나이
    private String birthday;        //생일 일월
    private String email;           //이메일
    private String gender;          //성별
    private String id;              //아이디
    private String name;            //이름
    private String nickname;        //별명
    private String profile_image;   //프로필 사진
    private String loginWay;        //로그인 수단 구분

}//end class()
