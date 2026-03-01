package com.app.trycatch.dto.experience;

import com.app.trycatch.common.enumeration.experience.ChallengerStatus;
import com.app.trycatch.common.enumeration.member.Gender;
import com.app.trycatch.domain.experience.ChallengerVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ChallengerDTO {
    private Long id;
    private Long applyId;
    private ChallengerStatus challengerStatus;
    private String createdDatetime;
    private String updatedDatetime;

//    view_participant 조인 필드
    private Long experienceProgramId;
    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private Long memberProfileFileId;
    private String individualMemberBirth;
    private Gender individualMemberGender;

    public ChallengerVO toVO() {
        return ChallengerVO.builder()
                .id(id)
                .applyId(applyId)
                .challengerStatus(challengerStatus)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
