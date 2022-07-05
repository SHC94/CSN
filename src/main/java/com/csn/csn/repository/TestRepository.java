package com.csn.csn.repository;

import com.csn.csn.entity.Members;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TestRepository {

    private final EntityManager em;

    public Members testSelect(Long id) {
        log.info("test repository start");
        return em.find(Members.class, id);
    }

}
