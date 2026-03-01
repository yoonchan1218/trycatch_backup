package com.app.trycatch.dto.experience;

import com.app.trycatch.common.enumeration.experience.ApplyStatus;
import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.domain.experience.ApplyVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ApplyDTO {
    private Long id;
    private Long experienceProgramId;
    private ApplyStatus applyStatus;
    private String createdDatetime;
    private String updatedDatetime;

    private Long memberId;
    private String individualMemberBirth;
    private Gender individualMemberGender;
    private String individualMemberEducation;
    private int individualMemberPoint;
    private int individualMemberLevel;
    private int individualMemberPostCount;
    private int individualMemberQuestionCount;

    private String memberName;
    private String memberEmail;
    private String memberPhone;

    private ChallengerDTO challenger;

    public ApplyVO toVO() {
        return ApplyVO.builder()
                .id(id)
                .experienceProgramId(experienceProgramId)
                .memberId(memberId)
                .applyStatus(applyStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
