package org.ducami.ducademi.server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.ducami.ducademi.server.domain.member.entity.Member;
import org.ducami.ducademi.server.domain.member.enums.Role;

@Data
@Builder
@AllArgsConstructor
public class MemberSignUpRequestDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String email;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Size(min = 2, message = "닉네임이 너무 짧습니다.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
            message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야 합니다.")
    private String password;

    private Role role;

    @NotBlank
    private String id;

    @NotBlank
    private String birth;

    @NotBlank
    private String phoneNum;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .email(email)
                .name(name)
                .birth(birth)
                .password(password)
                .role(Role.USER)
                .phoneNum(phoneNum)
                .build();
    }
}
