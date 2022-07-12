package com.csn.csn.encyclopedia.repository;

import com.csn.csn.Item.entity.DictionaryItem;
import com.csn.csn.Item.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Dictionary;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EncyclopediaRepository {

    private final EntityManager em;

    /**
     * 백과사전 데이터 조회
     * @return
     */
    public List<DictionaryItem> selectDictionaryList() {
        return em.createQuery("select d from DictionaryItem d where 1=1 order by last_build_date", DictionaryItem.class)
                .setFirstResult(0)
                .setMaxResults(10)
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


    public void saveDictionaryItem(Item item) {
        em.persist(item);
    }//end saveDictionaryItem()
}//end class()
