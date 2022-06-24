package hoon.springmvc.itemservice.web;

import hoon.springmvc.itemservice.domain.member.Member;
import hoon.springmvc.itemservice.domain.member.MemberRepository;
import hoon.springmvc.itemservice.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //@GetMapping("/")
    public String home() {
        return "home";
    }

    //@GetMapping("/")
    public String homeLoginV1(@CookieValue(name = "memberId", required = false) Long memberId,
                            Model model) {

        //로그인 안한 사용자
        if(memberId == null) {
            return "home";
        }

        //로그인 한 사용자
        Member loginMeber = memberRepository.findById(memberId);

        if(loginMeber == null) {
            return "home";
        }

        model.addAttribute("member", loginMeber);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV2(HttpServletRequest request,
                              Model model) {

        //세션 관리자에 저장된 회원 정보 조회
        Member member = (Member) sessionManager.getSession(request);

        //로그인 안한 사용자
        if(member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    //@GetMapping("/")
    public String homeLoginV3(HttpServletRequest request,
                              Model model) {

        HttpSession session = request.getSession(false);
        if(session == null) {
            return "home";
        }

        //세션 관리자에 저장된 회원 정보 조회
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //로그인 안한 사용자
        if(member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                              Model model) {

        //로그인 안한 사용자
        if(member == null) {
            return "home";
        }

        model.addAttribute("member", member);
        return "loginHome";
    }
}
