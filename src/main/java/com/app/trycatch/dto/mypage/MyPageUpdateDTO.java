package com.app.trycatch.dto.mypage;

import com.app.trycatch.common.enumeration.member.Gender;
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
public class MyPageUpdateDTO {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private String individualMemberBirth;
    private Gender individualMemberGender;
    private String individualMemberEducation;

    public MemberVO toMemberVO() {
        return MemberVO.builder()
                .id(id)
                .memberName(memberName)
                .memberEmail(memberEmail)
                .memberPhone(memberPhone)
                .build();
    }

    public IndividualMemberVO toIndividualMemberVO() {
        return IndividualMemberVO.builder()
                .id(id)
                .individualMemberBirth(individualMemberBirth)
                .individualMemberGender(individualMemberGender)
                .individualMemberEducation(individualMemberEducation)
                .build();
    }
}
