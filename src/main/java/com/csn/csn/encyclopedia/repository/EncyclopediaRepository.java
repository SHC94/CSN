package com.csn.csn.encyclopedia.repository;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.Item.entity.Item;
import com.csn.csn.encyclopedia.vo.PopularSearch;
import com.csn.csn.main.vo.SearchParam;
import com.csn.csn.search.entity.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class EncyclopediaRepository {

    private final EntityManager em;

    /**
     * 백과사전 데이터 조회
     * @return
     */
    public List<DictionaryItem> selectDictionaryList() {
        return em.createQuery("select d from DictionaryItem d where 1=1 order by last_build_date desc", DictionaryItem.class)
                .setFirstResult(1)
                .setMaxResults(5)
                .getResultList();
    }//end selectDictionaryList()

    /**
     * 아이템 중복 체크
     * @param dictionaryItem
     * @return
     */
    public Optional<Item> distinctItem(DictionaryItem dictionaryItem) {
        List<Item> distinctItem = em.createQuery("select i from Item i where 1=1 and link =:link", Item.class)
                .setParameter("link", dictionaryItem.getLink())
                .getResultList();

        return Optional.ofNullable(distinctItem.size() > 0 ? distinctItem.get(0) : null);
    }//end distinctItem()


    /**
     * 백과사전 ITEM 저장
     * @param item
     */
    public void saveDictionaryItem(Item item) {
        em.persist(item);
    }//end saveDictionaryItem()

    /**
     * 백과사전 데이터 조회 (조건)
     * @param searchParam
     * @return
     */
    public List<DictionaryItem> selectSearchDict(SearchParam searchParam) {
        return em.createQuery("select d from DictionaryItem d where 1=1 and (title like :query or description like :query) order by last_build_date desc", DictionaryItem.class)
                .setParameter("query", "%" + searchParam.getQuery() + "%")
                .setFirstResult(0)
                .setMaxResults(5)
                .getResultList();
    }//end selectSearchDict()

    /**
     * 인기 검색어
     * @return
     */
    public List<PopularSearch> popularSearch() {
        List<PopularSearch> resultList = new ArrayList<>();

        List<PopularSearch> list = em.createQuery("select s.query as query, count(*) as cnt from Search s where 1=1 group by query order by count(*) desc")
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        for(int a = 0 ; a < list.size() ; a++) {
            Object o = list.get(a);
            Object[] result = (Object[]) o;
            PopularSearch popularSearch = new PopularSearch();
            popularSearch.setQuery(String.valueOf(result[0]));
            popularSearch.setRank((a + 1));
            resultList.add(popularSearch);
        }//end for()

        return resultList;
    }//end popularSearch()
}//end class()
