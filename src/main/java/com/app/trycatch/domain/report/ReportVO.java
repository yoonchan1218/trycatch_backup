package com.app.trycatch.domain.report;

import com.app.trycatch.audit.Period;
import com.app.trycatch.common.enumeration.report.ReportStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class ReportVO extends Period {
    private Long id;
    private Long memberId;
    private int reportReasonCode;
    private String reportReasonDetail;
    private ReportStatus reportStatus;
    private String reportProcessedAt;
}
