package com.app.trycatch.dto.member;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Provider;
import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.domain.member.MemberVO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class MemberDTO{
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
    private String createdDatetime;
    private String updatedDatetime;
    private Provider provider;
    private boolean remember;

    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .id(id)
                .memberId(memberId)
                .memberPassword(memberPassword)
                .memberName(memberName)
                .memberEmail(memberEmail)
                .memberPhone(memberPhone)
                .addressId(addressId)
                .memberStatus(memberStatus)
                .memberAgreePrivacy(memberAgreePrivacy)
                .memberAgreeMarketing(memberAgreeMarketing)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
