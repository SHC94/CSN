package com.csn.csn.member.controller;

import com.csn.csn.member.dto.MemberJoinDto;
import com.csn.csn.member.dto.MemberJoinOrLoginWithNaverDto;
import com.csn.csn.member.dto.MemberLoginDto;
import com.csn.csn.member.entity.Member;
import com.csn.csn.member.repository.MemberRepository;
import com.csn.csn.member.service.MemberServiceImpl;
import com.csn.csn.search.entity.Search;
import com.csn.csn.search.repository.SearchRepository;
import com.csn.csn.search.service.SearchService;
import com.csn.csn.session.vo.SessionRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberService;
    private final MemberRepository memberRepository;
    private final SearchService searchService;

//    @PostMapping("/idCheck")
//    public String hasSameId(@Validated @ModelAttribute MemberJoinDto memberJoinDto, BindingResult bindingResult) {
//        String loginId = memberJoinDto.getLoginId();
//
//        if (memberService.hasSameId(loginId) == true) {
//            bindingResult.reject("idCheckFail", "중복된 아이디가 있습니다. :(");
//        }
//        else {
//            bindingResult.reject("idCheckSuccess", "올바른 아이디 입니다 :)");
//        }
//
//        return "join/joinForm";
//    }


    @GetMapping("/join")
    public String joinForm(Model model) {
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setBirthday(LocalDate.now());
        model.addAttribute("memberJoinDto", memberJoinDto);
        return "join/joinForm";
    }


    @PostMapping("/join")
    public String join(@Validated @ModelAttribute MemberJoinDto memberJoinDto, BindingResult bindingResult) {
        // 요청 파라미터 유효성 체크
        log.info("=============회원 가입 로직입니다=============");
        log.info("{}", bindingResult);
        log.info("=============회원 가입 로직입니다=============");
        if (bindingResult.hasErrors()) return "join/joinForm";

        memberService.join(memberJoinDto);
        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("MemberLoginDto", new MemberLoginDto());
        return "login/loginForm";
    }


//    @PostMapping("/login")
//    public String login(@Validated @ModelAttribute MemberLoginDto memberLoginDto, BindingResult bindingResult) {
//        // 요청 파라미터 유효성 검사
//        if (bindingResult.hasErrors()) return "login/loginForm";
//
//        // 로그인 성공
//        if (memberService.isLoggedIn(memberLoginDto) == true) {
//            return "redirect:/";
//        }
//        // 로그인 실패
//        else {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다. :(");
//            return "login/loginForm";
//        }
//    }

    @ResponseBody
    @PostMapping("/join-naver")
    public String joinOrLoginWithNaver(@Validated MemberJoinOrLoginWithNaverDto memberJoinOrLoginWithNaverDto) {
        memberService.joinOrLoginWithNaver(memberJoinOrLoginWithNaverDto);
        return "성공";
    }

    @GetMapping("/profile")
    public String profile(HttpSession httpSession, Model model) {
        SessionRequestVo sessionInfo = (SessionRequestVo)httpSession.getAttribute("user");

        String loginId = sessionInfo.getId();
        Member findMember = memberRepository.findByLoginId(loginId).orElse(null);
        List<Search> recentSearch = searchService.getRecentSearch(findMember);

        model.addAttribute("member", sessionInfo);
        model.addAttribute("searchList", recentSearch);

        return "profile/profileForm";
    }
}

