package org.ducami.ducademi.server.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ducami.ducademi.server.domain.member.dto.MemberLoginDto;
import org.ducami.ducademi.server.domain.member.dto.MemberSignUpRequestDto;
import org.ducami.ducademi.server.domain.member.dto.TokenDto;
import org.ducami.ducademi.server.domain.member.entity.Member;
import org.ducami.ducademi.server.domain.member.repository.MemberRepository;
import org.ducami.ducademi.server.global.jwt.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider; // JwtTokenProvider 추가

    @Transactional
    @Override
    public String signUp(MemberSignUpRequestDto requestDto) {
        // 이메일 중복 확인
        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            return "멤버가 이미 가입되어있습니다";
        }

        // 회원 저장 및 비밀번호 암호화
        Member member = requestDto.toEntity();

        member.encodePassword(passwordEncoder);

        memberRepository.save(member);

        return "OK";
    }

    @Override
    public TokenDto login(MemberLoginDto login) {
        // 이메일을 기반으로 회원 조회
        Member member = memberRepository.findByEmail(login.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다"));

        String password = login.getPassword();

        // 비밀번호 검증
        if (!member.checkPassword(passwordEncoder, password)) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }

        // 회원의 역할을 JWT 토큰에 포함하여 생성
        List<String> roles = new ArrayList<>();
        roles.add(member.getRole().name());

        String token = jwtTokenProvider.createToken(member.getUsername(), roles);

        return new TokenDto(token);
    }
}
