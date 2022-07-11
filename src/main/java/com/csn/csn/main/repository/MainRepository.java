package com.csn.csn.main.repository;

import com.csn.csn.Item.entity.Item;
import com.csn.csn.Item.entity.NewsItem;
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
import java.util.Optional;

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

    /**
     * 뉴스 정보 중복 체크
     */
    public Optional<Item> distinctItem(NewsItem newsItem) {
        List<Item> distinctItem = em.createQuery("select i from Item i where 1=1 and link =:link", Item.class)
                .setParameter("link", newsItem.getLink())
                .getResultList();

        //null인지 아닌지 확신할 수 없는 객체를 담고 있는 Optional 객체를 생성합니다.
        //Optional.empty()와 Optional.ofNullable(value)를 합쳐놓은 메소드라고 생각하시면 됩니다.
        //null이 넘어올 경우, NPE를 던지지 않고 Optional.empty()와 동일하게 비어 있는 Optional 객체를 얻어옵니다.
        //해당 객체가 null인지 아닌지 자신이 없는 상황에서는 이 메소드를 사용하셔야 합니다.
        return Optional.ofNullable(distinctItem.size() > 0 ? distinctItem.get(0) : null);
    }//end findNewsOne()


    /**
     * 뉴스 정보 조회 (다건)
     */

    /**
     * 뉴스 정보 저장
     * @param item
     */
    public void saveNewsItem(Item item) {
        em.persist(item);
    }//end saveNewsItem()
}//end class()
