package hoon.springmvc.itemservice.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    private Long id;

    @NotEmpty
    private String loginId; //로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
