package com.app.trycatch.domain.member;

import com.app.trycatch.common.enumeration.member.Gender;
import lombok.*;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class IndividualMemberVO {
    private Long id;
    private String individualMemberBirth;
    private Gender individualMemberGender;
    private String individualMemberEducation;
    private int individualMemberPoint;
    private int individualMemberLevel;
    private int individualMemberPostCount;
    private int individualMemberQuestionCount;
}
