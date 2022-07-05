package com.csn.csn.main.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 탭 메뉴 Entity
 */
@Entity
@Data
public class Tab {

    @Id @GeneratedValue
    private String tabNo;   /**탭 메뉴 번호*/
    private String tabCd;   /**탭 메뉴 코드*/
    private String tabNm;   /**탭 메뉴 명*/

}//end class()
