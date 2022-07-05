package com.csn.csn.main.repository;

import com.csn.csn.main.entity.Tab;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
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
}//end class()
