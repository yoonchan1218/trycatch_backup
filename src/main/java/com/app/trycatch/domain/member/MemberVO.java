package com.app.trycatch.domain.member;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Status;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of="id")
@Getter @ToString(callSuper = true)
@SuperBuilder
public class MemberVO extends Period {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Long addressId;
    private Status memberStatus;
    private boolean memberAgreePrivacy;
    private boolean memberAgreeMarketing;
}
