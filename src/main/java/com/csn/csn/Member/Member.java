package com.csn.csn.Member;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String login_id;

    private String password;
    private String name;
    private String birthday;
    private String email;
    private String phone;


}
