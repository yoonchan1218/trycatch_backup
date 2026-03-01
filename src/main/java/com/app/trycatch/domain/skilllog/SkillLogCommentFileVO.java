package com.app.trycatch.domain.skilllog;

import com.app.trycatch.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SkillLogCommentFileVO{
    private Long id;
    private Long skillLogCommentId;
}
