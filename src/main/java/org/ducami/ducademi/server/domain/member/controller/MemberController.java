package org.ducami.ducademi.server.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ducami.ducademi.server.domain.member.dto.MemberLoginDto;
import org.ducami.ducademi.server.domain.member.dto.MemberSignUpRequestDto;
import org.ducami.ducademi.server.domain.member.dto.TokenDto;
import org.ducami.ducademi.server.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public String join(@Valid @RequestBody MemberSignUpRequestDto request) {
        return memberService.signUp(request);
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody MemberLoginDto login) {
        return memberService.login(login);
    }
}
