package com.app.trycatch.domain.skilllog;

import com.app.trycatch.common.enumeration.skillLog.SkillLogReportTargetType;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SkillLogReportVO {
    private Long id;
    private Long skillLogId;
}
