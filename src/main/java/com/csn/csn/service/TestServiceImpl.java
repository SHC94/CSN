package com.csn.csn.service;

import com.csn.csn.entity.Members;
import com.csn.csn.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public Members testSelect(Long memberId) {
        log.info("testServiceImpl start");
        return testRepository.testSelect(memberId);
    }
}
