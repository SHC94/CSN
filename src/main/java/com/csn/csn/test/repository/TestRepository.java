package com.csn.csn.test.repository;

import com.csn.csn.test.entity.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TestRepository {

    private final EntityManager em;

    public List<Members> testSelect() {
        log.info("test repository start");
        return em.createQuery("select m from Members m", Members.class).getResultList();
    }

}
