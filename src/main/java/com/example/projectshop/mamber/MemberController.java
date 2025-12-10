package com.example.projectshop.mamber;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Authentication auth) {
        if(auth!=null){
            return "redirect:/list";
        }
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/member")
    public String addMember(
            String username,
            String password,
            String displayName
    ) {
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password));
        member.setDisplayName(displayName);
        memberRepository.save(member);

        return "redirect:/list";
    }
    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        return "mypage.html";
    }
}
