package com.app.trycatch.controller.report;

import com.app.trycatch.dto.skilllog.SkillLogCommentReportDTO;
import com.app.trycatch.dto.skilllog.SkillLogReportDTO;
import com.app.trycatch.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/report/**")
@RequiredArgsConstructor
@Slf4j
public class ReportController {
    private final ReportService reportService;

    @PostMapping("skill-log")
    public RedirectView skillLog(SkillLogReportDTO skillLogReportDTO) {
        reportService.skillLogReport(skillLogReportDTO);
        return new RedirectView("/skill-log/list");
    }

    @PostMapping("skill-log/comment")
    public RedirectView skillLogComment(SkillLogCommentReportDTO skillLogCommentReportDTO) {
        reportService.skillLogCommentReport(skillLogCommentReportDTO);
        return new RedirectView("/skill-log/list");
    }
}
