package hoon.springmvc.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    @DisplayName("메시지 테스트")
    public void helloMessage() throws Exception {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @DisplayName("코드를 못찾았을 때")
    public void notFoundMessageCode() throws Exception {
        assertThatThrownBy(() -> messageSource.getMessage("nothing", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @DisplayName("코드를 못찾았을 때 기본 메시지 전달")
    public void notFoundMessageCodeDefaultMessage() throws Exception {
        String result = messageSource.getMessage("nothing", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    @DisplayName("값을 넣어서 메시지 만들기")
    public void argumentMessage() throws Exception {
        String result = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @Test
    @DisplayName("영어로 조회")
    public void enLang() throws Exception {
        String enLang = messageSource.getMessage("hello", null, Locale.ENGLISH);
        assertThat(enLang).isEqualTo("hello");
    }
}
