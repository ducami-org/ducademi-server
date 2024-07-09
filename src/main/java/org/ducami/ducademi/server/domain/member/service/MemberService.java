package org.ducami.ducademi.server.domain.member.service;

import org.ducami.ducademi.server.domain.member.dto.MemberLoginDto;
import org.ducami.ducademi.server.domain.member.dto.MemberSignUpRequestDto;
import org.ducami.ducademi.server.domain.member.dto.TokenDto;

public interface MemberService {
    // 회원가입
    String signUp(MemberSignUpRequestDto requestDto);
    TokenDto login(MemberLoginDto members);
}
