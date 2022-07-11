package com.csn.csn.main.repository;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.vo.LoginForm;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MainRepository {

    private final EntityManager em;

    /**
     * 탭 메뉴 조회
     * @return
     */
    public List<Tab> selectTab(){
        return em.createQuery("select t from Tab t where 1=1 order by tab_no", Tab.class)
                .getResultList();
    }//end selectTab()

    /**
     * 가입 회원 조회
     * @param loginForm
     * @return
     */
    public List<Member> membershipFind(LoginForm loginForm) {
        return em.createQuery("select m from Member m where 1=1 and loginId = :loginId and password =:password", Member.class)
                .setParameter("loginId", loginForm.getLoginId())
                .setParameter("password", loginForm.getLoginPw())
                .getResultList();
    }//end membershipFind()

    /**
     * 최근 검색어 조회
     * @param searchParam
     * @return
     */
    public List<Item> selectSearchList(SearchParam searchParam) {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }//end selectSearchList()
}//end class()
