package com.app.trycatch.service.report;

import com.app.trycatch.dto.report.ReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogCommentReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogReportDTO;
import com.app.trycatch.repository.report.ReportDAO;
import com.app.trycatch.repository.skilllog.SkillLogCommentReportDAO;
import com.app.trycatch.repository.skilllog.SkillLogReportDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ReportService {
    private final ReportDAO reportDAO;
    private final SkillLogReportDAO skillLogReportDAO;
    private final SkillLogCommentReportDAO skillLogCommentReportDAO;

    //    skillLog 신고
    public void skillLogReport(SkillLogReportDTO skillLogReportDTO) {
        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setMemberId(skillLogReportDTO.getMemberId());
        reportDTO.setReportReasonCode(skillLogReportDTO.getReportReasonCode());
        reportDTO.setReportReasonDetail(skillLogReportDTO.getReportReasonDetail());

        reportDAO.save(reportDTO);
        skillLogReportDTO.setId(reportDTO.getId());

        skillLogReportDAO.save(skillLogReportDTO.toSkillLogReportVO());
    }
    //    skillLog comment 신고
    public void skillLogCommentReport(SkillLogCommentReportDTO skillLogCommentReportDTO) {
        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setMemberId(skillLogCommentReportDTO.getMemberId());
        reportDTO.setReportReasonCode(skillLogCommentReportDTO.getReportReasonCode());
        reportDTO.setReportReasonDetail(skillLogCommentReportDTO.getReportReasonDetail());

        reportDAO.save(reportDTO);
        skillLogCommentReportDTO.setId(reportDTO.getId());

        skillLogCommentReportDAO.save(skillLogCommentReportDTO.toSkillLogCommentReportVO());
    }

}
