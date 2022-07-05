package com.csn.csn.controller;

import com.csn.csn.entity.Members;
import com.csn.csn.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @RequestMapping("/")
    public String test(Model model) {
        log.info("test Controller start");
        Members member = testService.testSelect(99L);
        log.info("member = " + member.toString());
        log.info("한글깨짐 테스트");
        return "test";
    }
}
