package com.app.trycatch.dto.report;

import com.app.trycatch.common.enumeration.file.FileContentType;
import com.app.trycatch.common.enumeration.report.ReportStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ReportDTO {
    private Long id;
    private Long memberId;
    private int reportReasonCode;
    private String reportReasonDetail;
    private ReportStatus reportStatus;
    private String reportProcessedAt;
    private String createdDatetime;
    private String updatedDatetime;
}
