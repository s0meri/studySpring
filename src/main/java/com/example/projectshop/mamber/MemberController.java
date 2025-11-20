package com.example.projectshop.mamber;

import lombok.RequiredArgsConstructor;
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
    public String register() {
        return "register.html";
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
}
