package com.csn.csn.test.service;

import com.csn.csn.test.entity.Members;
import com.csn.csn.test.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    public List<Members> testSelect() {
        log.info("testServiceImpl start");
        return testRepository.testSelect();
    }
}
