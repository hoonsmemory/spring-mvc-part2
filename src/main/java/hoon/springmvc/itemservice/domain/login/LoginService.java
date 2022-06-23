package hoon.springmvc.itemservice.domain.login;

import hoon.springmvc.itemservice.domain.member.Member;
import hoon.springmvc.itemservice.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     *
     * @param loginId
     * @param password
     * @return null이면 로그인 실패     */
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m-> m.getPassword().equals(password))
                .orElse(null);
    }
}
