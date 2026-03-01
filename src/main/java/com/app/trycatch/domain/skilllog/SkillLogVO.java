package com.app.trycatch.domain.skilllog;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.skillLog.SkillLogStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class SkillLogVO extends Period {
    private Long id;
    private Long memberId;
    private Long experienceProgramId;
    private String skillLogTitle;
    private String skillLogContent;
    private int skillLogViewCount;
    private SkillLogStatus skillLogStatus;
}
