package com.csn.csn.search.repository;

import com.csn.csn.search.entity.Search;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SearchRepositoryImpl implements SearchRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Search search) {
        em.persist(search);
        return search.getId();
    }

    @Override
    public Search find(Long searchId) {
        return em.find(Search.class, searchId);
    }

    @Override
    public List<Search> findAll() {
        String jpql = "select s from Search s";
        return em.createQuery(jpql, Search.class)
                .getResultList();
    }

    @Override
    public List<Search> findByQuery(String query) {
        String jpql = "select s from Search s where s.query = :query";
        return em.createQuery(jpql, Search.class)
                .setParameter("query", query)
                .getResultList();
    }

    @Override
    public List<Search> findByBuildTime(LocalDateTime buildDateTime) {
        String jpql = "select s from Search s where s.buildDateTime = :buildDateTime";
        return em.createQuery(jpql, Search.class)
                .setParameter("buildDateTime", buildDateTime)
                .getResultList();
    }

    @Override
    public void delete(Search search) {
        em.remove(search);
    }
}
