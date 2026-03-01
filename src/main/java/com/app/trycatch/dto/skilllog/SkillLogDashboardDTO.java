package com.app.trycatch.dto.skilllog;

import com.app.trycatch.dto.member.IndividualMemberDTO;
import com.app.trycatch.dto.member.MemberDTO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogDashboardDTO {
    private IndividualMemberDTO individualMemberDTO;

    private int individualMemberPostCount;
    private int skillLogViewCountTotal;
    private int skillLogLikeCountTotal;
    private int skillLogCommentCount;
}
