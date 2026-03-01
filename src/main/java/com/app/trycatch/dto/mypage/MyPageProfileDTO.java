package com.app.trycatch.dto.mypage;

import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.common.enumeration.member.Status;
import com.app.trycatch.domain.member.IndividualMemberVO;
import com.app.trycatch.domain.member.MemberVO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class MyPageProfileDTO {
    private Long id;
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Long addressId;
    private Status memberStatus;

    private String individualMemberBirth;
    private Gender individualMemberGender;
    private String individualMemberEducation;
    private int individualMemberPoint;
    private int individualMemberLevel;
    private int individualMemberPostCount;
    private int individualMemberQuestionCount;
    private String memberProfileImageUrl;

    private String createdDatetime;
    private String updatedDatetime;

    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .id(id)
                .memberId(memberId)
                .memberName(memberName)
                .memberEmail(memberEmail)
                .memberPhone(memberPhone)
                .addressId(addressId)
                .memberStatus(memberStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }

    public IndividualMemberVO toIndividualMemberVO() {
        return IndividualMemberVO.builder()
                .id(id)
                .individualMemberBirth(individualMemberBirth)
                .individualMemberGender(individualMemberGender)
                .individualMemberEducation(individualMemberEducation)
                .individualMemberPoint(individualMemberPoint)
                .individualMemberLevel(individualMemberLevel)
                .individualMemberPostCount(individualMemberPostCount)
                .individualMemberQuestionCount(individualMemberQuestionCount)
                .build();
    }
}
