package hoon.springmvc.itemservice.web.login.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String loginId; //로그인 ID

    @NotEmpty
    private String password;
}
