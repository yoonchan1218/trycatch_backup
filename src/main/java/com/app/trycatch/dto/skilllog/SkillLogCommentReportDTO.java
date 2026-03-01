package com.app.trycatch.dto.skilllog;

import com.app.trycatch.common.enumeration.report.ReportStatus;
import com.app.trycatch.domain.report.ReportVO;
import com.app.trycatch.domain.skilllog.SkillLogCommentReportVO;
import com.app.trycatch.domain.skilllog.SkillLogReportVO;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class SkillLogCommentReportDTO {
    private Long id;
    private Long skillLogCommentId;

    private Long memberId;
    private int reportReasonCode;
    private String reportReasonDetail;
    private ReportStatus reportStatus;
    private String reportProcessedAt;
    private String createdDatetime;
    private String updatedDatetime;

    public SkillLogCommentReportVO toSkillLogCommentReportVO() {
        return SkillLogCommentReportVO.builder()
                .id(id)
                .skillLogCommentId(skillLogCommentId)
                .build();
    }

    public ReportVO toReportVO() {
        return ReportVO.builder()
                .id(id)
                .memberId(memberId)
                .reportReasonCode(reportReasonCode)
                .reportReasonDetail(reportReasonDetail)
                .reportStatus(reportStatus)
                .reportProcessedAt(reportProcessedAt)
                .createdDatetime(createdDatetime)
                .updatedDatetime(updatedDatetime)
                .build();
    }
}
