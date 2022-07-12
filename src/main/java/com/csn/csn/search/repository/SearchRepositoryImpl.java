package com.csn.csn.search.repository;

import com.csn.csn.member.entity.Member;
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
    public List<Search> findByMember(Member member, Integer limit) {
        String jpql = "select s from Search s " +
                "where s.member = :member " +
                "order by s.buildDateTime desc";

        return em.createQuery(jpql, Search.class)
                .setParameter("member", member)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Search> findByQuery(String query, Integer limit) {
        String jpql = "select s from Search s " +
                "where s.query = :query " +
                "order by s.buildDateTime desc";

        return em.createQuery(jpql, Search.class)
                .setParameter("query", query)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public List<Search> findByBuildTime(LocalDateTime buildDateTime, Integer limit) {
        String jpql = "select s from Search s " +
                "where s.buildDateTime >= :buildDateTime " +
                "order by s.buildDateTime desc";

        return em.createQuery(jpql, Search.class)
                .setParameter("buildDateTime", buildDateTime)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public void delete(Search search) {
        em.remove(search);
    }
}
