package com.app.trycatch.domain.skilllog;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SkillLogCommentReportVO {
    private Long id;
    private Long skillLogCommentId;
}
