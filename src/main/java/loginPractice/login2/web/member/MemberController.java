package loginPractice.login2.web.member;

import loginPractice.login2.domain.member.Member;
import loginPractice.login2.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    // 회원 가입이니까 add페이지로 가서 post로 add했을때 오류와 정상처리 하기!
    private final MemberRepository memberRepository;
    @GetMapping("/add")
    public String addForm(@ModelAttribute("member")Member member) {
        return "members/addMemberForm";
    }
    @PostMapping("/add")
    public String save(@Validated @ModelAttribute Member member, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberRepository.save(member);
        return "redirect:/";
    }

}
