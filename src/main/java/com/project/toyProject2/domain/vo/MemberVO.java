package com.project.toyProject2.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberVO {
    private Long memberId;
    private String memberLoginId;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberRole;
    private LocalDateTime memberRegisterDate;
    private LocalDateTime memberUpdateDate;
}
