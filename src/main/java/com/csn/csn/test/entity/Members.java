package com.csn.csn.test.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@ToString
public class Members {

    @Id @GeneratedValue
    public Long seq;

    private String mbId;
    private String mbPw;
    private String address;
    private String mbTell;

}
