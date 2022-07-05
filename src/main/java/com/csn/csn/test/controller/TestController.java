package com.csn.csn.test.controller;

import com.csn.csn.test.entity.Members;
import com.csn.csn.test.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @RequestMapping("/test")
    public String test(Model model) {
        log.info("test Controller start");
        List<Members> result = testService.testSelect();

        for (Members members : result) {
            log.info("member = " + members.toString());
        }

        log.info("한글깨짐 테스트");
        return "test";
    }
}
