package com.csn.csn.Item.repository;

import com.csn.csn.Item.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Item item) {
        em.persist(item);
        return item.getId();
    }

    @Override
    public Optional<Item> find(Long itemId) {
        Item item = em.find(Item.class, itemId);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAllSpecificType(String type) {
        String query = "select i from Item i where type(i) in (" + type + ")";
        return em.createQuery(query, Item.class)
                .getResultList();
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    @Override
    public void delete(Item item) {
        em.remove(item);
    }
}
