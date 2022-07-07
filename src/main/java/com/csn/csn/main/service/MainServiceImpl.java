package com.csn.csn.main.service;

import com.csn.csn.main.entity.Tab;
import com.csn.csn.main.repository.MainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MainServiceImpl implements MainService {

    private final MainRepository mainRepository;

    /**
     * 탭 메뉴 조회
     * @return
     */
    @Override
    public List<Tab> selectTab() {
        return mainRepository.selectTab();
    }//end selectTab()

    @Override
    public void batchTest() {
        log.info("batchTest start ================================");
    }//end batchTest()


}//end class()
