package loginPractice.login2.web.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import loginPractice.login2.domain.login.LoginService;
import loginPractice.login2.domain.member.Member;
import loginPractice.login2.domain.member.MemberRepository;
import loginPractice.login2.web.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final MemberRepository memberRepository;
    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") Login login) {
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String loginV3(@Validated @ModelAttribute Login login, BindingResult bindingResult, HttpServletRequest httpRequest) {
        if(bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member member = loginService.login(login.getLoginId(), login.getPassword());

        if(member == null) {
            bindingResult.reject("loginFail","아이디 또는 패스워드 잘못");
            return "login/loginForm";
        }
        HttpSession session = httpRequest.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        return "redirect:/";

    }
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        HttpSession session =request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }




}

