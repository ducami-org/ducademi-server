package org.ducami.ducademi.server.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
