package hoon.springmvc.itemservice.web;

import hoon.springmvc.itemservice.domain.member.Member;
import hoon.springmvc.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    //@GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId,
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
}
