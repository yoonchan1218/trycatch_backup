package com.app.trycatch.domain.skilllog;

import com.app.trycatch.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class SkillLogCommentLikesVO extends Period {
    private Long id;
    private Long memberId;
    private Long skillLogCommentId;
}
